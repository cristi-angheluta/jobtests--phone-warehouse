package wh.phones.be.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
class PhoneRepositoryImpl implements PhoneRepository {
    private static final Logger log = LoggerFactory.getLogger(PhoneRepositoryImpl.class);

    //language=H2
    private static final String LIST_AVAILABLE_PHONES = """
            SELECT p.*,
                 IFNULL(booked, 0) as booked,
                 CASE
                     WHEN (p.QUANTITY - IFNULL(booked, 0) > 0) THEN true
                     ELSE false
                 END as AVAILABILITY
            FROM PHONES p 
                 LEFT OUTER JOIN PHONES_NOT_RETURNED pnr ON p.ID = pnr.PID
            """;

    private final JdbcTemplate ds;

    PhoneRepositoryImpl(JdbcTemplate ds) {
        this.ds = ds;
    }
}
