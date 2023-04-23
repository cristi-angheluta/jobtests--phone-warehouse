package wh.phones.fe;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import wh.phones.be.WarehouseDirectory;
import wh.phones.be.domain.model.Booking;
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

    @GetMapping(path = "availability")
    List<PhoneAvailability> listAvailability() {
        return this.warehouse.listPhonesAvailability();
    }

    @PostMapping(path = "/book/{phoneId}")
    Booking bookPhone(@PathVariable("phoneId") @Min(1) long phoneId, @RequestParam("user") @NotBlank String user) {
        return this.warehouse.bookPhone(phoneId, user);
    }

    @PostMapping(path = "/return/{bookingId}")
    Booking returnPhone(@PathVariable("bookingId") @NotBlank String bookingId) {
        return this.warehouse.returnPhone(bookingId);
    }
}
