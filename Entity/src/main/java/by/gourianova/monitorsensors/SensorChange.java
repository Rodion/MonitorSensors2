package by.gourianova.monitorsensors;

import java.util.Objects;


public class SensorChange extends Entity {
    private int id;

    private int sensorId;
    private int userId;

    public int getSensorId() {
        return sensorId;
    }

    public void setSensorId(int sensorId) {
        this.sensorId = sensorId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SensorChange)) return false;
        SensorChange sensorChange = (SensorChange) o;
        return getId() == sensorChange.getId() &&
                getSensorId() == sensorChange.getSensorId() &&
                getUserId() == sensorChange.getUserId();

    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSensorId(), getUserId());
    }

    @Override
    public String toString() {
        return "SensorChange{" +
                "id=" + id +
                ", sensorId=" + sensorId +
                ", userId=" + userId +
                "} " + super.toString();
    }
}
