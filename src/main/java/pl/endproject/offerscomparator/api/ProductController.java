package pl.endproject.offerscomparator.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.endproject.offerscomparator.domain.Product;
import pl.endproject.offerscomparator.domain.ProductService;
import pl.endproject.offerscomparator.infrastructure.autocompleteFeature.Phrase;
import pl.endproject.offerscomparator.infrastructure.autocompleteFeature.Reader;
import pl.endproject.offerscomparator.infrastructure.autocompleteFeature.ReaderConfig;
import pl.endproject.offerscomparator.infrastructure.autocompleteFeature.SuggestionsWrapper;
import pl.endproject.offerscomparator.infrastructure.printPDFeature.PdfService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes({"userSearch"})
public class ProductController {

    //TODO: downloads pdf but after first request every other pdf is the same as first one.....
    private ProductService productService;
    private final ReaderConfig readerConfig;
    @Autowired
    private ServletContext servletContext;
    @Autowired
    private PdfService pdfService;


    public ProductController(ProductService productService, ReaderConfig readerConfig) {
        this.productService = productService;
        this.readerConfig = readerConfig;

    }

    @GetMapping("/offers")
    public String getOffers(Model model, @RequestParam(value = "userSearch", required = false, defaultValue = "") String userSearch,
                            HttpServletRequest request) {
        if (!userSearch.isBlank()) {
            List<Product> products = productService.findForPhrase(userSearch);
            if (products.isEmpty()) {
                return "no-results";
            }
            model.addAttribute("products", products);
            request.getSession().setAttribute("userSearch", userSearch);

        }
        return "getAll";
    }

    @PostMapping("/findProducts")
    public String findOffers(@RequestParam String userSearch) throws UnsupportedEncodingException {
        String encodedUserSearch = URLEncoder.encode(userSearch, StandardCharsets.UTF_8.toString());
        return "redirect:/offers?userSearch=" + encodedUserSearch;
    }


    @RequestMapping(value = "/suggestion", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public SuggestionsWrapper autocompleteSuggestions(@RequestParam("userSearch") String userSearch) throws IOException {
        ArrayList<Phrase> suggestions = new ArrayList<>();
        SuggestionsWrapper sw = new SuggestionsWrapper();
        Reader reader = readerConfig.readerFromFile();

        List<String> wordsFromReader = reader.getWords(userSearch);
        for (String productName : wordsFromReader) {
            suggestions.add(new Phrase(productName));

            sw.setSuggestions(suggestions);
        }
        return sw;
    }


    @RequestMapping("/printPdf")
    @ResponseBody
    public void getPdfFile(HttpServletRequest request, HttpServletResponse response) {
        String us = (String) request.getSession().getAttribute("userSearch");
        List<Product> products = productService.findForPhrase(us);
        pdfService.createPdf(products, servletContext, request, response);

        String fullPath = request.getServletContext().getRealPath("/resources/templates/getAll" + ".pdf");
        fileDownload(fullPath, response, "oferty-pl.pdf");
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
