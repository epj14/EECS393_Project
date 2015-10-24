
import java.io.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;

import com.itextpdf.tool.xml.*;

//


public class pdfOutput 
{
    public static void main(String[] args) throws IOException, DocumentException{
        
        File input = new File(args[0]);
        File output = new File(args[1]);
        pdfOutput(input,output);
        
    }
    
    public static void pdfOutput(File inFile, File outFile) throws IOException, DocumentException {
        
        Document doc = new Document();

		//Writes to pdf
        PdfWriter wrt = PdfWriter.getInstance(doc, new FileOutputStream(outFile));
 
        doc.open();
        
        //Parses HTML
        XMLWorkerHelper.getInstance().parseXHtml(wrt, doc, new FileInputStream(inFile));
        
        doc.close();
        
    }
        

}
