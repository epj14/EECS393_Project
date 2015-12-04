package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.itextpdf.text.DocumentException;

import main.InvalidFileException;
import main.PDFWriter;
import main.Searcher;

/**
 * a class that tests Searcher
 */
public class SearcherTest {
	
	// the directory where the search will be executed
	private String directory;
	// the Searcher that will be used to run the tests
	private Searcher s;
	
	/**
	 * the TemporaryFolder that will contain the files to search
	 */
	@Rule
	public TemporaryFolder folder = new TemporaryFolder();
	
	/**
	 * initializes the test configuration by instantiating the Searcher that will be 
	 * used to run the tests
	 * @throws IOException
	 * @throws InvalidFileException
	 * @throws DocumentException
	 */
	@Before
	public void setUp() throws IOException, InvalidFileException, DocumentException {
		directory = folder.getRoot().getAbsolutePath();
		String inputFilename = "input.html";
		String inputFilepath = folder.getRoot().getAbsolutePath() + File.separator + inputFilename;
		String outputFilepath = folder.getRoot().getAbsolutePath() + File.separator + "output.pdf";
		File file = folder.newFile(inputFilename);
		String inputFileContents = "<!doctype html>\n"
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
		PDFWriter pw = new PDFWriter(inputFilepath, outputFilepath);
		pw.writePDF();
		s = new Searcher(directory);
	}
	
	/**
	 * tests if getDirectory successfully gets the directory where the search will be 
	 * executed
	 */
	@Test
	public void testGetDirectory() {
		assertEquals(directory, s.getDirectory());
	}
	
	/**
	 * tests if setDirectory successfully sets the directory where the search will be 
	 * executed
	 */
	@Test
	public void testSetDirectory() {
		s.setDirectory("newdirectory");
		assertEquals("newdirectory", s.getDirectory());
	}
	
	/**
	 * tests if search successfully determines files containing the query if there is 
	 * only a single file in the directory that the search is executed in
	 * @throws IOException
	 */
	@Test
	public void testSearch() throws IOException {
		ArrayList<String> expected = new ArrayList<String>();
		expected.add(folder.getRoot().getAbsolutePath() + File.separator + "output.pdf");
		assertEquals(expected, s.search("test"));
	}
	
	/**
	 * tests if search returns an empty ArrayList<String> if there are no files in the 
	 * directory that the search is executed in
	 * @throws IOException
	 */
	@Test
	public void testSearch_Empty() throws IOException {
		ArrayList<String> expected = new ArrayList<String>();
		folder.delete();
		assertEquals(expected, s.search("test"));
	}
	
	/**
	 * tests if search successfully determines files containing the query if there are 
	 * multiple files in the directory that the search is executed in
	 * @throws IOException
	 * @throws InvalidFileException
	 * @throws DocumentException
	 */
	@Test
	public void testSearch_Multiple() throws IOException, InvalidFileException, DocumentException {
		ArrayList<String> expected = new ArrayList<String>();
		String inputFilename1 = "input1.html";
		String inputFilepath1 = folder.getRoot().getAbsolutePath() + File.separator + inputFilename1;
		String outputFilepath1 = folder.getRoot().getAbsolutePath() + File.separator + "output1.pdf";
		File file1 = folder.newFile(inputFilename1);
		String inputFileContents1 = "<!doctype html>\n"
				+ "<html>\n"
				+ " <head></head>\n"
				+ " <body> \n"
				+ "  <h1>heading</h1> \n"
				+ "  <p>this is test content</p>   \n"
				+ " </body>\n"
				+ "</html>";
		PrintWriter prw1 = new PrintWriter(file1.getAbsolutePath());
		prw1.println(inputFileContents1);
		prw1.close();
		PDFWriter pw1 = new PDFWriter(inputFilepath1, outputFilepath1);
		pw1.writePDF();
		String inputFilename2 = "input2.html";
		String inputFilepath2 = folder.getRoot().getAbsolutePath() + File.separator + inputFilename2;
		String outputFilepath2 = folder.getRoot().getAbsolutePath() + File.separator + "output2.pdf";
		File file2 = folder.newFile(inputFilename2);
		String inputFileContents2 = "<!doctype html>\n"
				+ "<html>\n"
				+ " <head></head>\n"
				+ " <body> \n"
				+ "  <h1>heading</h1> \n"
				+ "  <p>this is content</p>   \n"
				+ " </body>\n"
				+ "</html>";
		PrintWriter prw2 = new PrintWriter(file2.getAbsolutePath());
		prw2.println(inputFileContents2);
		prw2.close();
		PDFWriter pw2 = new PDFWriter(inputFilepath2, outputFilepath2);
		pw2.writePDF();
		expected.add(folder.getRoot().getAbsolutePath() + File.separator + "output.pdf");
		expected.add(folder.getRoot().getAbsolutePath() + File.separator + "output1.pdf");
		assertEquals(expected, s.search("test"));
	}

}
