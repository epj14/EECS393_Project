package test;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Test;

import main.StringUtil;

/**
 * a class that tests StringUtil
 */
public class StringUtilTest {

	/**
	 * tests that prependOutput prepends "output" to a simple string with no File.separator
	 */
	@Test
	public void testPrependOutput_Simple() {
		assertEquals("outputtest", StringUtil.prependOutput("test"));
	}
	
	/**
	 * tests that prependOutput prepends "output" to a string that contains a period
	 */
	@Test
	public void testPrependOutput_Extension() {
		assertEquals("outputtest.a", StringUtil.prependOutput("test.a"));
	}
	
	/**
	 * tests that prependOutput prepends "output" to the last element of the file path 
	 * string that contains File.separator
	 */
	@Test
	public void testPrependOutput_Separator() {
		String test = File.separator + "a" + File.separator + "b" + File.separator + "c.d";
		String expected = File.separator + "a" + File.separator + "b" + File.separator + "outputc.d";
		assertEquals(expected, StringUtil.prependOutput(test));
	}
	
	/**
	 * tests that prependOutput prepends "output" to the last element of the file path 
	 * string that contains multiple consecutive File.separator
	 */
	@Test
	public void testPrependOutput_MultipleSeparators() {
		String test = File.separator + "a" + File.separator + File.separator + "b.c";
		String expected = File.separator + "a" + File.separator + File.separator + "outputb.c";
		assertEquals(expected, StringUtil.prependOutput(test));
	}
	
	/**
	 * tests that replaceExtension replaces everything after the last "." in a string 
	 * with the specified extension
	 */
	@Test
	public void testReplaceExtension_Simple() {
		assertEquals("a.c", StringUtil.replaceExtension("a.b", "c"));
	}
	
	/**
	 * tests that replaceExtension replaces everything after the last "." in a string 
	 * with the specified extension if the specified extension contains a leading "."
	 */
	@Test
	public void testReplaceExtension_IncludeLeadingPeriod() {
		assertEquals("a.c", StringUtil.replaceExtension("a.b", ".c"));
	}
	
	/**
	 * tests that replaceExtension appends the specified extension to a string if that 
	 * string does not contain any "."
	 */
	@Test
	public void testReplaceExtension_NoExtension() {
		assertEquals("a.b", StringUtil.replaceExtension("a", "b"));
	}
	
	/**
	 * tests that replaceExtension replaces everything after the last "." in a string 
	 * with the specified extension if the string contains multiple "."
	 */
	@Test
	public void testReplaceExtension_MultiplePeriods() {
		assertEquals("a.b.c.e", StringUtil.replaceExtension("a.b.c.d", "e"));
	}
	
	/**
	 * tests that stripP strips "<p>" from the beginning of a string and "</p>" from the 
	 * end of a string if that string starts with "<p>" and ends with "</p>"
	 */
	@Test
	public void testStripP() {
		assertEquals("content", StringUtil.stripP("<p>content</p>"));
	}
	
	/**
	 * tests that stripP returns the given string if it does not start with "<p>" or end 
	 * with "</p>"
	 */
	@Test
	public void testStripP_NoP() {
		assertEquals("content", StringUtil.stripP("content"));
	}
	
	/**
	 * tests that stripP returns the given string if it starts with "<p>" but does not 
	 * end with "</p>"
	 */
	@Test
	public void testStripP_Start() {
		assertEquals("<p>content", StringUtil.stripP("<p>content"));
	}
	
	/**
	 * tests that stripP returns the given string if it ends with "</p>" but does not 
	 * start with "<p>"
	 */
	@Test
	public void testStripP_End() {
		assertEquals("content</p>", StringUtil.stripP("content</p>"));
	}
	
	/**
	 * tests that removeH removes HTML headings
	 */
	@Test
	public void testRemoveH() {
		assertEquals("", StringUtil.removeH("<h1></h1>"));
	}
	
	/**
	 * tests that removeH removes HTML headings and leaves content untouched
	 */
	@Test
	public void testRemoveH_Content() {
		assertEquals("content", StringUtil.removeH("<h1>content</h1>"));
	}
	
	/**
	 * tests that removeH removes all levels of HTML headings
	 */
	@Test
	public void testRemoveH_HeadingLevels() {
		assertEquals("", StringUtil.removeH("<h1></h1><h2></h2><h3></h3><h4></h4><h5></h5><h6></h6>"));
	}
	
	/**
	 * tests that removeH removes multiple HTML headings and leaves the content from each 
	 * heading untouched
	 */
	@Test
	public void testRemoveH_Multiple() {
		assertEquals("content 1content 2", StringUtil.removeH("<h1>content 1</h1><h1>content 2</h1>"));
	}
	
	/**
	 * tests that removeH removes unbalanced HTML headings
	 */
	@Test
	public void testRemoveH_Unbalanced() {
		assertEquals("content", StringUtil.removeH("<h1>content"));
	}
	
	/**
	 * tests that removeH removes multiple unbalanced HTML headings
	 */
	@Test
	public void testRemoveH_MultipleUnbalanced() {
		assertEquals("content", StringUtil.removeH("<h1><h2>content"));
	}
	
}
