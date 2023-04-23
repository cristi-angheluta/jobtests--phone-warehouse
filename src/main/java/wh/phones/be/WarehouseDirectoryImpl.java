package wh.phones.be;

import jakarta.validation.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wh.phones.be.domain.BookingRepository;
import wh.phones.be.domain.PhoneRepository;
import wh.phones.be.domain.model.Booking;
import wh.phones.be.domain.model.PhoneAvailability;

import java.util.List;

@Transactional(readOnly = true)
@Service
class WarehouseDirectoryImpl implements WarehouseDirectory {
    private static final Logger log = LoggerFactory.getLogger(WarehouseDirectoryImpl.class);

    private final PhoneRepository phonesRepo;
    private final BookingRepository bookingRepo;

    WarehouseDirectoryImpl(PhoneRepository phonesRepo, BookingRepository bookingRepo) {
        this.phonesRepo = phonesRepo;
        this.bookingRepo = bookingRepo;
    }

    @Transactional(readOnly = true)
    @Override
    public List<PhoneAvailability> listPhonesAvailability() {
        return this.phonesRepo.listAvailability();
    }

    @Override
    public Booking bookPhone(long phoneId, String currentUser) {
        var deviceSummary = this.phonesRepo.isAvailable(phoneId);
        if (deviceSummary != null && deviceSummary.isAvailable()) {
            log.info("Found an available deviceSummary: `{}`. Booking it.", deviceSummary);
            return this.bookingRepo.bookDevice(deviceSummary.getId(), currentUser);
        }
        if (deviceSummary == null) {
            log.warn("No device found for phoneId: `{}`", phoneId);
        } else {
            log.warn("No device available found for phoneId: `{}`", phoneId);
        }
        return null;
    }

    @Override
    public Booking returnPhone(@NotBlank String bookingId) {
        Booking found = this.bookingRepo.findByBookingId(bookingId);
        if (found != null && found.getReturnDate() == null) {
            return this.bookingRepo.returnDevice(found);
        }
        if (found == null) {
            log.warn("No booking found for id: `{}`", bookingId);
        } else {
            log.warn("A completed booking [{}] cannot be marked again as completed", found);
        }

        return found;
    }
}
