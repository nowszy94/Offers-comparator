package pl.endproject.offerscomparator.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.endproject.offerscomparator.domain.Product;
import pl.endproject.offerscomparator.infrastructure.printPDFeature.PdfService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;

@Controller
public class PdfController {

    private final PdfService pdfService;
    private final ProductController productController;
    @Autowired
    private ServletContext servletContext;

    public PdfController(PdfService pdfService, ProductController productController) {
        this.pdfService = pdfService;
        this.productController = productController;
    }

    @RequestMapping("/printPdf")
    @ResponseBody
    public void getPdfFile(HttpServletRequest request, HttpServletResponse response) {
        List<Product>  products = productController.getProducts();
        pdfService.createPdf(products, servletContext, request, response);
        String fullPath = request.getServletContext().getRealPath("/resources/templates/getAll" + ".pdf");
        fileDownload(fullPath, response, "oferty.pdf");
    }

    private void fileDownload(String fullPath, HttpServletResponse response, String fileName) {
        File file = new File(fullPath);
        final int BUFFER_SIZE = 4096;
        if (file.exists()) {
            try {
                FileInputStream fis = new FileInputStream(file);
                String mimeType = servletContext.getMimeType(fullPath);
                response.setContentType(mimeType);
                response.setHeader("content-disposition", "attachment; filename=" + fileName);
                OutputStream os = response.getOutputStream();
                byte[] buffer = new byte[BUFFER_SIZE];
                int bytesRead = -1;
                while ((bytesRead = fis.read(buffer)) != -1) {
                    os.write(buffer, 0, bytesRead);
                }
                fis.close();
                os.close();
                file.delete();


            } catch (Exception e) {
                e.getMessage();
            }
        }
    }
}
