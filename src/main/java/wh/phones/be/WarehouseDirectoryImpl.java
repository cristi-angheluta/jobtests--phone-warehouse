package wh.phones.be;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wh.phones.be.domain.PhoneRepository;
import wh.phones.be.domain.model.PhoneAvailability;

import java.util.List;

@Transactional
@Service
class WarehouseDirectoryImpl implements WarehouseDirectory {
    private static final Logger log = LoggerFactory.getLogger(WarehouseDirectoryImpl.class);

    private final PhoneRepository phonesRepo;

    WarehouseDirectoryImpl(PhoneRepository phonesRepo) {
        this.phonesRepo = phonesRepo;
    }

    @Override
    public List<PhoneAvailability> listPhonesAvailability() {
        return this.phonesRepo.listAvailability();
    }
}
