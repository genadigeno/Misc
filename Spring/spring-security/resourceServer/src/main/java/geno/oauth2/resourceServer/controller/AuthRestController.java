package geno.oauth2.resourceServer.controller;

import geno.oauth2.resourceServer.domain.UserInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class AuthRestController {

    @GetMapping(value = "/userinfo")
    public UserInfo userInfo(Principal principal) {
        return new UserInfo(principal.getName(), "Joe", "Smith", "jm@mail.ko");
    }
}
