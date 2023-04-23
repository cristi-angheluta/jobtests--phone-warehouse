package wh.phones.be.domain.model;

import java.util.Objects;

public record PhoneAvailability(long id, String model, boolean available) {

    public String getAvailability() {
        return available() ? "yes" : "no";
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
