package pl.endproject.offerscomparator.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.endproject.offerscomparator.domain.Product;
import pl.endproject.offerscomparator.infrastructure.filesDownloader.ExcelService;
import pl.endproject.offerscomparator.infrastructure.filesDownloader.PdfService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.List;


@Controller
public class DownloadFilesController {
    private ExcelService excelService;
    private PdfService pdfService;
    private ServletContext servletContext;

    public DownloadFilesController(ExcelService excelService, PdfService pdfService, ServletContext servletContext) {
        this.excelService = excelService;
        this.pdfService = pdfService;
        this.servletContext = servletContext;
    }

    @RequestMapping("/printPdf")
    @ResponseBody
    public void getPdfFile(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        List<Product> products = (List<Product>) session.getAttribute("products");
        pdfService.createPdf(products, servletContext, request, response);
    }

    @RequestMapping("/printExcel")
    @ResponseBody
    public void getExcel(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        List<Product> products = (List<Product>) session.getAttribute("products");
        excelService.generateExcel(products, servletContext, request, response);
    }

}
