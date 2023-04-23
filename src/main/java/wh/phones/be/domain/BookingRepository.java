package wh.phones.be.domain;

import wh.phones.be.domain.model.Booking;

public interface BookingRepository {
    boolean isDeviceAvailable(long phoneId);

    Booking bookDevice(long phoneId, String currentUser);

    Booking findByBookingId(String bookingId);

    Booking returnDevice(Booking booking);
}
