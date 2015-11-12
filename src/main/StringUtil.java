package main;

import java.io.File;

/**
 * a class that contains string utility methods
 */
public class StringUtil {
	
	/**
	 * prepends "output" to a string, if the string is a file path that contains 
	 * name-separators then "output" will be prepended to the last element of the 
	 * file path
	 * @param s the string that "output" will be prepended to
	 * @return a string that is the same as s, but with "output" prepended to it
	 */
	public static String prependOutput(String s) {
		StringBuilder sb = new StringBuilder();
		String[] split = s.split(File.separator);
		for (int i = 0; i < split.length - 1; i++) {
			sb.append(split[i] + File.separator);
		}
		sb.append("output" + split[split.length - 1]);
		return sb.toString();
	}
	
	/**
	 * replaces everything after the last "." in a string with a specified extension if 
	 * the string contains ".", or appends the specified extension to the string if the 
	 * string does not contain "." 
	 * @param s the string where everything after its last "." will be replaced by 
	 * extension if it contains ".", or that the specified extension will be appended to 
	 * if it does not contain "."
	 * @param extension the extension that will replace everything after the last "." in 
	 * s if s contains ".", or that will be appended to s if s does not contain "."
	 * @return a string that is the same as s, but with everything after the last "." in 
	 * the string replaced by extension if s contained ".", or with extension appended to 
	 * the end of s if s does not contain "."
	 */
	public static String replaceExtension(String s, String extension) {
		if (extension.contains(".")) {
			extension = extension.substring(1);
		}
		StringBuilder sb = new StringBuilder();
		String[] split = s.split("\\.");
		sb.append(split[0] + ".");
		for (int i = 1; i < split.length - 1; i++) {
			sb.append(split[i] + ".");
		}
		sb.append(extension);
		return sb.toString();
	}
	
	/**
	 * strips "<p>" from the beginning of a string and "</p>" from the end of a string if 
	 * that string starts with "<p>" and ends with "</p>"
	 * @param s the string that will have "<p>" stripped from its start and "</p>" 
	 * stripped from its end if they are both present
	 * @return s with "<p>" stripped from its start and "</p>" stripped from its end if 
	 * s started with "<p>" and ended with "</p>", or s if s did not start with "<p>" or 
	 * end with "</p>" 
	 */
	public static String stripP(String s) {
		if (s.startsWith("<p>") && s.endsWith("</p>")) {
			return s.substring(3, s.length() - 4);
		} else {
			return s;
		}
	}
	
}
