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
		if (Files.probeContentType(Paths.get(templateFilename)).equals("text/html")) {
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
		this(templateFilename, StringUtil.prependOutput(templateFilename));
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
	 * puts user supplied input into the output file under the specified section
	 * @param header the name of the section where content will be inserted, must be 
	 * unique for the file
	 * @param content the content to insert
	 * @throws IOException if an I/O error occurs
	 */
	public void writeContent(String header, String content) throws IOException {
		//TODO: expand this method to support more than just <h1> elements
		//TODO: if there is a problem reading or writing the output file, regenerate using the template 
		File outputFile = new File(outputFilename);
		Document doc = Jsoup.parse(outputFile, "UTF-8");
		Element h = doc.select("h1:contains(" + header + ")").get(0);
		Element p = h.nextElementSibling();
		p.text(content);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile), "UTF-8"));
		bw.write(doc.toString());
		bw.close();
	}
	
}
