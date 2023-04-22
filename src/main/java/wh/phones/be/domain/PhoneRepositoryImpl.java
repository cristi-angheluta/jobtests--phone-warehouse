package wh.phones.be.domain;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
class PhoneRepositoryImpl implements PhoneRepository {

    private final JdbcTemplate ds;

    PhoneRepositoryImpl(JdbcTemplate ds) {
        this.ds = ds;
    }
}
