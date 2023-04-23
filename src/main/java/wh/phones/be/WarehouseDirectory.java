package wh.phones.be;

import wh.phones.be.domain.model.Booking;
import wh.phones.be.domain.model.PhoneAvailability;

import java.util.List;

//TODO @cristi continue to document the api

public interface WarehouseDirectory {
    /**
     * Lists all phones' availability in the warehouse.
     *
     * @return a list filled with phones' availability
     */
    List<PhoneAvailability> listPhonesAvailability();

    /**
     * Let a phone be booked.
     *
     * @param phoneId     the phone to be booked
     * @param currentUser the borrower
     * @return null if no available phone found, otherwise the current
     */
    Booking bookPhone(long phoneId, String currentUser);

    /**
     * Return a device by bookingId
     *
     * @param bookingId the booking id to be completed
     * @return the completed booking if found
     */
    Booking returnPhone(String bookingId);
}
