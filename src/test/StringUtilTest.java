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
	 * tests that countTabs returns 0 if there are no tabs in the string
	 */
	@Test
	public void testCountTabs_NoTabs() {
		assertEquals(0, StringUtil.countTabs(""));
	}
	
	/**
	 * tests that countTabs successfully counts the number of tab characters in the 
	 * string if the tab characters are all at the start of the string, this case is 
	 * important because it is the intended use case for lists in user format
	 */
	@Test
	public void testCountTabs_TabsAtStart() {
		assertEquals(2, StringUtil.countTabs("\t\ttest"));
	}
	
	/**
	 * tests that countTabs successfully counts the number of tab characters in the 
	 * string if the tab characters are all separated from each other
	 */
	@Test
	public void testCountTabs_SeparatedTabs() {
		assertEquals(3, StringUtil.countTabs("\ttest\ttest\t"));
	}
	
	/**
	 * tests that countTabs successfully counts the number of tab characters in the 
	 * string if the tab characters are all at the end of the string
	 */
	@Test
	public void testCountTabs_TabsAtEnd() {
		assertEquals(2, StringUtil.countTabs("test\t\t"));
	}
	
	/**
	 * tests that stripListElement returns the input string if that string does not 
	 * start match the regex "\t*--.*"
	 */
	@Test
	public void testStripListElement_NoFormatting() {
		assertEquals("test", StringUtil.stripListElement("test"));
	}
	
	/**
	 * tests that stripListElement removes the "--" characters only if there are no tab 
	 * characters
	 */
	@Test
	public void testStripListElement_NoTab() {
		assertEquals("test", StringUtil.stripListElement("--test"));
	}
	
	/**
	 * tests that stripListElements successfully removes a single tab character as well 
	 * as the "--" characters
	 */
	@Test
	public void testStripListElement_SingleTab() {
		assertEquals("test", StringUtil.stripListElement("\t--test"));
	}
	
	/**
	 * tests that stripListElements successfully removes multiple tab characters as well 
	 * as the "--" characters
	 */
	@Test
	public void testStripListElement_MultipleTabs() {
		assertEquals("test", StringUtil.stripListElement("\t\t--test"));
	}
	
}
