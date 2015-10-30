package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

/**
 * a class that is used to write a template file to a PDF file
 */
public class PDFWriter {
	
	// the filename of the input file
	private String inputFilename;
	// the filename of the output file
	private String outputFilename;
	
	/**
	 * constructor for PDFWriter
	 * @param inputFilename the filename of the input file
	 * @param outputFilename the filename of the output file
	 * @throws IOException if the input file cannot be found
	 * @throws InvalidFileException if the input file is not a valid type
	 */
	public PDFWriter(String inputFilename, String outputFilename) throws IOException, InvalidFileException {
		if (Files.probeContentType(Paths.get(inputFilename)).equals("text/html")) {
			this.inputFilename = inputFilename;
			this.outputFilename = outputFilename;
		} else {
			throw new InvalidFileException("invalid file type: " + 
					Files.probeContentType(Paths.get(inputFilename)) + 
					", input file type must be text/html");
		}
	}
	
	/**
	 * constructor for PDFWriter, defaults to using "output" + outputFilename as the name 
	 * of the output file because no output filename was given
	 * @param inputFilename the filename of the input file
	 * @throws IOException if the input file cannot be found
	 * @throws InvalidFileException if the input file is not a valid type
	 */
	public PDFWriter(String inputFilename) throws IOException, InvalidFileException {
		this(inputFilename, StringUtil.prependOutput(StringUtil.replaceExtension(inputFilename, "pdf")));
	}
	
	/**
	 * gets the input filename
	 * @return the input filename
	 */
	public String getInputFilename() {
		return inputFilename;
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
	 * writes a pdf file named outputFilename using the content from the file specified 
	 * by inputFilename
	 * @throws DocumentException if PdfWriter fails to get an instance
	 * @throws IOException if the file specified by inputFileName cannot be found
	 */
	public void writePDF() throws DocumentException, IOException {
		Document document = new Document();
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(new File(outputFilename)));
		document.open();
		XMLWorkerHelper.getInstance().parseXHtml(writer, document, new FileInputStream(inputFilename));
		document.close();
	}
	
}
