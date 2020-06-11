package pl.endproject.offerscomparator.infrastructure.printPDFeature;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;
import pl.endproject.offerscomparator.domain.Product;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;

import java.util.List;

@Service
public class PdfService {


    public boolean createPdf(List<Product> products, ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) {
        Document document = new Document(PageSize.A4, 15, 15, 45, 30);
        try {
            String filepath = servletContext.getRealPath("/resources/templates");
            File file = new File(filepath);
            boolean exists = new File(filepath).exists();
            if (!exists) {
                new File(filepath).mkdirs();
            }
            PdfWriter.getInstance(document, new FileOutputStream(file + "/" + "getAll" + ".pdf"));
            document.open();

            Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);

            Paragraph paragraph = new Paragraph("All products", font);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            paragraph.setIndentationLeft(50);
            paragraph.setIndentationRight(50);
            paragraph.setSpacingAfter(10);
            document.add(paragraph);

            PdfPTable pdfPTable = new PdfPTable(4);
            pdfPTable.setWidthPercentage(100);
            pdfPTable.setSpacingBefore(10);
            pdfPTable.setSpacingAfter(10);
            Font tableHeader = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, BaseColor.BLACK);
            Font tableBody = FontFactory.getFont(FontFactory.COURIER, 10, BaseColor.BLACK);

            float[] columnWidths = {2f, 2f, 2f, 2f};
            pdfPTable.setWidths(columnWidths);


            /*CELLS CREATING*/
            /*TITLES*/
            PdfPCell title = new PdfPCell(new Phrase("Title"));
            title.setBorderColor(BaseColor.BLACK);
            title.setPaddingLeft(10);
            title.setHorizontalAlignment(Element.ALIGN_CENTER);
            title.setVerticalAlignment(Element.ALIGN_CENTER);
            title.setBackgroundColor(BaseColor.GRAY);

            pdfPTable.addCell(title);
            /*Prices*/
            PdfPCell price = new PdfPCell(new Phrase("Price"));
            price.setBorderColor(BaseColor.BLACK);
            price.setPaddingLeft(10);
            price.setHorizontalAlignment(Element.ALIGN_CENTER);
            price.setVerticalAlignment(Element.ALIGN_CENTER);
            price.setBackgroundColor(BaseColor.GRAY);

            pdfPTable.addCell(price);
            /*Sources*/
            PdfPCell source = new PdfPCell(new Phrase("Source"));
            source.setBorderColor(BaseColor.BLACK);
            source.setPaddingLeft(10);
            source.setHorizontalAlignment(Element.ALIGN_CENTER);
            source.setVerticalAlignment(Element.ALIGN_CENTER);
            source.setBackgroundColor(BaseColor.GRAY);

            pdfPTable.addCell(source);
            /*Actions*/
            PdfPCell action = new PdfPCell(new Phrase("Action"));
            action.setBorderColor(BaseColor.BLACK);
            action.setPaddingLeft(10);
            action.setHorizontalAlignment(Element.ALIGN_CENTER);
            action.setVerticalAlignment(Element.ALIGN_CENTER);
            action.setBackgroundColor(BaseColor.GRAY);

            pdfPTable.addCell(action);

            for (Product product : products) {
                PdfPCell titleValue = new PdfPCell(new Phrase(product.getName()));
                titleValue.setBorderColor(BaseColor.BLACK);
                titleValue.setPaddingLeft(10);
                titleValue.setHorizontalAlignment(Element.ALIGN_CENTER);
                titleValue.setVerticalAlignment(Element.ALIGN_CENTER);
                titleValue.setBackgroundColor(BaseColor.WHITE);
                pdfPTable.addCell(titleValue);

                PdfPCell priceValue = new PdfPCell(new Phrase(String.valueOf(product.getPrice())));
                priceValue.setBorderColor(BaseColor.BLACK);
                priceValue.setPaddingLeft(10);
                priceValue.setHorizontalAlignment(Element.ALIGN_CENTER);
                priceValue.setVerticalAlignment(Element.ALIGN_CENTER);
                priceValue.setBackgroundColor(BaseColor.WHITE);
                pdfPTable.addCell(priceValue);

                PdfPCell sourceValue = new PdfPCell(new Phrase(product.getSource().toString()));
                sourceValue.setBorderColor(BaseColor.BLACK);
                sourceValue.setPaddingLeft(10);
                sourceValue.setHorizontalAlignment(Element.ALIGN_CENTER);
                sourceValue.setVerticalAlignment(Element.ALIGN_CENTER);
                sourceValue.setBackgroundColor(BaseColor.WHITE);
                pdfPTable.addCell(sourceValue);

                PdfPCell actionValue = new PdfPCell(new Phrase(product.getUrl()));
                actionValue.setBorderColor(BaseColor.BLACK);
                actionValue.setPaddingLeft(10);
                actionValue.setHorizontalAlignment(Element.ALIGN_CENTER);
                actionValue.setVerticalAlignment(Element.ALIGN_CENTER);
                actionValue.setBackgroundColor(BaseColor.WHITE);
                pdfPTable.addCell(actionValue);


            }
            document.add(pdfPTable);
            document.close();
            return true;


        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
