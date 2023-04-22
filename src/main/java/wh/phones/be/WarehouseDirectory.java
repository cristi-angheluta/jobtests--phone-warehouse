package wh.phones.be;

import wh.phones.be.domain.model.PhoneAvailability;

import java.util.List;

public interface WarehouseDirectory {
    List<PhoneAvailability> listPhonesAvailability();
}
