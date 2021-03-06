package test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import main.ContentWriter;
import main.InvalidFileException;
import main.NonuniqueHeadingException;
import main.StringUtil;

/**
 * a class that tests ContentWriter
 */
public class ContentWriterTest {
	
	//the filename of the template file
	private String templateFilename;
	//the file path of the template file
	private String templateFilepath;
	//the filename of the output file
	private String outputFilepath;
	//the contents of the template file
	private String fileContents;
	//the ContentWriter that will be used to run the tests
	private ContentWriter cw;
	
	/**
	 * the TemporaryFolder that will contain the template file and the output file, these 
	 * files will be removed after each test so they do not persist throughout other tests
	 */
	@Rule
	public TemporaryFolder folder = new TemporaryFolder();
	
	/**
	 * initializes the test configuration by creating the template file and instantiating 
	 * the ContentWriter that will be used to run the tests
	 * @throws IOException
	 * @throws InvalidFileException
	 */
	@Before
	public void setUp() throws IOException, InvalidFileException {
		templateFilename = "test.html";
		templateFilepath = folder.getRoot().getAbsolutePath() + File.separator + templateFilename;
		outputFilepath = folder.getRoot().getAbsolutePath() + File.separator + "output.html";
		File file = folder.newFile(templateFilename);
		fileContents = "<!doctype html>\n"
				+ "<html>\n"
				+ "<body>\n"
				+ "<p></p>\n"
				+ " <h1>heading</h1>\n"
				+ " <p></p>\n"
				+ "</body>\n"
				+ "</html>";
		PrintWriter pw = new PrintWriter(file.getAbsolutePath());
		pw.println(fileContents);
		pw.close();
		cw = new ContentWriter(templateFilepath);
	}
	
	/**
	 * tests if the ContentWriter constructor throws an InvalidFileException if the 
	 * given template file is an invalid file type
	 * @throws IOException
	 * @throws InvalidFileException
	 */
	@Test(expected = InvalidFileException.class)
	public void testContentWriter_InvalidFile() throws IOException, InvalidFileException {
		templateFilename = "test.txt";
		templateFilepath = folder.getRoot().getAbsolutePath() + File.separator + templateFilename;
		File file = folder.newFile(templateFilename);
		fileContents = "test";
		PrintWriter pw = new PrintWriter(file.getAbsolutePath());
		pw.println(fileContents);
		pw.close();
		cw = new ContentWriter(templateFilepath);
	}
	
	/**
	 * tests if getTemplateFilename successfully gets the template filename
	 */
	@Test
	public void testGetTemplateFilename() {
		assertEquals(templateFilepath, cw.getTemplateFilename());
	}
	
	/**
	 * tests if getOutputFilename successfully gets the output filename when an output 
	 * filename is specified
	 * @throws IOException
	 * @throws InvalidFileException
	 */
	@Test
	public void testGetOutputFilename_Specified() throws IOException, InvalidFileException {
		cw = new ContentWriter(templateFilepath, outputFilepath);
		assertEquals(outputFilepath, cw.getOutputFilename());
	}
	
	/**
	 * tests if getOutputFilename successfully gets the output filename when an output 
	 * filename is not specified
	 */
	@Test
	public void testGetOutputFilename_Default() {
		assertEquals(StringUtil.prependOutput(templateFilepath), cw.getOutputFilename());
	}
	
	/**
	 * tests if setOutputFilename successfully sets the output filename
	 */
	@Test
	public void testSetOutputFilename() {
		cw.setOutputFilename("newoutputfilename.html");
		assertEquals("newoutputfilename.html", cw.getOutputFilename());
	}
	
	/**
	 * tests if getHeadings successfully gets a single heading
	 * @throws IOException
	 */
	@Test
	public void testGetHeadings() throws IOException {
		Elements expected = new Elements();
		expected.add(new Element(Tag.valueOf("h1"), "").appendText("heading"));
		assertEquals(expected, cw.getHeadings());
	}
	
	/**
	 * tests if getHeadings successfully gets multiple different headings
	 * @throws IOException
	 * @throws InvalidFileException
	 */
	@Test
	public void testGetHeadings_Multiple() throws IOException, InvalidFileException {
		templateFilename = "test2.html";
		templateFilepath = folder.getRoot().getAbsolutePath() + File.separator + templateFilename;
		File file = folder.newFile(templateFilename);
		fileContents = "<!doctype html>\n"
				+ "<html>\n"
				+ "<body>\n"
				+ "<p></p>\n"
				+ " <h1>heading 1</h1>\n"
				+ " <p></p>\n"
				+ " <h2>heading 2</h2>\n"
				+ " <p></p>\n"
				+ " <h3>heading 3</h3>\n"
				+ " <p></p>\n"
				+ "</body>\n"
				+ "</html>";
		PrintWriter pw = new PrintWriter(file.getAbsolutePath());
		pw.println(fileContents);
		pw.close();
		cw = new ContentWriter(templateFilepath);
		Elements expected = new Elements();
		expected.add(new Element(Tag.valueOf("h1"), "").appendText("heading 1"));
		expected.add(new Element(Tag.valueOf("h2"), "").appendText("heading 2"));
		expected.add(new Element(Tag.valueOf("h3"), "").appendText("heading 3"));
		assertEquals(expected, cw.getHeadings());
	}
	
