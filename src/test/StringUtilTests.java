package test;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Test;

import main.StringUtil;

/**
 * a class that tests StringUtil
 */
public class StringUtilTests {

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
	
}
