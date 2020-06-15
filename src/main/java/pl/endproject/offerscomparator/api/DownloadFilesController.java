package pl.endproject.offerscomparator.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.endproject.offerscomparator.domain.Product;
import pl.endproject.offerscomparator.infrastructure.filesDownloader.ExcelService;
import pl.endproject.offerscomparator.infrastructure.filesDownloader.PdfService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class DownloadFilesController {
    @Autowired
    private ProductController productController;
    private ExcelService excelService;
    private PdfService pdfService;
    @Autowired
    private ServletContext servletContext;

    public DownloadFilesController(ExcelService excelService, PdfService pdfService) {
        this.excelService = excelService;
        this.pdfService = pdfService;
    }

    @RequestMapping("/printPdf")
    @ResponseBody
    public void getPdfFile(HttpServletRequest request, HttpServletResponse response) {
        List<Product> products = productController.getProducts();
        pdfService.createPdf(products, servletContext, request, response);
    }


    @RequestMapping("/printExcel")
    @ResponseBody
    public void getExcel(HttpServletRequest request, HttpServletResponse response) {
        List<Product> products = productController.getProducts();
        excelService.generateExcel(products, servletContext, request, response);
    }
}
