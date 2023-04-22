package wh.phones.fe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import wh.phones.be.WarehouseDirectory;
import wh.phones.be.domain.model.PhoneAvailability;

import java.util.List;

@RequestMapping("/phones")
@RestController
class PhoneController {
    private static final Logger log = LoggerFactory.getLogger(PhoneController.class);

    private final WarehouseDirectory warehouse;

    PhoneController(WarehouseDirectory warehouse) {
        this.warehouse = warehouse;
    }

    @GetMapping(path = "availability")
    List<PhoneAvailability> listPhones() {
        return this.warehouse.listPhonesAvailability();
    }

    @PostMapping(path = "/book/{phoneId}")
    PhoneAvailability bookPhone(@PathVariable("phoneId") String phoneId) {
        return null;
    }

    @PostMapping(path = "/return/{phoneId}")
    PhoneAvailability returnPhone(@PathVariable("phoneId") String phoneId) {
        return null;
    }
}
