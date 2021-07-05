package by.gourianova.monitorsensors;

import java.util.Objects;


public class SensorUnit extends Entity {
    private int id;
    private String unit;


    public SensorUnit() {
    }

    public SensorUnit(int id, String unit) {
        this.id = id;
        this.unit = unit;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SensorUnit)) return false;
        SensorUnit sensorUnit = (SensorUnit) o;
        return getId() == sensorUnit.getId() &&
                Objects.equals(getUnit(), sensorUnit.getUnit()) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUnit());
    }

    @Override
    public String toString() {
        return "SensorUnit{" +
                "id=" + id +
                ", unit='" + unit + '\'' +
                    '}';
    }
}
