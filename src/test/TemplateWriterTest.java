package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import main.NonuniqueHeadingException;
import main.TemplateWriter;

/**
 * a class that tests TemplateWriter
 */
public class TemplateWriterTest {

	// the filename of the template file
	private String templateFilename;
	// the file path of the template file
	private String templateFilepath;
	// the ArrayList<String> used to store the content that will be added to the template file
	private ArrayList<String> contentList;
	// the TemplateWriter that will be used to run the tests
	private TemplateWriter tw;
	
	/**
	 * the TemporaryFolder that will contain the template file, this file will be removed 
	 * after each test so it does not persist throughout other tests
	 */
	@Rule
	public TemporaryFolder folder = new TemporaryFolder();
	
	/**
	 * 
	 * @throws IOException
	 */
	@Before
	public void setUp() throws IOException {
		templateFilename = "test.html";
		templateFilepath = folder.getRoot().getAbsolutePath() + File.separator + templateFilename;
		File file = folder.newFile(templateFilename);
		tw = new TemplateWriter(templateFilepath);
	}
	
	/**
	 * 
	 */
	@Test
	public void testGetTemplateFilename() {
		assertEquals(templateFilepath, tw.getTemplateFilename());
	}
	
	/**
	 * 
	 */
	@Test
	public void testSetTemplateFilename() {
		tw.setTemplateFilename("newtemplatefilename.html");
		assertEquals("newtemplatefilename.html", tw.getTemplateFilename());
	}
	
	/**
	 * 
	 */
	@Test
	public void testSetTemplateFilename_NoExtension() {
		tw.setTemplateFilename("newtemplatefilename");
		assertEquals("newtemplatefilename.html", tw.getTemplateFilename());
	}
	
	/**
	 * 
	 */
	@Test
	public void testGetContentList_Empty() {
		assertEquals(new ArrayList<String>(), tw.getContentList());
	}
	
	/**
	 * 
	 * @throws NonuniqueHeadingException
	 */
	@Test
	public void testGetContentList() throws NonuniqueHeadingException {
		ArrayList<String> expectedContentList = new ArrayList<String>();
		expectedContentList.add("<h1>heading</h1><p></p>");
		tw.appendTemplateContent("heading");
		assertEquals(expectedContentList, tw.getContentList());
	}
	
	/**
	 * 
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@Test
	public void testGenerateTemplateFile() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method generateTemplateFile = TemplateWriter.class.getDeclaredMethod("generateTemplateFile", String.class);
		generateTemplateFile.setAccessible(true);
		generateTemplateFile.invoke(TemplateWriter.class, templateFilepath);
		File template = new File(templateFilepath);
		assertTrue(template.exists());
	}
	
	/**
	 * 
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@Test
	public void testGenerateHTML() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method generateHTML = TemplateWriter.class.getDeclaredMethod("generateHTML", String.class);
		generateHTML.setAccessible(true);
		String actualHTML = (String) generateHTML.invoke(TemplateWriter.class, "heading");
		String expectedHTML = "<h1>heading</h1><p></p>";
		assertEquals(expectedHTML, actualHTML);
	}
	
	/**
	 * 
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@Test
	public void testGenerateHTML_Empty() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method generateHTML = TemplateWriter.class.getDeclaredMethod("generateHTML", String.class);
		generateHTML.setAccessible(true);
		String actualHTML = (String) generateHTML.invoke(TemplateWriter.class, "");
		String expectedHTML = null;
		assertEquals(expectedHTML, actualHTML);
	}
	
	/**
	 * 
	 * @throws NonuniqueHeadingException
	 */
	@Test
	public void testAppendTemplateContent() throws NonuniqueHeadingException {
		ArrayList<String> expectedContentList = new ArrayList<String>();
		expectedContentList.add("<h1>heading</h1><p></p>");
		tw.appendTemplateContent("heading");
		assertEquals(expectedContentList, tw.getContentList());
	}
	
	/**
	 * 
	 * @throws NonuniqueHeadingException
	 */
	@Test(expected = NonuniqueHeadingException.class)
	public void testAppendTemplateContent_NonuniqueHeading() throws NonuniqueHeadingException {
		tw.appendTemplateContent("heading");
		tw.appendTemplateContent("heading");
	}
	
	/**
	 * 
	 * @throws NonuniqueHeadingException
	 * @throws IOException
	 */
	@Test
	public void testWriteTemplateContent() throws NonuniqueHeadingException, IOException {
		tw.appendTemplateContent("heading");
		tw.writeTemplateContent();
		String expectedFileContents = "<!DOCTYPE html>\n<html><head></head><body><h1>heading</h1><p></p></body></html>";
		Scanner s = new Scanner(new File(templateFilepath));
		String actualFileContents = s.useDelimiter("\\Z").next();
		s.close();
		assertEquals(expectedFileContents, actualFileContents);
	}
	
	/**
	 * 
	 * @throws NonuniqueHeadingException
	 * @throws IOException
	 */
	@Test
	public void testWriteTemplateContent_Multiple() throws NonuniqueHeadingException, IOException {
		tw.appendTemplateContent("heading 1");
		tw.appendTemplateContent("heading 2");
		tw.writeTemplateContent();
		String expectedFileContents = "<!DOCTYPE html>\n<html><head></head><body><h1>heading 1</h1><p></p><h1>heading 2</h1><p></p></body></html>";
		Scanner s = new Scanner(new File(templateFilepath));
		String actualFileContents = s.useDelimiter("\\Z").next();
		s.close();
		assertEquals(expectedFileContents, actualFileContents);
	}
	
	/**
	 * 
	 * @throws IOException
	 */
	@Test
	public void testWriteTemplateContent_NoContent() throws IOException {
		tw.writeTemplateContent();
		String expectedFileContents = "<!DOCTYPE html>\n<html><head></head><body></body></html>";
		Scanner s = new Scanner(new File(templateFilepath));
		String actualFileContents = s.useDelimiter("\\Z").next();
		s.close();
		assertEquals(expectedFileContents, actualFileContents);
	}

}