	/**
	 * tests if getHeadingsArray successfully gets a single heading
	 * @throws IOException
	 */
	@Test
	public void testGetHeadingsArray() throws IOException {
		String[] expected = {"heading"};
		assertArrayEquals(expected, cw.getHeadingsArray());
	}
	
	/**
	 * tests if getHeadingsArray successfully gets multiple headings
	 * @throws IOException
	 * @throws InvalidFileException
	 */
	@Test
	public void testGetHeadingsArray_Multiple() throws IOException, InvalidFileException {
		templateFilename = "test2.html";
		templateFilepath = folder.getRoot().getAbsolutePath() + File.separator + templateFilename;
		File file = folder.newFile(templateFilename);
		fileContents = "<!doctype html>\n"
				+ "<html>\n"
				+ "<body>\n"
				+ "<p></p>\n"
				+ " <h1>heading 1</h1>\n"
				+ " <p></p>\n"
				+ " <h2>heading 2</h2>\n"
				+ " <p></p>\n"
				+ " <h3>heading 3</h3>\n"
				+ " <p></p>\n"
				+ "</body>\n"
				+ "</html>";
		PrintWriter pw = new PrintWriter(file.getAbsolutePath());
		pw.println(fileContents);
		pw.close();
		cw = new ContentWriter(templateFilepath);
		String[] expected = {"heading 1", "heading 2", "heading 3"};
		assertArrayEquals(expected, cw.getHeadingsArray());
	}
	
	/**
	 * tests if checkNonuniqueHeadings does not throw a NonuniqueHeadingException if all 
	 * of the headings in the output file are unique
	 * @throws IOException
	 * @throws NonuniqueHeadingException
	 */
	@Test
	public void testCheckNonuniqueHeadings() throws IOException, NonuniqueHeadingException {
		cw.checkNonuniqueHeadings();
	}
	
	/**
	 * tests if checkNonuniqueHeadings does throw a NonuniqueHeadingException if not all 
	 * of the headings in the output file are unique
	 * @throws IOException
	 * @throws InvalidFileException
	 * @throws NonuniqueHeadingException
	 */
	@Test(expected = NonuniqueHeadingException.class)
	public void testCheckNonuniqueHeadings_Exception() throws IOException, InvalidFileException, NonuniqueHeadingException {
		templateFilename = "test2.html";
		templateFilepath = folder.getRoot().getAbsolutePath() + File.separator + templateFilename;
		File file = folder.newFile(templateFilename);
		fileContents = "<!doctype html>\n"
				+ "<html>\n"
				+ "<body>\n"
				+ "<p></p>\n"
				+ " <h1>heading</h1>\n"
				+ " <p></p>\n"
				+ " <h2>heading</h2>\n"
				+ "</body>\n"
				+ "</html>";
		PrintWriter pw = new PrintWriter(file.getAbsolutePath());
		pw.println(fileContents);
		pw.close();
		cw = new ContentWriter(templateFilepath);
		cw.checkNonuniqueHeadings();
	}
	
	/**
	 * tests if writeInitialContent successfully puts the specified content string in 
	 * the p section directly below the first body section, note that the expected file 
	 * contents are slightly altered from the template file contents because using JSoup 
	 * to parse the HTML file results in some slight alterations
	 * @throws IOException
	 * @throws InvalidFileException
	 */
	@Test
	public void testWriteInitialContent() throws IOException, InvalidFileException {
		cw = new ContentWriter(templateFilepath, outputFilepath);
		cw.writeInitialContent("this is test content");
		String expectedFileContents = "<!doctype html>\n"
				+ "<html>\n"
				+ " <head></head>\n"
				+ " <body> \n"
				+ "  <p>this is test content</p> \n"
				+ "  <h1>heading</h1> \n"
				+ "  <p></p>   \n"
				+ " </body>\n"
				+ "</html>";
		Scanner s = new Scanner(new File(outputFilepath));
		String actualFileContents = s.useDelimiter("\\Z").next();
		s.close();
		assertEquals(expectedFileContents, actualFileContents);
	}
	
