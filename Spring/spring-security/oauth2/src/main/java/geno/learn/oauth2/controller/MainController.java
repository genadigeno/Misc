package geno.learn.oauth2.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping(value = "/api/")
public class MainController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "Home";
    }
}
