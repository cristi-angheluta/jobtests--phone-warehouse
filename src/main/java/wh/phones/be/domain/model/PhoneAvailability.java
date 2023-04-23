package wh.phones.be.domain.model;

import java.util.Objects;

public class PhoneAvailability {
    private long id;
    private String model;
    private boolean available;

    public PhoneAvailability(){}

    public PhoneAvailability(long id, String model, boolean available) {
        this.id = id;
        this.model = model;
        this.available = available;
    }

    public long getId() {
        return id;
    }

    public PhoneAvailability setId(long id) {
        this.id = id;
        return this;
    }

    public String getModel() {
        return model;
    }

    public PhoneAvailability setModel(String model) {
        this.model = model;
        return this;
    }

    public boolean isAvailable() {
        return available;
    }

    public PhoneAvailability setAvailable(boolean available) {
        this.available = available;
        return this;
    }

    public String getAvailability() {
        return isAvailable() ? "yes" : "no";
    }

    @Override
    public String toString() {
        return "PhoneAvailability{" +
               "id='" + id + '\'' +
               ", model='" + model + '\'' +
               ", availability=" + getAvailability() +
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
