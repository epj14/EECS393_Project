package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.itextpdf.text.DocumentException;

import main.InvalidFileException;
import main.PDFWriter;
import main.StringUtil;

/**
 * a class that tests PDFWriter
 */
public class PDFWriterTests {

	//the filename of the input file
	private String inputFilename;
	//the file path of the input file
	private String inputFilepath;
	//the file path of the output file
	private String outputFilepath;
	//the contents of the input file
	private String inputFileContents;
	//the PDFWriter that will be used to run the tests
	private PDFWriter pw;
	
	/**
	 * the TemporaryFolder that will contain the input file and the output file, these 
	 * files will be removed after each test so they do not persist throughout other tests
	 */
	@Rule
	public TemporaryFolder folder = new TemporaryFolder();
	
	/**
	 * initializes the test configuration by creating the input file and instantiating 
	 * the PDFWriter that will be used to run the tests
	 * @throws IOException
	 * @throws InvalidFileException
	 */
	@Before
	public void setUp() throws IOException, InvalidFileException {
		inputFilename = "input.html";
		inputFilepath = folder.getRoot().getAbsolutePath() + File.separator + inputFilename;
		outputFilepath = folder.getRoot().getAbsolutePath() + File.separator + "output.pdf";
		File file = folder.newFile(inputFilename);
		inputFileContents = "<!doctype html>\n"
				+ "<html>\n"
				+ " <head></head>\n"
				+ " <body> \n"
				+ "  <h1>heading</h1> \n"
				+ "  <p>this is test content</p>   \n"
				+ " </body>\n"
				+ "</html>";
		PrintWriter prw = new PrintWriter(file.getAbsolutePath());
		prw.println(inputFileContents);
		prw.close();
		pw = new PDFWriter(inputFilepath, outputFilepath);
	}
	
	/**
	 * tests if getInputFilename successfully gets the input filename
	 */
	@Test
	public void testGetInputFilename() {
		assertEquals(inputFilepath, pw.getInputFilename());
	}
	
	/**
	 * tests if getOutputFilename successfully gets the output filename when an output 
	 * filename is specified
	 * @throws IOException
	 * @throws InvalidFileException
	 */
	@Test
	public void testGetOuputFilename_Specified() throws IOException, InvalidFileException {
		pw = new PDFWriter(inputFilepath, outputFilepath);
		assertEquals(outputFilepath, pw.getOutputFilename());
	}
	
	/**
	 * tests if getOutputFilename successfully gets the output filename when an output 
	 * filename is not specified
	 * @throws InvalidFileException 
	 * @throws IOException 
	 */
	@Test
	public void testGetOutputFilename_Default() throws IOException, InvalidFileException {
		pw = new PDFWriter(inputFilepath);
		assertEquals(StringUtil.prependOutput(StringUtil.replaceExtension(inputFilepath, "pdf")), pw.getOutputFilename());
	}
	
	/**
	 * tests if setOutputFilename successfully sets the output filename
	 */
	@Test
	public void testSetOutputFilename() {
		pw.setOutputFilename("newoutputfilename.pdf");
		assertEquals("newoutputfilename.pdf", pw.getOutputFilename());
	}
	
	/**
	 * tests if the constructor throws an InvalidFileExtension if the specified input 
	 * file is not an html file
	 * @throws IOException
	 * @throws InvalidFileException
	 * @throws DocumentException
	 */
	@Test(expected = InvalidFileException.class)
	public void testPDFWriter_InvalidExtension() throws IOException, InvalidFileException, DocumentException {
		pw = new PDFWriter("a.b", outputFilepath);
	}
	
	/**
	 * tests if writePDF successfully creates an output PDF file
	 * @throws DocumentException
	 * @throws IOException
	 */
	@Test
	public void testWritePDF() throws DocumentException, IOException {
		pw.writePDF();
		File outputFile = new File(outputFilepath);
		assertTrue(outputFile.exists());
	}
	
	/**
	 * tests if writePDF throws an InvalidFileException if the specified input file 
	 * does not exist
	 * @throws IOException
	 * @throws InvalidFileException
	 * @throws DocumentException
	 */
	@Test(expected = InvalidFileException.class)
	public void testWritePDF_InputFileNotFound() throws IOException, InvalidFileException, DocumentException {
		pw = new PDFWriter("", outputFilepath);
		pw.writePDF();
	}
	
}
