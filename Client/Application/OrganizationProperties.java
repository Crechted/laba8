package Client.Application;

import Code8.Address;
import Code8.Converter;
import Code8.Coordinates;
import Code8.OrganizationType;
import javafx.beans.property.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;

import java.awt.Color;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class OrganizationProperties implements Serializable {
    private static final long serialVersionUID = 30L;
    private LongProperty id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private StringProperty name; //Поле не может быть null, Строка не может быть пустой
    private ObjectProperty<Coordinates> coordinates; //Поле не может быть null
    private Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private FloatProperty annualTurnover; //Значение поля должно быть больше 0
    private IntegerProperty employeesCount; //Поле не может быть null, Значение поля должно быть больше 0
    private ObjectProperty<OrganizationType> type; //Поле может быть null
    private ObjectProperty<Address> officialAddress; //Поле может быть null
    private Color color;
    private OrganizationCircle organizationCircle;

    public OrganizationProperties(Long id, String name, Coordinates coordinates, LocalDateTime creationDate, float annualTurnover,
                        Integer employeesCount, OrganizationType type, Address officialAddress, Color color, Main main) {
        this.id = new SimpleLongProperty(id);
        this.name = new SimpleStringProperty(name);
        this.coordinates = new SimpleObjectProperty<Coordinates>(coordinates);
        this.creationDate = Converter.convertLocalDateTimeToDate(creationDate);
        this.annualTurnover = new SimpleFloatProperty(annualTurnover);
        this.employeesCount = new SimpleIntegerProperty(employeesCount);
        this.type = new SimpleObjectProperty<OrganizationType>(type);
        this.officialAddress = new SimpleObjectProperty<Address>(officialAddress);
        this.color = color;
        organizationCircle = new OrganizationCircle(((double) (coordinates.getX() + 902) / Long.MAX_VALUE) * 1000,
                ((double) (coordinates.getY() + Integer.MAX_VALUE - 553) / Integer.MAX_VALUE) * 1000,
                ((double)(annualTurnover/ Float.MAX_VALUE)) * 25,
                color, main.getCanvas());
        organizationCircle.addEventHandler(MouseEvent.MOUSE_CLICKED, (mouseEvent -> {
            Main.getRootController().selectRowInTable(this);
            Animation.startAnimationClicked(organizationCircle);
        }));
        Main.getRootController().addCircleInPane(organizationCircle);
    }

    @Override
    public String toString() {
        return "\n" + "ID: " + id.get()
                + "\n" + "name: " + name.get()
                + "\n" + "coordinatesX: " + coordinates.get().getX()
                + "\n" + "coordinatesY: " + coordinates.get().getY()
                + "\n" + "creationDate: " + creationDate.toString()
                + "\n" + "annualTurnover: " + annualTurnover.get()
                + "\n" + "employeesCount: " + employeesCount.get()
                + "\n" + "type: " + type.get()
                + "\n" + "officialAddress: " + officialAddress.get()
                + "\n" + "hashCode: " + hashCode()
                + "\n" + "---------------------------------------";
    }


    public long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public Coordinates getCoordinates() {
        return coordinates.get();
    }

    public ObjectProperty<Coordinates> coordinatesProperty() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates.set(coordinates);
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Date creationDateProperty() {
        return creationDate;
    }

    public void setCreationDateS(String creationDateS) {
        this.creationDate = creationDate;
    }

    public float getAnnualTurnover() {
        return annualTurnover.get();
    }

    public FloatProperty annualTurnoverProperty() {
        return annualTurnover;
    }

    public void setAnnualTurnover(float annualTurnover) {
        this.annualTurnover.set(annualTurnover);
    }

    public int getEmployeesCount() {
        return employeesCount.get();
    }

    public IntegerProperty employeesCountProperty() {
        return employeesCount;
    }

    public void setEmployeesCount(int employeesCount) {
        this.employeesCount.set(employeesCount);
    }

    public OrganizationType getType() {
        return type.get();
    }

    public ObjectProperty<OrganizationType> typeProperty() {
        return type;
    }

    public void setType(OrganizationType type) {
        this.type.set(type);
    }

    public Address getOfficialAddress() {
        return officialAddress.get();
    }

    public ObjectProperty<Address> officialAddressProperty() {
        return officialAddress;
    }

    public void setOfficialAddress(Address officialAddress) {
        this.officialAddress.set(officialAddress);
    }

    public OrganizationCircle getOrganizationCircle() {
        return organizationCircle;
    }

    public void setOrganizationCircle(OrganizationCircle organizationCircle) {
        this.organizationCircle = organizationCircle;
    }
}
