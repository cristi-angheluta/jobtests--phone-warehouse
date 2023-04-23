package wh.phones.be.domain;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import wh.phones.be.domain.model.Booking;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Repository
class BookingRepositoryImpl implements BookingRepository {
    private static final Logger log = LoggerFactory.getLogger(BookingRepositoryImpl.class);
    private final JdbcTemplate jdbc;

    BookingRepositoryImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public boolean isDeviceAvailable(@Min(1) long phoneId) {
        return false;
    }

    @Override
    public Booking bookDevice(@Min(1) long phoneId, @NotBlank String currentUser) {
        var b = new Booking();
        b.setId(UUID.randomUUID().toString());
        b.setPhoneId(phoneId);
        b.setUser(currentUser);
        b.setBookingDate(LocalDateTime.now());

        return this.insert(b);
    }

    @Override
    public Booking findByBookingId(@NotBlank String bookingId) {
        return jdbc.queryForObject("SELECT b.* FROM BOOKINGS b WHERE b.ID = ?", new RowMapper<Booking>() {

            @Override
            public Booking mapRow(ResultSet rs, int rowNum) throws SQLException {
                Booking b = new Booking();
                b.setId(rs.getString("ID"));
                b.setPhoneId(rs.getLong("PHONE_ID"));
                b.setBookingDate(rs.getTimestamp("BOOKING_DATE").toLocalDateTime());
                b.setUser(rs.getString("BOOKED_BY"));

                Timestamp returnDate = rs.getTimestamp("RETURN_DATE");
                if (returnDate != null) {
                    b.setReturnDate(returnDate.toLocalDateTime());
                }

                return b;
            }
        }, bookingId);
    }

    @Override
    public Booking returnDevice(Booking booking) {
        return complete(booking);
    }

    private Booking insert(Booking b) {
        int counter = jdbc.update(
                "INSERT INTO BOOKINGS (ID, PHONE_ID, BOOKING_DATE, BOOKED_BY) VALUES (?, ?, ?, ? )",
                b.getId(), b.getPhoneId(), b.getBookingDate(), b.getUser()
        );
        if (counter == 1) {
            log.info("New booking recorded: `{}`", b.getId());
            return b;
        }

        log.error("New booking was not recorded: `{}`", b.getId());
        return null;
    }

    private Booking complete(Booking booking) {
        Objects.requireNonNull(booking);
        booking.setReturnDate(LocalDateTime.now());

        int counter = jdbc.update(
                "UPDATE BOOKINGS b SET RETURN_DATE = ?  WHERE b.ID = ?",
                booking.getBookingDate(), booking.getId()
        );
        if (counter == 1) {
            log.info("New booking recorded: `{}`", booking.getId());
            return booking;
        }

        log.error("New booking was not recorded: `{}`", booking.getId());
        return null;
    }
}
