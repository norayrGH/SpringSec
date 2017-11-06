package am.test.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
    @GetMapping(path = "/login")
    public ModelAndView index(@RequestParam(value = "error", required = false) String error, ModelAndView modelAndView) {
        modelAndView.addObject("loginError", "".equals(error));
        modelAndView.setViewName("login.html");
        return modelAndView;
    }
}
