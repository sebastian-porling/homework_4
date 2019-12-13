package se.kth.homework_4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import se.kth.homework_4.model.*;
import javax.validation.Valid;

@Controller
public class MainController {

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private RateService rateService;

    private Conversion conversion = new Conversion("SEK", "USD", 0, 0);

    private Currency currentCurrency = new Currency("SEK");

    private Rate rateToChange = new Rate(null, null, 0.0);

    @RequestMapping(value = "/")
    public String indexPage(Model model) {
        model.addAttribute("currencies", currencyService.getAll());
        model.addAttribute("error", "");
        model.addAttribute("conversion", conversion);
        return "index";
    }

    @RequestMapping(value = "/convert")
    public String convert(@Valid Conversion conversion, BindingResult result, Model model){
        if (result.hasErrors()){
            this.conversion = new Conversion("SEK", "USD", 0, 0);
            model.addAttribute("currencies", currencyService.getAll());
            model.addAttribute("error", "Invalid input");
            model.addAttribute("conversion", this.conversion);
            return "index";
        }
        convertion(conversion);
        this.conversion = conversion;
        return "redirect:/";
    }

    private void convertion(Conversion conversion){
        Currency fromCurrency = currencyService.get(conversion.getFromCurrency());
        Currency toCurrency = currencyService.get(conversion.getToCurrency());
        Rate rate = rateService.getRateForConversion(fromCurrency, toCurrency);
        conversion.convert(rate);
        rateService.incrementNumberOfUses(rate);
    }

    @RequestMapping(value = "/admin")
    public String adminPage(Model model) {
        addAttributes(model);
        model.addAttribute("error", "");
        return "admin";
    }

    @RequestMapping(value = "/admin/changeRate")
    public String changeRate(@Valid Rate rate, double newRate, BindingResult result, Model model){
        if (result.hasErrors()){
            addAttributes(model);
            model.addAttribute("error", "Not valid values for rate");
            return "admin";
        }
        if (newRate < 0){
            addAttributes(model);
            model.addAttribute("error", "Can't have negative rate");
            return "admin";
        }
        rate.setRate(newRate);
        rateService.storeRate(rate);
        return "redirect:/admin";
    }

    private void addAttributes(Model model){
        model.addAttribute("currencies", currencyService.getAll());
        model.addAttribute("currentCurrency", currentCurrency);
        model.addAttribute("rates", rateService.getAll());
    }

    @RequestMapping(value = "/admin/setCurrent")
    public String setCurrent(@Valid Currency currency, BindingResult result, Model model){
        if (result.hasErrors()){

        }
        System.out.println(currency);
        this.currentCurrency = currency;
        return "redirect:/admin";
    }

}
