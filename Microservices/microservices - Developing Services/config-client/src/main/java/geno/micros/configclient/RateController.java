package geno.micros.configclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RefreshScope
public class RateController {

    @Value("${rate}")
    private String rate;

    @Value("${lanecount}")
    private String laneCount;

    @Value("${tollstart}")
    private String tallStart;

    @Value("${constring}")
    private String connString;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> home() {
        ResponseObject object = new ResponseObject(rate, laneCount, tallStart, connString);
        return ResponseEntity.of(Optional.of(object));
    }
}
