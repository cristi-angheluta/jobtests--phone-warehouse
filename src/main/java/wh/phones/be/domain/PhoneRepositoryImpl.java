package wh.phones.be.domain;

import jakarta.validation.constraints.Min;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import wh.phones.be.domain.model.PhoneAvailability;

import java.util.List;

@Repository
class PhoneRepositoryImpl implements PhoneRepository {
    private static final Logger log = LoggerFactory.getLogger(PhoneRepositoryImpl.class);

    //language=H2
    private static final String LIST_AVAILABILITY = """
            SELECT p.*,
                 IFNULL(booked, 0) as booked,
                 CASE
                     WHEN (p.QUANTITY - IFNULL(booked, 0) > 0) THEN true
                     ELSE false
                 END as AVAILABILITY
            FROM PHONES p 
                 LEFT OUTER JOIN PHONES_NOT_RETURNED pnr ON p.ID = pnr.PHONE_ID
            """;

    //language=H2
    private static final String CHECK_AVAILABILITY_FOR_PHONE = """
            SELECT p.*,
                 IFNULL(booked, 0) as booked,
                 CASE
                     WHEN (p.QUANTITY - IFNULL(booked, 0) > 0) THEN true
                     ELSE false
                 END as AVAILABILITY
            FROM PHONES p 
                 LEFT OUTER JOIN PHONES_NOT_RETURNED pnr ON p.ID = pnr.PHONE_ID
                 WHERE p.ID = ?
            """;

    private final JdbcTemplate jdbc;

    PhoneRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbc = jdbcTemplate;
    }

    @Override
    public List<PhoneAvailability> listAvailability() {
        log.debug("Listing phones availability");
        var availabilityList = this.jdbc.query(
                LIST_AVAILABILITY,
                (rs, rowNum) ->
                        new PhoneAvailability(
                                rs.getLong("ID"),
                                rs.getString("MODEL"),
                                rs.getBoolean("AVAILABILITY")
                        )
        );
        log.info("List availability: `{}`", availabilityList);

        return availabilityList;
    }

    @Override
    public PhoneAvailability isAvailable(@Min(1) long phoneId) {
        log.debug("Checking availability for phoneId: `{}`", phoneId);
        var availabilityList = this.jdbc.query(
                CHECK_AVAILABILITY_FOR_PHONE,
                (rs, rowNum) ->
                        new PhoneAvailability(
                                rs.getLong("ID"),
                                rs.getString("MODEL"),
                                rs.getBoolean("AVAILABILITY")
                        ),
                phoneId
        );
        log.info("The availability for phoneId: `{}`", availabilityList);
        return availabilityList.stream().findFirst().orElse(null);
    }
}
