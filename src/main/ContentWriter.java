package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.pegdown.PegDownProcessor;

/**
 * a class that is used to write user supplied content to a temporary template file
 */
public class ContentWriter {
	
	// the filename of the template file
	private String templateFilename;
	// the filename of the output file
	private String outputFilename;
	
	/**
	 * constructor for ContentWriter, creates the output file as a copy of the 
	 * template file
	 * @param templateFilename the filename of the template file
	 * @param outputFilename the filename of the output file
	 * @throws IOException if an I/O error occurs
	 * @throws InvalidFileException if the template file is not a valid type
	 */
	public ContentWriter(String templateFilename, String outputFilename) throws IOException, InvalidFileException {
		if (Files.probeContentType(Paths.get(templateFilename)) != null && Files.probeContentType(Paths.get(templateFilename)).equals("text/html")) {
			this.templateFilename = templateFilename;
			this.outputFilename = outputFilename;
			generateOutputFile(templateFilename, outputFilename);
		} else {
			throw new InvalidFileException("invalid file type: " + 
					Files.probeContentType(Paths.get(templateFilename)) + 
					", template file type must be text/html");
		}
	}
	
	/**
	 * constructor for ContentWriter, creates the output file as a copy of the 
	 * template file, defaults to using "output" + templateFilename as the name of the 
	 * output file because no output filename was given
	 * @param templateFilename the filename of the template file
	 * @throws IOException if an I/O error occurs
	 * @throws InvalidFileException if the template file is not a valid type
	 */
	public ContentWriter(String templateFilename) throws IOException, InvalidFileException {
		this(templateFilename, StringUtil.prependOutput(StringUtil.replaceExtension(templateFilename, "html")));
	}
	
	/**
	 * gets the template filename
	 * @return the template filename
	 */
	public String getTemplateFilename() {
		return templateFilename;
	}

	/**
	 * gets the output filename
	 * @return the output filename
	 */
	public String getOutputFilename() {
		return outputFilename;
	}

	/**
	 * sets the output filename
	 * @param outputFilename the output filename to set
	 */
	public void setOutputFilename(String outputFilename) {
		this.outputFilename = outputFilename;
	}

	/**
	 * generates the the output file as a copy of the template file
	 * @param templateFilename the filename of the template file
	 * @param outputFilename the filename of the output file
	 * @throws IOException if an I/O error occurs
	 */
	private static void generateOutputFile(String templateFilename, String outputFilename) throws IOException {
		InputStream in = new FileInputStream(new File(templateFilename));
		OutputStream out = new FileOutputStream(new File(outputFilename));
		byte[] buf = new byte[8192];
		int len;
		while ((len = in.read(buf)) > 0) {
			out.write(buf, 0, len);
		}
		in.close();
		out.close();
	}
	
	/**
	 * gets all of the headings in the output file
	 * @return all of the headings in the output file in an Elements object
	 * @throws IOException if an I/O error occurs
	 */
	public Elements getHeadings() throws IOException {
		File outputFile = new File(outputFilename);
		Document doc = Jsoup.parse(outputFile, "UTF-8");
		return doc.select("h1, h2, h3, h4, h5, h6");
	}
	
	/**
	 * gets all of the headings in the output file as a String[]
	 * @return all of the headings in the output file as a String[]
	 * @throws IOException if an I/O error occurs
	 */
	public String[] getHeadingsArray() throws IOException {
		Elements es = getHeadings();
		return StringUtil.removeH(es.toString()).split("\n");
	}
	
	/**
	 * writes user supplied input into the output file under the first p section in the 
	 * document, this section is meant to contain general information about the person 
	 * who is authoring the document (for example: contact information)
	 * @param content the content to insert
	 * @throws IOException if an I/O error occurs
	 */
	public void writeInitialContent(String content) throws IOException {
		File outputFile = new File(outputFilename);
		Document doc = Jsoup.parse(outputFile, "UTF-8");
		Element b = doc.select("body").get(0);
		Element p = b.child(0);
		if (!(p.tag().getName().equals("p"))) {
			b.prependElement("p");
			p = b.child(0);
		}
		content = StringUtil.removeH(content);
		PegDownProcessor pdp = new PegDownProcessor();
		p.text(StringUtil.stripP(pdp.markdownToHtml(content)));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile), "UTF-8"));
		bw.write(doc.toString());
		bw.close();
	}
	
	/**
	 * writes user supplied input into the output file under the specified section
	 * @param heading the name of the section where content will be inserted, must be 
	 * unique for the file
	 * @param content the content to insert
	 * @throws IOException if an I/O error occurs
	 */
	public void writeContent(String heading, String content) throws IOException {
		File outputFile = new File(outputFilename);
		Document doc = Jsoup.parse(outputFile, "UTF-8");
		Elements es = doc.select("h1, h2, h3, h4, h5, h6:contains(" + heading + ")");
		Element h = null;
		for (Element e : es) {
			if (e.text().equals(heading)) {
				h = e;
			}
		}
		if (h == null) {
			throw new IOException("heading " + heading + " was not found");
		}
		Element p = h.nextElementSibling();
		content = StringUtil.removeH(content);
		PegDownProcessor pdp = new PegDownProcessor();
		p.html(StringUtil.stripP(pdp.markdownToHtml(content)));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile), "UTF-8"));
		bw.write(doc.toString());
		bw.close();
	}
	
}
