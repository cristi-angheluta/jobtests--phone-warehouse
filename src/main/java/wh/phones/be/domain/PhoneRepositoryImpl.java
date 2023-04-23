package wh.phones.be.domain;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import wh.phones.be.domain.model.PhoneAvailability;

import java.util.ArrayList;
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
                 LEFT OUTER JOIN PHONES_NOT_RETURNED pnr ON p.ID = pnr.PID
            """;

    private final JdbcTemplate jdbc;

    PhoneRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbc = jdbcTemplate;
    }

    private static List<PhoneAvailability> generateDummies(int counter) {
        return new ArrayList<>(){{
            for (int i = 0; i < counter; i++) {
                add(generateDummy());
            }
        }};
    }

    private static PhoneAvailability generateDummy() {
        return new PhoneAvailability(RandomUtils.nextLong(),RandomStringUtils.randomAlphanumeric(10, 20), RandomUtils.nextBoolean());
    }

    @Override
    public List<PhoneAvailability> listAvailability() {
        return generateDummies(RandomUtils.nextInt(2, 10));
    }
}
