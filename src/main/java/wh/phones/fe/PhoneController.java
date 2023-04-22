package wh.phones.fe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RequestMapping("/phones/availability")
@RestController
class PhoneController {
    private static final Logger log = LoggerFactory.getLogger(PhoneController.class);

    @GetMapping(path = {"", "/"})
    Flux<String> listPhones() {
        return Flux.just("unu", "doi");
    }
}
