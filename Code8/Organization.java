package Code8;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * this class describes Organization from the collection
 * @see OrganizationType
 * @see Code8.Address
 * @see Code8.CollectionOrganization
 */
public class    Organization implements Serializable, Comparable<Organization> {
    private static final long serialVersionUID = 20L;
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private float annualTurnover; //Значение поля должно быть больше 0
    private Integer employeesCount; //Поле не может быть null, Значение поля должно быть больше 0
    private OrganizationType type; //Поле может быть null
    private Address officialAddress; //Поле может быть null
    private int access;

    public Organization(String name, Coordinates coordinates, float annualTurnover,
                        Integer employeesCount, OrganizationType type, Address officialAddress) {
        this.name = name;
        this.coordinates = coordinates;
        creationDate = LocalDateTime.now();
        this.annualTurnover = annualTurnover;
        this.employeesCount = employeesCount;
        this.type = type;
        this.officialAddress = officialAddress;
    }
    public Organization(Long id, String name, Coordinates coordinates, LocalDateTime creationDate, float annualTurnover,
                        Integer employeesCount, OrganizationType type, Address officialAddress) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.annualTurnover = annualTurnover;
        this.employeesCount = employeesCount;
        this.type = type;
        this.officialAddress = officialAddress;
    }
    public Organization(){}

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public float getAnnualTurnover() {
        return annualTurnover;
    }

    public Integer getEmployeesCount() {
        return employeesCount;
    }

    public OrganizationType getType() {
        return type;
    }

    public Address getOfficialAddress() {
        return officialAddress;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAccess() {
        return access;
    }

    public void setAccess(int access) {
        this.access = access;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setAnnualTurnover(float annualTurnover) {
        this.annualTurnover = annualTurnover;
    }

    public void setEmployeesCount(Integer employeesCount) {
        this.employeesCount = employeesCount;
    }

    public void setType(OrganizationType type) {
        this.type = type;
    }

    public void setOfficialAddress(Address officialAddress) {
        this.officialAddress = officialAddress;
    }

    @Override
    public boolean equals(Object obj) {
        Organization org = (Organization) obj;
       if(this.id == org.id || this == obj)
            return true;
       else
            return false;
    }

    @Override
    public String toString() {
        return "\n" + "ID: " + id
                + "\n" + "name: " + name
                + "\n" + "coordinatesX: " + coordinates.getX()
                + "\n" + "coordinatesY: " + coordinates.getY()
                + "\n" + "creationDate: " + creationDate
                + "\n" + "annualTurnover: " + annualTurnover
                + "\n" + "employeesCount: " + employeesCount
                + "\n" + "type: " + type
                + "\n" + "officialAddress: " + officialAddress
                + "\n" + "hashCode: " + hashCode()
                + "\n" + "---------------------------------------";
    }


    @Override
    public int compareTo(Organization o) {
            return Long.valueOf(this.getId()).compareTo(o.getId());
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}


