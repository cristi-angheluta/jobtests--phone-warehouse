package wh.phones.be;

import wh.phones.be.domain.model.PhoneAvailability;

import java.util.List;

//TODO @cristi continue to document the api

public interface WarehouseDirectory {
    /**
     * Lists all phones' availability in the warehouse.
     * @return a list filled with phones' availability
     */
    List<PhoneAvailability> listPhonesAvailability();

    /**
     * Let a phone be booked.
     * @param phoneId
     * @param currentUser
     * @return null if no available phone found, otherwise the current
     */
    PhoneAvailability bookPhone(String phoneId, String currentUser);

    PhoneAvailability returnPhone(String phoneId);
}
