package pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;


public void generatePDF(String filePath, String content) {
    Document document = new Document();
    try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
        PdfWriter.getInstance(document, fileOutputStream);
        
        document.open();
        document.add(new Paragraph(content));
        document.close();
        
        System.out.println("PDF generated successfully at: " + filePath);
    } catch (DocumentException | Exception e) {
        e.printStackTrace();
    }
}