	/**
	 * tests if writeInitialContent successfully creates a p section directly below the 
	 * first body section and puts the specified content string in that section if the 
	 * template does not contain a p section directly below the first body section, note 
	 * that the expected file contents are slightly altered from the template file contents
	 * because using JSoup to parse the HTML file results in some slight alterations
	 * @throws IOException
	 * @throws InvalidFileException
	 */
	@Test
	public void testWriteInitialContent_MissingFromTemplate() throws IOException, InvalidFileException {
		templateFilename = "test2.html";
		templateFilepath = folder.getRoot().getAbsolutePath() + File.separator + templateFilename;
		File file = folder.newFile(templateFilename);
		fileContents = "<!doctype html>\n"
				+ "<html>\n"
				+ "<body>\n"
				+ " <h1>heading</h1>\n"
				+ " <p></p>\n"
				+ "</body>\n"
				+ "</html>";
		PrintWriter pw = new PrintWriter(file.getAbsolutePath());
		pw.println(fileContents);
		pw.close();
		cw = new ContentWriter(templateFilepath, outputFilepath);
		cw.writeInitialContent("this is test content");
		String expectedFileContents = "<!doctype html>\n"
				+ "<html>\n"
				+ " <head></head>\n"
				+ " <body>\n"
				+ "  <p>this is test content</p> \n"
				+ "  <h1>heading</h1> \n"
				+ "  <p></p>   \n"
				+ " </body>\n"
				+ "</html>";
		Scanner s = new Scanner(new File(outputFilepath));
		String actualFileContents = s.useDelimiter("\\Z").next();
		s.close();
		assertEquals(expectedFileContents, actualFileContents);
	}
	
	/**
	 * tests if generateOutputFile generates the output file with the same contents as 
	 * the template file
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws FileNotFoundException
	 */
	@Test
	public void testGenerateOutputFile() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, FileNotFoundException {
		Method generateOutput = ContentWriter.class.getDeclaredMethod("generateOutputFile", String.class, String.class);
		generateOutput.setAccessible(true);
		generateOutput.invoke(ContentWriter.class, templateFilepath, outputFilepath);
		Scanner s = new Scanner(new File(outputFilepath));
		String actualFileContents = s.useDelimiter("\\Z").next();
		s.close();
		assertEquals(fileContents, actualFileContents);
	}
	
	/**
	 * tests if generateOutputFile throws an exception if the specified template cannot 
	 * be found
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@Test(expected = InvocationTargetException.class)
	public void testGenerateOutputFile_TemplateFileNotFound() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method generateOutput = ContentWriter.class.getDeclaredMethod("generateOutputFile", String.class, String.class);
		generateOutput.setAccessible(true);
		generateOutput.invoke(ContentWriter.class, "", outputFilepath);
	}
	
	/**
	 * tests if writeContent successfully puts the specified content string in the p 
	 * section directly below the specified h1 section, note that the expected file 
	 * contents are slightly altered from the template file contents because using 
	 * JSoup to parse the HTML file results in some slight alterations
	 * @throws IOException
	 * @throws InvalidFileException
	 */
	@Test
	public void testWriteContent() throws IOException, InvalidFileException  {
		cw = new ContentWriter(templateFilepath, outputFilepath);
		cw.writeContent("heading", "this is test content");
		String expectedFileContents = "<!doctype html>\n"
				+ "<html>\n"
				+ " <head></head>\n"
				+ " <body> \n"
				+ "  <p></p> \n"
				+ "  <h1>heading</h1> \n"
				+ "  <p>this is test content</p>   \n"
				+ " </body>\n"
				+ "</html>";
		Scanner s = new Scanner(new File(outputFilepath));
		String actualFileContents = s.useDelimiter("\\Z").next();
		s.close();
		assertEquals(expectedFileContents, actualFileContents);
	}
	
	/**
	 * tests if writeContent successfully puts the specified content string in the p 
	 * section directly below the specified h3 headings, note that the expected file 
	 * contents are slightly altered from the template file contents because using 
	 * JSoup to parse the HTML file results in some slight alterations
	 * @throws IOException
	 * @throws InvalidFileException
	 */
	@Test
	public void testWriteContent_LowerHeading() throws IOException, InvalidFileException {
		templateFilename = "test2.html";
		templateFilepath = folder.getRoot().getAbsolutePath() + File.separator + templateFilename;
		File file = folder.newFile(templateFilename);
		fileContents = "<!doctype html>\n"
				+ "<html>\n"
				+ "<body>\n"
				+ "<p></p>\n"
				+ " <h3>heading</h3>\n"
				+ " <p></p>\n"
				+ "</body>\n"
				+ "</html>";
		PrintWriter pw = new PrintWriter(file.getAbsolutePath());
		pw.println(fileContents);
		pw.close();
		cw = new ContentWriter(templateFilepath, outputFilepath);
		cw.writeContent("heading", "this is test content");
		String expectedFileContents = "<!doctype html>\n"
				+ "<html>\n"
				+ " <head></head>\n"
				+ " <body> \n"
				+ "  <p></p> \n"
				+ "  <h3>heading</h3> \n"
				+ "  <p>this is test content</p>   \n"
				+ " </body>\n"
				+ "</html>";
		Scanner s = new Scanner(new File(outputFilepath));
		String actualFileContents = s.useDelimiter("\\Z").next();
		s.close();
		assertEquals(expectedFileContents, actualFileContents);
	}

}
