package by.gourianova.monitorsensors;



import java.util.Objects;


public class Sensor extends Entity {


    private int id;
    private String name;
    private String model;
    private int range_from;
    private int range_to;
   private int typeId;
   private int unitId;
    private String unit;
    private String type;
    private String location;
    private String description;


    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }



    public int getId() {
        return id;

    }    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getRange_from() {
        return range_from;
    }

    public void setRange_from(int range_from) {
        this.range_from = range_from;
    }

    public int getRange_to() {
        return range_to;
    }

    public void setRange_to(int range_to) {
        this.range_to = range_to;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }





    public Sensor() {
    }

    public Sensor(int id, String name, String model, int range_from, int range_to,int typeId,int unitId, String type, String unit, String location,String description) {
        this.id=id;
        this.name=name;
        this.model=model;
        this.range_from=range_from;
        this.range_to=range_to;
        this.typeId=typeId;
        this.unitId=unitId;
        this.type=type;
        this.unit=unit;
        this.location=location;
        this.description=description;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sensor)) return false;
        Sensor sensor = (Sensor) o;
        return getId() == sensor.getId() &&
                Objects.equals(getName(),sensor.getName())&&
                Objects.equals(getModel(),sensor.getModel())&&
                 getRange_from()==sensor.getRange_from()&&
                getRange_to()==sensor.getRange_to()&&
                 Objects.equals(getType(), sensor.getType()) &&
                Objects.equals(getUnit(), sensor.getUnit()) &&
                Objects.equals(getLocation(), sensor.getLocation()) &&
                Objects.equals(getDescription(), sensor.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),getName() ,getModel(), getRange_from(),getRange_to(), getType(),getUnit() , getLocation(),getDescription());
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "id=" + id +
                ", Name='" + name +
                ", Model='" + model +
                ", Range_from='" + range_from +
                ", Range_to='" + range_to +
                ", Type='" + type +
                ", Unit='" + unit +
                ", Location='" + location +
                ", Description='" + description +


                '}' + super.toString();
    }
}