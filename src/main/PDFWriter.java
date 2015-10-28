package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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
		if (Files.probeContentType(Paths.get(inputFilename)).equals("application/pdf")) {
			this.inputFilename = inputFilename;
			this.outputFilename = outputFilename;
		} else {
			throw new InvalidFileException("invalid file type: " + 
					Paths.get(inputFilename) + ", input file type must be application/pdf");
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
		this(inputFilename, StringUtil.prependOutput(inputFilename));
	}
	
}
