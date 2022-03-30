package botiga;

import java.time.LocalDate;
import java.util.LinkedHashSet;

public class Supplier extends Person {
    public Supplier(int idperson, String dNI, String name, String lastName, LocalDate birthDate, String email,
    LinkedHashSet phone, Address adress) {
        super(idperson, dNI, name, lastName, birthDate, email, phone, adress);
    }

    public Supplier(int idperson, String dNI, String name, String lastName) {
        super(idperson, dNI, name, lastName);
    }
}
