package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import org.jsoup.Jsoup;

/**
 * a class that is used to write a user defined template to a template file
 */
public class TemplateWriter {
	
	// the filename of the template file
	private String templateFilename;
	// the content that will be added to the template file
	private ArrayList<String> contentList;
<<<<<<< HEAD

	// Style parameters
	private ArrayList<String> styles;
=======
>>>>>>> master
	
	/**
	 * constructor for TemplateWriter
	 * @param templateFilename the filename of the template file
	 */
	public TemplateWriter(String templateFilename) {
		this.templateFilename = StringUtil.replaceExtension(templateFilename, "html");
		this.contentList = new ArrayList<String>();
	}
	
	/**
	 * gets the template filename
	 * @return the template filename
	 */
	public String getTemplateFilename() {
		return templateFilename;
	}
	
	/**
	 * sets the template filename
	 * @param templateFilename the template filename to set
	 */
	public void setTemplateFilename(String templateFilename) {
		this.templateFilename = StringUtil.replaceExtension(templateFilename, "html");
	}
	
	/**
	 * gets the content that will be added to the template file
	 * @return the content that will be added to the template file
	 */
	public ArrayList<String> getContentList() {
		return contentList;
	}
	
	/**
	 * generates the template file that the content stored by contentList will be written 
	 * into
	 * @param templateFilename the filename of the template file
	 * @return the File that was generated
	 */
	private static File generateTemplateFile(String templateFilename) {
		return new File(templateFilename);
	}
	
	/**
	 * generates the HTML for the template using the given heading 
	 * @param heading the heading that will be used to generate HTML for the template
	 * @return the HTML that was generated for the template using heading, or null if 
	 * heading was null or the empty string
	 */
	private static String generateHTML(String heading) {
		heading = StringUtil.removeH(heading);
		if (heading.length() == 0) {
			return null;
		} else {
			StringBuilder sb = new StringBuilder();
			sb.append("<h1>" + heading + "</h1>");
			sb.append("<p></p>");
			return sb.toString();
		}
	}
	
	/**
	 * appends the HTML for the template using the given heading to contentList
	 * @param heading the heading that will be used to generate HTML for the template 
	 * that will be added to contentList
	 * @throws NonuniqueHeadingException if contentList already contains HTML that was 
	 * generated by heading
	 */
	public void appendTemplateContent(String heading) throws NonuniqueHeadingException {
		String content = generateHTML(heading);
		if (contentList.contains(content)) {
			throw new NonuniqueHeadingException("heading " + heading + "already used, "
					+ "template headings must be unique");
		}
		if (content != null) {
			contentList.add(content);
		}
	}
<<<<<<< HEAD

	/**
	 * adds CSS styling for headings and paragraphs
	 * @param style that will be passed to CSS header
	 * @param stylePlace used to match style information with proper value in CSS
	 */
	public void addStyle(String style, int stylePlace) {
		styles[stylePlace] = style;
	}
=======
>>>>>>> master
	
	/**
	 * writes the content stored by contentList into the template file and parses the 
	 * resulting template file to ensure that it is valid HTML, any existing content in 
	 * the template file will be overwritten, contentList is not altered
	 * @throws IOException if an I/O error occurs
	 */
	public void writeTemplateContent() throws IOException {
		File templateFile = generateTemplateFile(templateFilename);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(templateFile, false), "UTF-8"));
<<<<<<< HEAD
		bw.write(
			"<!DOCTYPE html>\n
			<html>\n
			<head>\n
			</head>\n
			<style>\n
			h1 {\n
				font-size: ");
		bw.write(styles[0]);
		bw.write("\nfont-style: ");
		bw.write(styles[1]);
		bw.write("\nfont-family: ");
		bw.write(styles[2]);
		bw.write("\nfont-weight: ");
		bw.write(styles[3]);

		bw.write(
			"}\n
			p {\n				
				font-size: ");
		bw.write(styles[0]);
		bw.write("\nfont-style: ");
		bw.write(styles[1]);
		bw.write("\nfont-family: ");
		bw.write(styles[2]);
		bw.write("\nfont-weight: ");
		bw.write(styles[3]);
		bw.write("\n}")
		bw.write("</style>");
		bw.write("<body>");
	
=======
		bw.write("<!DOCTYPE html>\n<html><head></head><body><p></p>");
>>>>>>> master
		for (String s : contentList) {
			bw.write(s);
		}
		bw.write("</body></html>");
		bw.close();
		Jsoup.parse(templateFile, "UTF-8");
	}
	
}
