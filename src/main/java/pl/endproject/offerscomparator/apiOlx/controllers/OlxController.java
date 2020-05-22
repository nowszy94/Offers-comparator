package pl.endproject.offerscomparator.apiOlx.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.endproject.offerscomparator.apiOlx.api.OlxScraper;
import pl.endproject.offerscomparator.apiOlx.model.Item;
import pl.endproject.offerscomparator.apiOlx.service.OlxService;

import java.util.List;

@Controller
public class OlxController {

    private final OlxService olxService;

    //TODO: dodać do modelu
    private final String TU_WPISZ_CO_WYSZKUKAC = "potop";
    private final Integer TU_WPISZ_ILOSC = 5;

    @Autowired
    public OlxController(OlxService olxService) {
        this.olxService = olxService;
    }

    @GetMapping("/all")
    public String getItems(Model model) {
        List<Item> allItems = olxService.getAllItems(TU_WPISZ_CO_WYSZKUKAC, TU_WPISZ_ILOSC);
        model.addAttribute("allItems", allItems);
        return "getAll";
    }
}
