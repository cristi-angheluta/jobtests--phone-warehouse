package wh.phones.be.domain.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Booking {

    private String id;
    private long phoneId;
    private LocalDateTime bookingDate;
    private String user;
    private LocalDateTime returnDate;

    public Booking() {
    }

    public String getId() {
        return id;
    }

    public Booking setId(String id) {
        this.id = id;
        return this;
    }

    public long getPhoneId() {
        return phoneId;
    }

    public Booking setPhoneId(long phoneId) {
        this.phoneId = phoneId;
        return this;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public Booking setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
        return this;
    }

    public String getUser() {
        return user;
    }

    public Booking setUser(String user) {
        this.user = user;
        return this;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public Booking setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return id == booking.id && phoneId == booking.phoneId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, phoneId);
    }

    @Override
    public String toString() {
        return "Booking{" +
               "id=" + id +
               ", phoneId=" + phoneId +
               ", bookingDate=" + bookingDate +
               ", user='" + user + '\'' +
               ", returnDate=" + returnDate +
               '}';
    }
}
