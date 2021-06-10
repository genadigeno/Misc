package geno.micro.fastpassconsole;

import geno.micro.fastpassconsole.message.TollSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
public class FastPassRestController {

    @Autowired
    private TollSource tollSource;

    @RequestMapping(value = "/toll", method = RequestMethod.POST)
    public String publishMessage(@RequestBody String payload) {
        System.out.println("payload = " + payload);
        Random random = new Random();
        tollSource.fastpassToll().send(MessageBuilder.withPayload(payload).setHeader(
        "speed", random.nextInt(8) * 10).build()
        );
        return "success";
    }
}
