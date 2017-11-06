package am.test.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RedirectController {

    @PostMapping(path = "/redirect")
    public String index() {
        return "logged";
    }
}
