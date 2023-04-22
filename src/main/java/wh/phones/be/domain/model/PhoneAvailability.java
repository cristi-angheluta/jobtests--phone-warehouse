package wh.phones.be.domain.model;

import java.util.Objects;

public class PhoneAvailability {
    private final String id;
    private final String model;
    private final boolean availability;

    public PhoneAvailability(String id, String model, boolean availability) {
        this.id = id;
        this.model = model;
        this.availability = availability;
    }

    public String getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public boolean isAvailability() {
        return availability;
    }

    @Override
    public String toString() {
        return "PhoneAvailability{" +
               "id='" + id + '\'' +
               ", model='" + model + '\'' +
               ", availability=" + availability +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneAvailability that = (PhoneAvailability) o;
        return Objects.equals(id, that.id) && Objects.equals(model, that.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model);
    }
}
