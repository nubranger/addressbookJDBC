package lt.bit.data;

public class Address {

    private Integer id;
    private String address;
    private String city;
    private String postalCode;
    private Integer personId;

    public Address() {
    }

    public Address(String address, String city, String postalCode, Integer personId) {
        this.id = id;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.personId = personId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", personId=" + personId +
                '}';
    }
}
