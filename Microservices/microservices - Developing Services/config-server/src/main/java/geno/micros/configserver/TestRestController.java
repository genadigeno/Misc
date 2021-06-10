package geno.micros.configserver;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

@RestController
public class TestRestController {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String getRequest(HttpServletResponse response, HttpServletRequest request){
        HttpSession session = request.getSession();

        Enumeration<String> enumeration = session.getAttributeNames();
        while (enumeration.hasMoreElements()){
            System.out.println(enumeration.nextElement());
        }

        SecurityContext context = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
        System.out.println(context.getAuthentication());


        CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
        System.out.println("token = " + token);
        return "GET: Ok; _csrf=";
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public String postRequest(){
        return "POST: Ok";
    }
}
