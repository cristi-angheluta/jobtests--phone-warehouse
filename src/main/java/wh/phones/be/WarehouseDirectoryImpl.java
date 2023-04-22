package wh.phones.be;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wh.phones.be.domain.PhoneRepository;

@Transactional
@Service
class WarehouseDirectoryImpl implements WarehouseDirectory {
    private static final Logger log = LoggerFactory.getLogger(WarehouseDirectoryImpl.class);

    private final PhoneRepository phones;

    WarehouseDirectoryImpl(PhoneRepository phones) {
        this.phones = phones;
    }

}
