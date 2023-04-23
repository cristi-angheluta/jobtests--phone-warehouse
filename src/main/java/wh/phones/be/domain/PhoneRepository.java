package wh.phones.be.domain;

import wh.phones.be.domain.model.PhoneAvailability;

import java.util.List;

/**
 * The repository interface exposed to services
 */
public interface PhoneRepository {
    List<PhoneAvailability> listAvailability();
}
