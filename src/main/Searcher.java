package main;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;

/**
 * a class that is used to perform searches for text in PDF files within a directory
 */
public class Searcher {
	
	// the directory where the search will be executed
	private String directory;
	
	/**
	 * constructor for Searcher
	 * @param directory the directory where the search will be executed
	 */
	public Searcher(String directory) {
		this.directory = directory;
	}
	
	/**
	 * gets the directory where the search will be executed
	 * @return the directory where the search will be executed
	 */
	public String getDirectory() {
		return directory;
	}
	
	/**
	 * sets the directory where the search will be executed
	 * @param directory the directory where the search will be executed to set
	 */
	public void setDirectory(String directory) {
		this.directory = directory;
	}
	
	/**
	 * executes a search for a specified query in the directory where the search will be 
	 * executed
	 * @param query the text that will be searched for
	 * @return an ArrayList<String> containing the file paths of all the files that 
	 * contained text matched the query text
	 * @throws IOException if an I/O error occurs
	 */
	public ArrayList<String> search(String query) throws IOException {
		ArrayList<String> result = new ArrayList<String>();
		File d = new File(directory);
		File[] files = d.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith(".pdf");
			}
		});
		for (File f : files) {
			PdfReader reader = new PdfReader(f.getAbsolutePath());
			PdfReaderContentParser parser = new PdfReaderContentParser(reader);
			TextExtractionStrategy strategy;
			for (int i = 1; i <= reader.getNumberOfPages(); i++) {
				try {
					strategy = parser.processContent(i,  new SimpleTextExtractionStrategy());
					if (strategy.getResultantText().contains(query)) {
						result.add(f.getAbsolutePath());
					}
				} catch (IOException e) {
					//ignore if PDF text cannot be reads
				}
			}
		}
		return result;
	}
	
}
