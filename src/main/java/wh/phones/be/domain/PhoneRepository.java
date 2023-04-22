package wh.phones.be.domain;

import wh.phones.be.domain.model.PhoneAvailability;

import java.util.List;

public interface PhoneRepository {
    List<PhoneAvailability> listAvailability();
}
