package am.test.security.controller;

import am.test.security.confing.WebSecurityConfig;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping(path = "/test")
    public String index() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth instanceof AnonymousAuthenticationToken) {
            return "Who you are ???";
        }

        User user = (User) auth.getPrincipal();
        if (user.getAuthorities().contains(WebSecurityConfig.AUTHORITY_ADMIN)) {
            return "You are admin";
        }
        if (user.getAuthorities().contains(WebSecurityConfig.AUTHORITY_USER)) {
            return "You are user";
        }
        return "Who you are ???";
    }
}
