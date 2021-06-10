package geno.micros.secureservice.controller;

import java.util.ArrayList;
import geno.micros.secureservice.model.TollUsage;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableResourceServer
public class GeneralRestController {

    @RequestMapping(value = "/tolldata", method = RequestMethod.GET)
    @PreAuthorize("#oauth2.hasAnyScope('toll_read') and hasRole('ADMIN')")
    public ArrayList<TollUsage> getData(){

        TollUsage tollUsage1 = new TollUsage("124", "32423", "g5h5", "1984");
        TollUsage tollUsage2 = new TollUsage("1", "234", "ghfgh", "1990");
        TollUsage tollUsage3 = new TollUsage("24", "23", "kdfg", "2001");

        ArrayList<TollUsage> list = new ArrayList<TollUsage>();
        list.add(tollUsage1);
        list.add(tollUsage2);
        list.add(tollUsage3);

        return list;
    }
}
