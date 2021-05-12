package com.appsdeveloperblog.resourceserver.controllers;

import com.appsdeveloperblog.resourceserver.response.UserRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private Environment environment;

    @GetMapping(value = "/status/check")
    public String status(){
        return "works on port: "+environment.getProperty("local.server.port");
    }

    //@Secured("ROLE_developer")
    //@PreAuthorize("hasRole('developer')")
    @PreAuthorize("hasAuthority('ROLE_developer') or #id == #jwt.subject")
    @DeleteMapping(path = "/{id}")
    public String deleteUser(@PathVariable String id, @AuthenticationPrincipal Jwt jwt){
        return "deleted: "+id+" and jwt subject: "+jwt.getSubject();
    }

    @PostAuthorize("returnObject.id==#jwt.subject")
    @GetMapping(path = "/{id}")
    public UserRest getUser(@PathVariable String id, @AuthenticationPrincipal Jwt jwt){
        return new UserRest("1", "geno", "mumladze");
    }
}
