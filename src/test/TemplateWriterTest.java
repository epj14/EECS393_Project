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
	// the TemplateWriter that will be used to run the tests
	private TemplateWriter tw;
	
	/**
	 * the TemporaryFolder that will contain the template file, this file will be removed 
	 * after each test so it does not persist throughout other tests
	 */
	@Rule
	public TemporaryFolder folder = new TemporaryFolder();
	
	/**
	 * initializes the test configuration by creating the file that the template will be 
	 * written to and instantiating the TemplateWriter that will be used to run the tests
	 * @throws IOException
	 */
	@Before
	public void setUp() throws IOException {
		templateFilename = "test.html";
		templateFilepath = folder.getRoot().getAbsolutePath() + File.separator + templateFilename;
		folder.newFile(templateFilename);
		tw = new TemplateWriter(templateFilepath);
	}
	
	/**
	 * tests if getTemplateFilename successfully gets the template filename
	 */
	@Test
	public void testGetTemplateFilename() {
		assertEquals(templateFilepath, tw.getTemplateFilename());
	}
	
	/**
	 * tests if setTemplateFilename successfully sets the template filename
	 */
	@Test
	public void testSetTemplateFilename() {
		tw.setTemplateFilename("newtemplatefilename.html");
		assertEquals("newtemplatefilename.html", tw.getTemplateFilename());
	}
	
	/**
	 * tests if setTemplateFilename successfully sets the template filename with the 
	 * correct extension if no extension was specified
	 */
	@Test
	public void testSetTemplateFilename_NoExtension() {
		tw.setTemplateFilename("newtemplatefilename");
		assertEquals("newtemplatefilename.html", tw.getTemplateFilename());
	}
	
	/**
	 * tests if getContentList returns an empty ArrayList<String> if no template content 
	 * has been added to contentList
	 */
	@Test
	public void testGetContentList_Empty() {
		assertEquals(new ArrayList<String>(), tw.getContentList());
	}
	
	/**
	 * tests if getContentList returns an ArrayList<String> containing the formatted 
	 * heading that was appended to contentList in the form <h1>heading</h1><p></p>
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
	 * tests if generateTemplateFile successfully generates a file named templateFilename
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
	 * tests if generateHTML returns a formatted version of the heading string provided 
	 * in the form <h1>heading</h1><p></p>
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
	 * tests if generateHTML returns null if the empty string is provided as the heading
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
	 * tests if generateHTML removes HTML headings included in the parameter heading and 
	 * returns null if there is no content in those headings
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@Test
	public void testGenerateHTML_Heading() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method generateHTML = TemplateWriter.class.getDeclaredMethod("generateHTML", String.class);
		generateHTML.setAccessible(true);
		String actualHTML = (String) generateHTML.invoke(TemplateWriter.class, "<h1></h1>");
		String expectedHTML = null;
		assertEquals(expectedHTML, actualHTML);
	}
	
	/**
	 * tests if generateHTML removes HTML headings included in the parameter heading and 
	 * returns a formatted version of the heading string provided with HTML headings 
	 * removed in the form <h1>heading with HTML headings removed</h1><p></p>
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@Test
	public void testGenerateHTML_HeadingContent() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method generateHTML = TemplateWriter.class.getDeclaredMethod("generateHTML", String.class);
		generateHTML.setAccessible(true);
		String actualHTML = (String) generateHTML.invoke(TemplateWriter.class, "<h1>heading</h1>");
		String expectedHTML = "<h1>heading</h1><p></p>";
		assertEquals(expectedHTML, actualHTML);
	}
	
	/**
	 * tests if generateHTML removes all levels of HTML headings included in the 
	 * parameter heading and returns null if there is no content in those headings
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@Test
	public void testGenerateHTML_HeadingLevels() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method generateHTML = TemplateWriter.class.getDeclaredMethod("generateHTML", String.class);
		generateHTML.setAccessible(true);
		String actualHTML = (String) generateHTML.invoke(TemplateWriter.class, "<h1></h1><h2></h2><h3></h3><h4></h4><h5></h5><h6></h6>");
		String expectedHTML = null;
		assertEquals(expectedHTML, actualHTML);
	}
	
	/**
	 * tests if generateHTML removes all HTML headings included in the parameter heading 
	 * and returns a formatted version of the heading string provided with HTML headings 
	 * removed in the form <h1>heading with HTML headings removed</h1><p></p>
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@Test
	public void testGenerateHTML_MultipleHeadings() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method generateHTML = TemplateWriter.class.getDeclaredMethod("generateHTML", String.class);
		generateHTML.setAccessible(true);
		String actualHTML = (String) generateHTML.invoke(TemplateWriter.class, "<h1>heading 1</h1><h1>heading 2</h1>");
		String expectedHTML = "<h1>heading 1heading 2</h1><p></p>";
		assertEquals(expectedHTML, actualHTML);
	}
	
	/**
	 * tests if generateHTML removes unbalanced HTML headings included in the parameter 
	 * heading and returns a formatted version of the heading string provided with 
	 * unbalanced HTML headings removed in the form <h1>heading with unbalanced HTML 
	 * headings removed</h1><p></p>
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@Test
	public void testGenerateHTML_UnbalancedHeading() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method generateHTML = TemplateWriter.class.getDeclaredMethod("generateHTML", String.class);
		generateHTML.setAccessible(true);
		String actualHTML = (String) generateHTML.invoke(TemplateWriter.class, "<h1>heading");
		String expectedHTML = "<h1>heading</h1><p></p>";
		assertEquals(expectedHTML, actualHTML);
	}
	
	/**
	 * tests if generateHTML removes all unbalanced HTML headings included in the 
	 * parameter heading and returns a formatted version of the heading string provided 
	 * with unbalanced HTML headings removed in the form <h1>heading with unbalanced 
	 * HTML headings removed</h1><p></p>
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@Test
	public void testGenerateHTML_MultipleUnbalancedHeadings() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method generateHTML = TemplateWriter.class.getDeclaredMethod("generateHTML", String.class);
		generateHTML.setAccessible(true);
		String actualHTML = (String) generateHTML.invoke(TemplateWriter.class, "<h1><h2>heading");
		String expectedHTML = "<h1>heading</h1><p></p>";
		assertEquals(expectedHTML, actualHTML);
	}
	
	/**
	 * tests if appendTemplateContent successfully appends a formatted version of the 
	 * headings string provided in the form <h1>heading</h1><p></p> to contentList
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
	 * tests if appendTemplateContent throws a NonuniqueHeadingException if the same 
	 * heading is attempted to be appended to contentList multiple times
	 * @throws NonuniqueHeadingException
	 */
	@Test(expected = NonuniqueHeadingException.class)
	public void testAppendTemplateContent_NonuniqueHeading() throws NonuniqueHeadingException {
		tw.appendTemplateContent("heading");
		tw.appendTemplateContent("heading");
	}
	
	/**
	 * tests if writeTemplateContent successfully writes an HTML file with the specified 
	 * heading element included
	 * @throws NonuniqueHeadingException
	 * @throws IOException
	 */
	@Test
	public void testWriteTemplateContent() throws NonuniqueHeadingException, IOException {
		tw.appendTemplateContent("heading");
		tw.writeTemplateContent();
		String expectedFileContents = "<!DOCTYPE html>\n<html><head></head><body><p></p><h1>heading</h1><p></p></body></html>";
		Scanner s = new Scanner(new File(templateFilepath));
		String actualFileContents = s.useDelimiter("\\Z").next();
		s.close();
		assertEquals(expectedFileContents, actualFileContents);
	}
	
	/**
	 * tests if writeTemplateContent successfully writes an HTML file with the multiple 
	 * specified heading elements included
	 * @throws NonuniqueHeadingException
	 * @throws IOException
	 */
	@Test
	public void testWriteTemplateContent_Multiple() throws NonuniqueHeadingException, IOException {
		tw.appendTemplateContent("heading 1");
		tw.appendTemplateContent("heading 2");
		tw.writeTemplateContent();
		String expectedFileContents = "<!DOCTYPE html>\n<html><head></head><body><p></p><h1>heading 1</h1><p></p><h1>heading 2</h1><p></p></body></html>";
		Scanner s = new Scanner(new File(templateFilepath));
		String actualFileContents = s.useDelimiter("\\Z").next();
		s.close();
		assertEquals(expectedFileContents, actualFileContents);
	}
	
	/**
	 * tests if writeTemplateContent successfully writes an HTML file that does not 
	 * contain any heading elements if contentList is empty
	 * @throws IOException
	 */
	@Test
	public void testWriteTemplateContent_NoContent() throws IOException {
		tw.writeTemplateContent();
		String expectedFileContents = "<!DOCTYPE html>\n<html><head></head><body><p></p></body></html>";
		Scanner s = new Scanner(new File(templateFilepath));
		String actualFileContents = s.useDelimiter("\\Z").next();
		s.close();
		assertEquals(expectedFileContents, actualFileContents);
	}

}
