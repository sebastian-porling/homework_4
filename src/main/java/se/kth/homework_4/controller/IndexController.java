package se.kth.homework_4.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

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
            System.err.println("***** PAGE NOT FOUND ******");
            model.addAttribute("errorType", "404 page not found");
            model.addAttribute("errorMessage", "The page you tried to reach doesn't exist on this server.");
        } else if (statusCode == HttpStatus.BAD_REQUEST.value()){
            System.err.println("***** BAD REQUEST ******");
            model.addAttribute("errorType", "500 Bad reqeust");
            model.addAttribute("errorMessage", "The action you tried to make is not possible.");
        } else {
            System.out.println(HttpStatus.valueOf(statusCode).toString());
        }
        model.addAttribute("error", "Not valid input");
        return "error";
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Exception exc, Model model) {
        System.err.println("***** INTERNAL ERROR ******");
        model.addAttribute("errorType", "INTERNAL SERVER ERROR");
        model.addAttribute("errorMessage", "The action you tried to make is not possible. Please try again.");
        return "error";
    }

    private String extractHttpStatusCode(HttpServletRequest request) {
        return request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE).toString();
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
