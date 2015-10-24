
import java.io.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;

import com.itextpdf.tool.xml.*;

//


public class pdfOutput 
{
    public static void main(String[] args) throws IOException, DocumentException{
        
        File input = new File("/Users/Cameron/Desktop/Indian Creek Massacre and Captivity of Hall Girls, by Charles M. Scanlan. -- a Project Gutenberg eBook.html");
        File output = new File("/Users/Cameron/Desktop/newPdf.pdf");
        pdfOutput(input,output);
        
    }
    
    public static void pdfOutput(File inFile, File outFile) throws IOException, DocumentException {
        
        Document doc = new Document();
        

        PdfWriter wrt = PdfWriter.getInstance(doc, new FileOutputStream(outFile));
 
        doc.open();
        
        XMLWorkerHelper.getInstance().parseXHtml(wrt, doc, new FileInputStream(inFile));
        
        doc.close();
        
    }
        

}
