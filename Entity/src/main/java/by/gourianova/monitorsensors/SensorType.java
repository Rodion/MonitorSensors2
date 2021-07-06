package by.gourianova.monitorsensors;

import java.math.BigDecimal;
import java.util.Objects;

public class SensorType extends Entity {
    private int id;
    private String type;
    private BigDecimal price;
    private String image;

    public SensorType() {
    }

    public SensorType(int id, String type) {
        this.id = id;
        this.type = type;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SensorType)) return false;
        SensorType sensorType = (SensorType) o;
        return getId() == sensorType.getId() &&
                Objects.equals(getType(), sensorType.getType());

    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getType());
    }

    @Override
    public String toString() {
        return "SensorType{" +
                "id=" + id +
                ", type='" + type + '\'' +

                "} " + super.toString();
    }
}
