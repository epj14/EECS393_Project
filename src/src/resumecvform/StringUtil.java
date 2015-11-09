
package resumecvform;
 
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
			sb.append(split[i] + "/");
		}
		sb.append("output" + split[split.length - 1]);
		return sb.toString();
	}

}