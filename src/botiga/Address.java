package botiga;

public class Address {
    private int pc;
    private String city;
    private String province;
    private String street;

    public Address() {
    }

    public Address(int cp, String stree, String cit, String provinc) {
        this.pc = cp;
        this.street = stree;
        this.city = cit;
        this.province = provinc;
    }

    
    public int getPc() {
        return pc;
    }

    public void setPc(int pc) {
        this.pc = pc;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return "Adreca [cp=" + pc + ", domicili=" + street + ", poblacio=" + city + ", provincia=" + province
                + "]";
    }
}
