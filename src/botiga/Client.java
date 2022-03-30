package botiga;

import java.time.LocalDate;
import java.util.LinkedHashSet;

public class Client extends Person {
    public Client(int idperson, String dNI, String name, String lastName, LocalDate birthDate, String email,
    LinkedHashSet phone, Address adress) {
        super(idperson, dNI, name, lastName, birthDate, email, phone, adress);
    }

    public Client(int idperson, String dNI, String name, String lastName) {
        super(idperson, dNI, name, lastName);
    }
}
