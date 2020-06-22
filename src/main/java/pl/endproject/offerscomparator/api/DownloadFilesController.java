package pl.endproject.offerscomparator.api;

import com.sun.net.httpserver.HttpContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.annotation.SessionScope;
import pl.endproject.offerscomparator.domain.Product;
import pl.endproject.offerscomparator.infrastructure.filesDownloader.ExcelService;
import pl.endproject.offerscomparator.infrastructure.filesDownloader.PdfService;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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
    public void getPdfFile(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        List<Product> products = (List<Product>) session.getAttribute("products");

        Cookie uiColorCookie = setCookie(response);
        System.out.println(uiColorCookie.getName() + " " + uiColorCookie.getValue());


        pdfService.createPdf(products, servletContext, request, response);
        Cookie co = new Cookie("downloadStarted","1");
        co.setMaxAge(0);
        response.addCookie(co);
        System.out.println("Co " + co.getValue());
    }


    @RequestMapping("/printExcel")
    @ResponseBody
    public void getExcel(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        List<Product> products = (List<Product>) session.getAttribute("products");
        excelService.generateExcel(products, servletContext, request, response);
    }

    @SessionScope
    private Cookie setCookie(HttpServletResponse response){
        Cookie uiColorCookie = new Cookie("downloadStarted", "1");
        response.addCookie(uiColorCookie);
        uiColorCookie.setMaxAge(60*60);

        return uiColorCookie;
    }
}
