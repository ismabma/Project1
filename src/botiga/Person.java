package botiga;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Locale.Category;

public abstract class Person implements Identificable {
    private Integer idperson;
    private String DNI;
    private String name;
    private String lastName;
    private LocalDate birthDate;
    private String Email;
    private LinkedHashSet tlf;
    private Address address;
    static int numPeople;

    // Contructor 1
    public Person(Integer idperso, String dNI, String nam, String lastNam) {
        if (isValid(dNI, nam, lastNam)) {
            this.idperson = idperso;
            this.DNI = dNI;
            this.name = nam;
            this.lastName = lastNam;
            numPeople++;
        }
    }

    // Contructor 2
    public Person(Integer idperso, String dNI, String nam, String lastNam, LocalDate birthDat, String email,
        LinkedHashSet tlf, Address address) {
        if (isValid(dNI, nam, lastNam)) {
            this.idperson = idperso;
            this.DNI = dNI;
            this.name = nam;
            this.lastName = lastNam;
            this.birthDate = birthDat;
            this.Email = email;
            this.tlf = tlf;
            this.address = address;
            numPeople++;
        }
    }

    // Setters
    public void setAdreca(Address address) {
        this.address = address;
    }

    public void setCognoms(String lastNam) {
        this.lastName = lastNam;
    }

    public void setDNI(String dNI) {
        DNI = dNI;
    }

    public void setData_naixement(LocalDate birthDat) {
        this.birthDate = birthDat;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setId(Integer idperso) {
        this.idperson = idperso;
    }

    public void setNom(String nam) {
        this.name = nam;
    }

    public void setTlf(LinkedHashSet tlf) {
        this.tlf = tlf;
    }

    // Getters
    public Address getAdreca() {
        return address;
    }

    public String getCognoms() {
        return lastName;
    }

    public String getDNI() {
        return DNI;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getEmail() {
        return Email;
    }

    public Integer getId() {
        return idperson;
    }

    public String getNom() {
        return name;
    }

    public LinkedHashSet getTlf() {
        return tlf;
    }

    public int getNumPersones() {
        return numPeople;
    }

    public int getEdad() {
        return (int) ChronoUnit.YEARS.between(this.birthDate, LocalDate.now());
    }

    public static long diferenciaEdad(Person p1, Person p2) {
        return ChronoUnit.YEARS.between(p1.birthDate, p2.birthDate);
    }

    @Override
    public String toString() {
        return "ID: " + this.idperson + "\n" + "DNI: " + this.DNI + "\n" + "Email: " + this.Email + "\n" + "Cognoms: "
                + this.lastName + "\n" + "Nom: " + this.name + "\n" + "Telefon: " + this.tlf + "\n" + "Adreca: "
                + this.address.toString() + "\n" + "Edat: " + this.getEdad() + "\n";
    }

    private boolean isValid(String dNI, String nam, String lastNam) {
        if (dNI != null && nam != null && lastNam != null) {
            return true;
        } else {
            return false;
        }
    }

}
