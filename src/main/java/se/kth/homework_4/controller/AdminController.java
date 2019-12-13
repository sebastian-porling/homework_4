/*package se.kth.homework_4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import se.kth.homework_4.model.*;
import javax.validation.Valid;

@Controller
public class AdminController {

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private RateService rateService;

    @RequestMapping(value = "/admin")
    public String adminPage(Model model) {
        model.addAttribute("currencies", currencyService.getAll());
        model.addAttribute("rates", rateService.getAll());
        model.addAttribute("error", "");
        return "admin";
    }

    public void changeRate(@Valid Rate rate, BindingResult result, Model model){
        if (result.hasErrors()){

        }

    }
}
*/