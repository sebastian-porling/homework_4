package se.kth.homework_4.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@ControllerAdvice
class IndexController implements ErrorController {
    private static final String ERROR_PATH = "/error";

    @RequestMapping(value = ERROR_PATH)
    public String error(HttpServletRequest request, HttpServletResponse response, Model model) {
        int statusCode = Integer.parseInt(extractHttpStatusCode(request));
        System.err.println("ERROR: " + statusCode);
        if (statusCode == HttpStatus.NOT_FOUND.value()){

        } else if (statusCode == HttpStatus.BAD_REQUEST.value()){
            System.out.println();
        } else {
            System.out.println(HttpStatus.valueOf(statusCode).toString());
        }
        model.addAttribute("error", "Not valid input");
        return "redirect:/";
    }

    private String extractHttpStatusCode(HttpServletRequest request) {
        return request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE).toString();
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
