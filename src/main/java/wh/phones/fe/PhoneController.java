package wh.phones.fe;

import jakarta.validation.constraints.NotBlank;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import wh.phones.be.WarehouseDirectory;
import wh.phones.be.domain.model.PhoneAvailability;

import java.util.List;

@RequestMapping("/phones")
@RestController
class PhoneController {
    private static final Logger log = LoggerFactory.getLogger(PhoneController.class);

    /**
     * Business logic API responsible with querying and altering the state of the phones in the warehouse.
     * Spring injected service.
     */
    private final WarehouseDirectory warehouse;

    PhoneController(WarehouseDirectory warehouse) {
        this.warehouse = warehouse;
    }

    /**
     * Returns a generic user. It should be fetched from request as parameter or from the SecurityContext (if security is in place)
     * @param exchange the http wrapper containing the request and response handlers
     * @return a dummy alphanumeric username.
     */
    private static String getCurrentUser(ServerWebExchange exchange) {
        return RandomStringUtils.randomAlphanumeric(5, 10);
    }

    @GetMapping(path = "availability")
    List<PhoneAvailability> listAvailability() {
        return this.warehouse.listPhonesAvailability();
    }

    @PostMapping(path = "/book/{phoneId}")
    PhoneAvailability bookPhone(@PathVariable("phoneId") @NotBlank String phoneId, ServerWebExchange exchange) {
        return this.warehouse.bookPhone(phoneId, getCurrentUser(exchange));
    }

    @PostMapping(path = "/return/{phoneId}")
    PhoneAvailability returnPhone(@PathVariable("phoneId") @NotBlank String phoneId) {
        return this.warehouse.returnPhone(phoneId);
    }
}
