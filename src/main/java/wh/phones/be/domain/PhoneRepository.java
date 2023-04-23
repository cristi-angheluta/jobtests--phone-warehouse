package wh.phones.be.domain;

import jakarta.validation.constraints.Min;
import wh.phones.be.domain.model.PhoneAvailability;

import java.util.List;

/**
 * The repository interface exposed to services
 */
public interface PhoneRepository {
    List<PhoneAvailability> listAvailability();

    PhoneAvailability isAvailable(@Min(1) long phoneId);
}
