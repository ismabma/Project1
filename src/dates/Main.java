package dates;

import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main{
    /**
     * Per quan introdueix una opcio que no existeix
     */
    private static final String NONSENSE = "Eso no tiene ningun sentido!";
    /**
     * Text de introduir la teva opcio
     */
    private static final String INSERT_OPTION = "Introdueix la teva opcio: ";
    /**
     * Opcio de sortir del programa
     */
    private static final String Q_EXIT = "q)Sortir del programa";
    public static void main(String[] args) throws SecurityException{
        menu();
    }

    private static void menu() throws SecurityException{
        char option = ' ';
        SimpleFormatter simpleFormatter = new SimpleFormatter();
        Logger logger = Logger.getLogger("DAO");
        Scanner scanner = new Scanner(System.in);
        try{
            Handler fileHandler = new FileHandler("./dao.log", false);
            fileHandler.setFormatter(simpleFormatter);
            logger.addHandler(fileHandler);
        } catch(IOException e){
            e.printStackTrace();
            System.out.println("No s'ha trobat l'arxiu");
        }
        

            do {
                System.out.println("######################## MENU DE INICI ########################");
                System.out.println("a)MENU DE CLIENTS");
                System.out.println("b)MENU DE PROVEIDORS");
                System.out.println("c)MENU DE PRODUCTES");
                System.out.println("c)MENU DE PRESENCIA");
                System.out.println(Q_EXIT);
                System.out.print(INSERT_OPTION);
                try {
                    option = scanner.nextLine().charAt(0);
                    switch (option) {
                    case 'a':
                        menuClient(logger);
                        break;
                    case 'b':
                        menuSupplier(logger);
                        break;
                    case 'c':
                        menuProduct(logger);
                        break;
                    case 'd':
                        menuPresencia(logger);
                        break;
                    case 'q':
                        System.out.println("Que la fuerza te acompañe joven padawan");
                        break;
                    default:
                        System.out.println(NONSENSE);
                        break;
                    }
                } catch (StringIndexOutOfBoundsException e) {
                    logger.info(e.toString());
                }

            } while (option != 'q');
            scanner.close();
    }

    private static void menuPresencia(Logger logger) {
        Scanner scanner = new Scanner(System.in);
        char opcion = ' ';
        RegistrePresenciaDAO registrePresencia = new RegistrePresenciaDAO();
        Integer idpersona;
        // String dni;
        // String name;
        // String lastName;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date;
        LocalDate presenceDate;
        String time;
        LocalTime presenceTime;
        Presencia p;
        // String email;
        // int tlf;
        // LinkedHashSet tlfs = new LinkedHashSet<>();
        // Address adress = new Address();

        do {
            System.out.println("######################## MENU DE GESTIO DE CLIENTS ########################");
            System.out.println("a)Fitxar entrada un dia concreta");
            System.out.println("b)Fitxar sortida un dia on s'hagi fet entrada");
            System.out.println("c)Consultar fitxada d'un dia i treballador");
            System.out.println(Q_EXIT);
            System.out.print(INSERT_OPTION);
            try{
                opcion = scanner.nextLine().charAt(0);
                switch (opcion) {
                case 'a':
                    System.out.println("Introdueix l'id:");
                    try {
                        idpersona = scanner.nextInt();
                        scanner.nextLine();
                    } catch (StringIndexOutOfBoundsException | InputMismatchException e) {
                        idpersona = 10;
                    }

                    System.out.println("Introdueix la data de fitxatge: (YYYY-MM-DD)");
                    try {
                        date = scanner.nextLine();
                        presenceDate = LocalDate.parse(date);
                    } catch (DateTimeParseException e) {
                        date = simpleDateFormat.format(new Date());
                        presenceDate = LocalDate.parse(date);
                        logger.info(e.toString());
                    }

                    System.out.println("Introdueix la hora de fitxatge: (HH-MM-SS)");
                    try {
                        time = scanner.nextLine();
                        presenceTime = LocalTime.parse(time);
                    } catch (DateTimeParseException e) {
                        presenceTime = LocalTime.now();
                        logger.info(e.toString());
                    }

                    p = new Presencia(idpersona, presenceDate, presenceTime);

                    registrePresencia.addStart(p);
                    break;
                case 'b':
                    System.out.println("Introdueix l'id:");
                    try {
                        idpersona = scanner.nextInt();
                        scanner.nextLine();
                    } catch (StringIndexOutOfBoundsException | InputMismatchException e) {
                        idpersona = 10;
                    }

                    System.out.println("Introdueix la data de fitxatge: (YYYY-MM-DD)");
                    try {
                        date = scanner.nextLine();
                        presenceDate = LocalDate.parse(date);
                    } catch (DateTimeParseException e) {
                        date = simpleDateFormat.format(new Date());
                        presenceDate = LocalDate.parse(date);
                        logger.info(e.toString());
                    }

                    System.out.println("Introdueix la hora de fitxatge: (HH-MM-SS)");
                    try {
                        time = scanner.nextLine();
                        presenceTime = LocalTime.parse(time);
                    } catch (DateTimeParseException e) {
                        presenceTime = LocalTime.now();
                        logger.info(e.toString());
                    }

                    p = new Presencia(idpersona, presenceDate, presenceTime);

                    registrePresencia.addEnd(p);
                    break;
                case 'c':
                    System.out.println("Introdueix l'id:");
                    try {
                        idpersona = scanner.nextInt();
                        scanner.nextLine();
                    } catch (StringIndexOutOfBoundsException | InputMismatchException e) {
                        idpersona = 10;
                    }

                    System.out.println("Introdueix la data de fitxatge: (YYYY-MM-DD)");
                    try {
                        date = scanner.nextLine();
                        presenceDate = LocalDate.parse(date);
                    } catch (DateTimeParseException e) {
                        date = simpleDateFormat.format(new Date());
                        presenceDate = LocalDate.parse(date);
                        logger.info(e.toString());
                    }

                    p = new Presencia(idpersona, presenceDate);

                    p = registrePresencia.get(p);
                    System.out.println(p.toString());
                    break;
                case 'd':
                    
                    break;
                case 'e':
                    
                    break;
                default:
                    System.out.println(NONSENSE);
                    break;
                }
            
            } catch (StringIndexOutOfBoundsException e) {
                logger.info(e.toString());
            }

        } while (opcion != 'q');

        scanner.close();
    }

    private static void menuClient(Logger logger) {
        Scanner scanner = new Scanner(System.in);
        char opcion = ' ';
        ClientDAO clients = new ClientDAO();
        Integer idpersona;
        String dni;
        String name;
        String lastName;
        String date;
        LocalDate birthDate;
        String email;
        int tlf;
        LinkedHashSet tlfs = new LinkedHashSet<>();
        Address adress = new Address();

        do {
            System.out.println("######################## MENU DE GESTIO DE CLIENTS ########################");
            System.out.println("a)Afegir un client");
            System.out.println("b)Actualitzar un client");
            System.out.println("c)Cercar un client");
            System.out.println("d)Esborrar un client");
            System.out.println("e)Mostrar els clients");
            System.out.println(Q_EXIT);
            System.out.print(INSERT_OPTION);
            try{
                opcion = scanner.nextLine().charAt(0);
                switch (opcion) {
                case 'a':
                    System.out.println("Introdueix l'id:");
                    try {
                        idpersona = scanner.nextInt();
                        scanner.nextLine();
                    } catch (StringIndexOutOfBoundsException | InputMismatchException e) {
                        idpersona = clients.getLastId();
                        idpersona++;
                    }
                    System.out.println("Introdueix el nom:");
                    try {
                        name = scanner.nextLine();
                    } catch (StringIndexOutOfBoundsException e) {
                        name = "Jose";
                    }
                    System.out.println("Introdueix els cognoms:");
                    try {
                        lastName = scanner.nextLine();
                    } catch (StringIndexOutOfBoundsException e) {
                        lastName = "Luis Prado";
                    }
                    System.out.println("Introdueix la data de naixement: (YYYY-MM-DD)");
                    try {
                        date = scanner.nextLine();
                        birthDate = LocalDate.parse(date);
                    } catch (DateTimeParseException e) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        date = simpleDateFormat.format(new Date());
                        birthDate = LocalDate.parse(date);
                        logger.info(e.toString());
                    }
                    System.out.println("Introdueix l'email:");
                    try {
                        email = scanner.nextLine();
                    } catch (StringIndexOutOfBoundsException e) {
                        email = "raulapruebame@ibadia.cat";
                    }
                    System.out.println("Introdueix el DNI:");
                    try {
                        dni = scanner.nextLine();
                    } catch (StringIndexOutOfBoundsException e) {
                        dni = "56498062Y";
                    }
                    System.out.println("Introdueix el/s telefon/s:");
                    try {

                        do{
                            tlf = scanner.nextInt(); 
                            scanner.nextLine();
                            tlfs.add(tlf);
                        }while(tlf != 0);
                    } catch (StringIndexOutOfBoundsException | InputMismatchException e) {
                        tlf = 000000000;
                    }
                    System.out.println("Introdueix el codi postal:");
                    try {
                        int cp = scanner.nextInt();
                        scanner.nextLine();
                        adress.setPc(cp);
                    } catch (StringIndexOutOfBoundsException | InputMismatchException e) {
                        adress.setPc(28211);
                    }
                    System.out.println("Introdueix el Poblacio:");
                    try {
                        String city = scanner.nextLine();
                        adress.setCity(city);
                    } catch (StringIndexOutOfBoundsException e) {
                        adress.setCity("Badia");
                    }
                    System.out.println("Introdueix el Domicili:");
                    try {
                        String street = scanner.nextLine();
                        adress.setStreet(street);
                    } catch (StringIndexOutOfBoundsException e) {
                        adress.setStreet("Calle Hola botella 321");
                    }
                    System.out.println("Introdueix el Provincia:");
                    try {
                        String province = scanner.nextLine();
                        adress.setProvince(province);
                    } catch (StringIndexOutOfBoundsException e) {
                        adress.setProvince("Barcelona");
                    }
                    Client c = new Client(idpersona, dni, name, lastName, birthDate, email, tlfs, adress);

                    clients.add(c);
                    break;
                case 'b':
                    System.out.println("Introdueix l'id:");
                    try {
                        idpersona = scanner.nextInt();
                        scanner.nextLine();
                    } catch (StringIndexOutOfBoundsException | InputMismatchException e) {
                        idpersona = clients.getLastId();
                        idpersona++;
                    }
                    System.out.println("Introdueix el nom:");
                    try {
                        name = scanner.nextLine();
                    } catch (StringIndexOutOfBoundsException e) {
                        name = "Jose";
                    }
                    System.out.println("Introdueix els cognoms:");
                    try {
                        lastName = scanner.nextLine();
                    } catch (StringIndexOutOfBoundsException e) {
                        lastName = "Luis Prado";
                    }
                    System.out.println("Introdueix la data de naixement: (YYYY-MM-DD)");
                    try {
                        date = scanner.nextLine();
                        birthDate = LocalDate.parse(date);
                    } catch (DateTimeParseException e) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        date = simpleDateFormat.format(new Date());
                        birthDate = LocalDate.parse(date);
                        logger.info(e.toString());
                    }
                    System.out.println("Introdueix l'email:");
                    try {
                        email = scanner.nextLine();
                    } catch (StringIndexOutOfBoundsException e) {
                        email = "raul.apruebame@ibadia.cat";
                    }
                    System.out.println("Introdueix el DNI:");
                    try {
                        dni = scanner.nextLine();
                    } catch (StringIndexOutOfBoundsException e) {
                        dni = "56498062Y";
                    }
                    System.out.println("Introdueix el/s telefon/s:");
                    try {

                        do{
                            tlf = scanner.nextInt(); 
                            scanner.nextLine();
                            tlfs.add(tlf);
                        }while(tlf != 0);
                    } catch (StringIndexOutOfBoundsException | InputMismatchException e) {
                        tlf = 000000000;
                    }
                    System.out.println("Introdueix el codi postal:");
                    try {
                        int cp = scanner.nextInt();
                        scanner.nextLine();
                        adress.setPc(cp);
                    } catch (StringIndexOutOfBoundsException | InputMismatchException e) {
                        adress.setPc(28211);
                    }
                    System.out.println("Introdueix el Poblacio:");
                    try {
                        String city = scanner.nextLine();
                        adress.setCity(city);
                    } catch (StringIndexOutOfBoundsException e) {
                        adress.setCity("Badia");
                    }
                    System.out.println("Introdueix el Domicili:");
                    try {
                        String street = scanner.nextLine();
                        adress.setStreet(street);
                    } catch (StringIndexOutOfBoundsException e) {
                        adress.setStreet("Calle Hola botella 321");
                    }
                    System.out.println("Introdueix el Provincia:");
                    try {
                        String province = scanner.nextLine();
                        adress.setProvince(province);
                    } catch (StringIndexOutOfBoundsException e) {
                        adress.setProvince("Barcelona");
                    }
                    c = new Client(idpersona, dni, name, lastName, birthDate, email, tlfs, adress);

                    clients.update(c);
                    break;
                case 'c':
                    System.out.println("Introdueix l'id:");
                    idpersona = scanner.nextInt();
                    scanner.nextLine();

                    clients.get(idpersona);
                    break;
                case 'd':
                    System.out.println("Introdueix l'id:");
                    idpersona = scanner.nextInt();
                    scanner.nextLine();
                    clients.delete(idpersona);
                    break;
                case 'e':
                    Persistable<Client> per = clients;
                    printMap(per);
                    break;
                default:
                    System.out.println(NONSENSE);
                    break;
                }
            
            } catch (StringIndexOutOfBoundsException e) {
                logger.info(e.toString());
            }

        } while (opcion != 'q');

        scanner.close();
    }

    private static void menuSupplier(Logger logger) {
        Scanner scanner = new Scanner(System.in);
        char option;
        SupplierDAO suppliers = new SupplierDAO();
        Integer idperson;
        String dni;
        String name;
        String lastName;
        String date;
        LocalDate birthDate;
        String email;
        int tlf;
        LinkedHashSet tlfs = new LinkedHashSet<>();
        Address address = new Address();

        do {
            System.out.println("######################## MENU DE GESTIO DE PROVEIDORS ########################");
            System.out.println("a)Afegir un proveidor");
            System.out.println("b)Actualitzar un proveidor");
            System.out.println("c)Cercar un proveidor");
            System.out.println("d)Esborrar un proveidor");
            System.out.println("e)Mostrar els proveidor");
            System.out.println(Q_EXIT);
            System.out.print(INSERT_OPTION);
            option = scanner.nextLine().charAt(0);
            switch (option) {
            case 'a':
                System.out.println("Introdueix l'id:");
                try {
                    idperson = scanner.nextInt();
                    scanner.nextLine();
                } catch (StringIndexOutOfBoundsException | InputMismatchException e) {
                    idperson = suppliers.getLastId();
                    idperson++;
                }
                System.out.println("Introdueix el nom:");
                try {
                    name = scanner.nextLine();
                } catch (StringIndexOutOfBoundsException e) {
                    name = "Jose";
                }
                System.out.println("Introdueix els cognoms:");
                try {
                    lastName = scanner.nextLine();
                } catch (StringIndexOutOfBoundsException e) {
                    lastName = "Luis Prado";
                }
                System.out.println("Introdueix la data de naixement: (YYYY-MM-DD)");
                try {
                    date = scanner.nextLine();
                    birthDate = LocalDate.parse(date);
                } catch (DateTimeParseException e) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    date = simpleDateFormat.format(new Date());
                    birthDate = LocalDate.parse(date);
                    logger.info(e.toString());
                }
                System.out.println("Introdueix l'email:");
                try {
                    email = scanner.nextLine();
                } catch (StringIndexOutOfBoundsException e) {
                    email = "raulapruebame@ibadia.cat";
                }
                System.out.println("Introdueix el DNI:");
                try {
                    dni = scanner.nextLine();
                } catch (StringIndexOutOfBoundsException e) {
                    dni = "56498062Y";
                }
                System.out.println("Introdueix el/s telefon/s:");
                    try {

                        do{
                            tlf = scanner.nextInt(); 
                            scanner.nextLine();
                            tlfs.add(tlf);
                        }while(tlf != 0);
                    } catch (StringIndexOutOfBoundsException | InputMismatchException e) {
                        tlf = 000000000;
                    }
                System.out.println("Introdueix el codi postal:");
                try {
                    int cp = scanner.nextInt();
                    scanner.nextLine();
                    address.setPc(cp);
                } catch (StringIndexOutOfBoundsException | InputMismatchException e) {
                    address.setPc(28211);
                }
                System.out.println("Introdueix el Poblacio:");
                try {
                    String city = scanner.nextLine();
                    address.setCity(city);
                } catch (StringIndexOutOfBoundsException e) {
                    address.setCity("Badia");
                }
                System.out.println("Introdueix el Domicili:");
                try {
                    String street = scanner.nextLine();
                    address.setStreet(street);
                } catch (StringIndexOutOfBoundsException e) {
                    address.setStreet("Calle Hola botella 321");
                }
                System.out.println("Introdueix el Provincia:");
                try {
                    String province = scanner.nextLine();
                    address.setProvince(province);
                } catch (StringIndexOutOfBoundsException e) {
                    address.setProvince("Barcelona");
                }
                Supplier s = new Supplier(idperson, dni, name, lastName, birthDate, email, tlfs, address);

                suppliers.add(s);
                break;
            case 'b':
                System.out.println("Introdueix l'id:");
                try {
                    idperson = scanner.nextInt();
                    scanner.nextLine();
                } catch (StringIndexOutOfBoundsException | InputMismatchException e) {
                    idperson = suppliers.getLastId();
                    idperson++;
                }
                System.out.println("Introdueix el nom:");
                try {
                    name = scanner.nextLine();
                } catch (StringIndexOutOfBoundsException e) {
                    name = "Jose";
                }
                System.out.println("Introdueix els cognoms:");
                try {
                    lastName = scanner.nextLine();
                } catch (StringIndexOutOfBoundsException e) {
                    lastName = "Luis Prado";
                }
                System.out.println("Introdueix la data de naixement: (YYYY-MM-DD)");
                try {
                    date = scanner.nextLine();
                    birthDate = LocalDate.parse(date);
                } catch (DateTimeParseException e) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    date = simpleDateFormat.format(new Date());
                    birthDate = LocalDate.parse(date);
                    logger.info(e.toString());
                }
                System.out.println("Introdueix l'email:");
                try {
                    email = scanner.nextLine();
                } catch (StringIndexOutOfBoundsException e) {
                    email = "raulapruebame@ibadia.cat";
                }
                System.out.println("Introdueix el DNI:");
                try {
                    dni = scanner.nextLine();
                } catch (StringIndexOutOfBoundsException e) {
                    dni = "56498062Y";
                }
                System.out.println("Introdueix el/s telefon/s:");
                    try {

                        do{
                            tlf = scanner.nextInt(); 
                            scanner.nextLine();
                            if(tlfs.contains(tlf)){
                                System.out.println("El telefon ja esta ficat a la llista!");
                            }
                            tlfs.add(tlf);
                        }while(tlf != 0);
                    } catch (StringIndexOutOfBoundsException | InputMismatchException e) {
                        tlf = 000000000;
                    }
                System.out.println("Introdueix el codi postal:");
                try {
                    int cp = scanner.nextInt();
                    scanner.nextLine();
                    address.setPc(cp);
                } catch (StringIndexOutOfBoundsException | InputMismatchException e) {
                    address.setPc(28211);
                }
                System.out.println("Introdueix el Poblacio:");
                try {
                    String city = scanner.nextLine();
                    address.setCity(city);
                } catch (StringIndexOutOfBoundsException e) {
                    address.setCity("Badia");
                }
                System.out.println("Introdueix el Domicili:");
                try {
                    String street = scanner.nextLine();
                    address.setStreet(street);
                } catch (StringIndexOutOfBoundsException e) {
                    address.setStreet("Calle Hola botella 321");
                }
                System.out.println("Introdueix el Provincia:");
                try {
                    String province = scanner.nextLine();
                    address.setProvince(province);
                } catch (StringIndexOutOfBoundsException e) {
                    address.setProvince("Barcelona");
                }
                s = new Supplier(idperson, dni, name, lastName, birthDate, email, tlfs, address);

                suppliers.update(s);
                break;
            case 'c':
                System.out.println("Introdueix l'id:");
                try {
                    idperson = scanner.nextInt();
                    scanner.nextLine();
                } catch (StringIndexOutOfBoundsException | InputMismatchException e) {
                    idperson = suppliers.getLastId();
                    idperson++;
                }

                suppliers.get(idperson);
                break;
            case 'd':
                System.out.println("Introdueix l'id:");
                try {
                    idperson = scanner.nextInt();
                    scanner.nextLine();
                } catch (StringIndexOutOfBoundsException | InputMismatchException e) {
                    idperson = suppliers.getLastId();
                    idperson++;
                }
                suppliers.delete(idperson);
                break;
            case 'e':
                Persistable<Supplier> per = suppliers;
                printMap(per);
                break;
            default:
                System.out.println(NONSENSE);
                break;
            }

        } while (option != 'q');

        scanner.close();
    }

    private static void menuProduct(Logger logger) {
        Scanner scanner = new Scanner(System.in);
        char option;
        ProductsDAO products = new ProductsDAO();
        Integer idproduct = 0;
        String name;
        double priceSell = 0;
        int stock = 0;
        int discount = 0;
        String pop;
        String date;
        LocalDate startDate;
        LocalDate endDate;
        ArrayList outdated = new ArrayList<Product>();

        do {
            System.out.println("######################## MENU DE GESTIO DE PRODUCTES ########################");
            System.out.println("a)Afegir un producte o un pack");
            System.out.println("b)Actualitzar un producte o un pack");
            System.out.println("c)Cercar un producte o un pack");
            System.out.println("d)Esborrar un producte o un pack");
            System.out.println("e)Mostrar els productes i els packs");
            System.out.println("f)Afegir stock");
            System.out.println("g)Guardar productes");
            System.out.println("h)Carregar productes");
            System.out.println("i)Fer comanda");
            System.out.println("j)Imprimir productes ordenats");
            System.out.println("k)Imprimir productes descatalogats");
            System.out.println(Q_EXIT);
            System.out.print(INSERT_OPTION);
            option = scanner.nextLine().charAt(0);
            switch (option) {
            case 'a':
                System.out.println("Introdueix l'id:");
                try {
                    idproduct = scanner.nextInt();
                    scanner.nextLine();
                } catch (StringIndexOutOfBoundsException | InputMismatchException e) {
                    idproduct = products.getLastId();
                    idproduct++;
                }
                System.out.println("Introdueix el nom:");
                try {
                    name = scanner.nextLine();
                } catch (StringIndexOutOfBoundsException e) {
                    name = "Canicas";
                }
                System.out.println("Introdueix el preu de venta:");
                try {
                    priceSell = scanner.nextDouble();
                    scanner.nextLine();
                } catch (StringIndexOutOfBoundsException | InputMismatchException e) {
                    priceSell = 5;
                }
                System.out.println("Introdueix l'stock:");
                try {
                    stock = scanner.nextInt();
                    scanner.nextLine();
                } catch (StringIndexOutOfBoundsException | InputMismatchException e) {
                    stock = products.getLastId();
                    stock++;
                }
                System.out.println("Introdueix la data d'inici de cataleg: (DD-MM-YYYY)");
                try {
                    date = scanner.nextLine();
                    startDate = LocalDate.parse(date);
                } catch (DateTimeParseException e) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    date = simpleDateFormat.format(new Date());
                    startDate = LocalDate.parse(date);
                    logger.info(e.toString());
                }
                System.out.println("Introdueix la data de fi de cataleg: (DD-MM-YYYY)");
                try {
                    date = scanner.nextLine();
                    endDate = LocalDate.parse(date);
                } catch (DateTimeParseException e) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    date = simpleDateFormat.format(new Date());
                    endDate = LocalDate.parse(date);
                    logger.info(e.toString());
                }
                System.out.println("Es un 'pack' o un 'producte'?");
                try {
                    pop = scanner.nextLine();
                } catch (StringIndexOutOfBoundsException e) {
                    pop = "producte";
                }
                if (pop.equals("pack")) {
                    System.out.println("Introdueix el descompte:");
                    try {
                        discount = scanner.nextInt();
                        scanner.nextLine();
                    } catch (StringIndexOutOfBoundsException | InputMismatchException e) {
                        discount = 10;
                    }
                }

                if (pop.equals("pack")) {
                    Pack pa = new Pack(idproduct, name, priceSell, stock, discount, startDate, endDate);
                    boolean exists = false;
                    for(Map.Entry<Integer,Product> entry : products.getMap().entrySet()) {
                        if(entry.getValue().equals(pa)) exists = true;
                    }
                    if(!exists)products.add(pa);
                } else {
                    Product p = new Product(idproduct, name, priceSell, stock, startDate, endDate);
                    products.add(p);
                }
                break;
            case 'b':
                System.out.println("Introdueix l'id:");
                try {
                    idproduct = scanner.nextInt();
                    scanner.nextLine();
                } catch (StringIndexOutOfBoundsException | InputMismatchException e) {
                    idproduct = products.getLastId();
                    idproduct++;
                }
                System.out.println("Introdueix el nom:");
                try {
                    name = scanner.nextLine();
                } catch (StringIndexOutOfBoundsException e) {
                    name = "Canicas";
                }
                System.out.println("Introdueix el preu de venta:");
                try {
                    priceSell = scanner.nextDouble();
                    scanner.nextLine();
                } catch (StringIndexOutOfBoundsException | InputMismatchException e) {
                    priceSell = 5;
                }
                scanner.nextLine();
                System.out.println("Introdueix l'stock:");
                try {
                    stock = scanner.nextInt();
                    scanner.nextLine();
                } catch (StringIndexOutOfBoundsException | InputMismatchException e) {
                    stock = 0;
                }
                System.out.println("Introdueix la data d'inici de cataleg: (DD-MM-YYYY)");
                try {
                    date = scanner.nextLine();
                    startDate = LocalDate.parse(date);
                } catch (DateTimeParseException e) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    date = simpleDateFormat.format(new Date());
                    startDate = LocalDate.parse(date);
                    logger.info(e.toString());
                }
                System.out.println("Introdueix la data de fi de cataleg: (DD-MM-YYYY)");
                try {
                    date = scanner.nextLine();
                    endDate = LocalDate.parse(date);
                } catch (DateTimeParseException e) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    date = simpleDateFormat.format(new Date());
                    endDate = LocalDate.parse(date);
                    logger.info(e.toString());
                }
                System.out.println("Es un 'pack' o un 'producte'?");
                try {
                    pop = scanner.nextLine();
                } catch (StringIndexOutOfBoundsException e) {
                    pop = "producte";
                }
                if (pop.equals("pack")) {
                    System.out.println("Introdueix el descompte:");
                    try {
                        discount = scanner.nextInt();
                        scanner.nextLine();
                    } catch (StringIndexOutOfBoundsException | InputMismatchException e) {
                        discount = 10;
                    }
                }

                if (pop.equals("pack")) {
                    Pack pa = new Pack(idproduct, name, priceSell, stock, discount, startDate, endDate);
                    products.update(pa);
                } else {
                    Product p = new Product(idproduct, name, priceSell, stock, startDate, endDate);
                    products.update(p);
                }
                break;
            case 'c':
                try {
                    idproduct = scanner.nextInt();
                    scanner.nextLine();
                } catch (StringIndexOutOfBoundsException | InputMismatchException e) {
                    idproduct = products.getLastId();
                    idproduct++;
                }

                products.get(idproduct);
                break;
            case 'd':
                try {
                    idproduct = scanner.nextInt();
                    scanner.nextLine();
                } catch (StringIndexOutOfBoundsException | InputMismatchException e) {
                    idproduct = products.getLastId();
                    idproduct++;
                }
                products.delete(idproduct);
                break;
            case 'e':
                Persistable<Product> per = products;
                printMap(per);
                break;
            case 'f':
                String at = "";
                String am = "";
                System.out.println("Vols afegir o treure stock? ---  a (afegir) / r (treure)");
                try {
                    at = scanner.nextLine();
                } catch (StringIndexOutOfBoundsException | InputMismatchException e) {
                    at = "a";
                }
                
                if(at.equals("a") || at.equals("A")){
                    System.out.println("Vols afegir manualment o de manera automatica? ---  M (manual) / a (automatica)");
                    try {
                        am = scanner.nextLine();
                    } catch (StringIndexOutOfBoundsException | InputMismatchException e) {
                        am = "a";
                    }
                    if(am.equals("a") || am.equals("A")){
                        try {
                            DataInputStream stockFile = new DataInputStream(new FileInputStream("comanda_rebuda.txt"));
                            while (stockFile.available() > 0) {
                                String id = stockFile.readUTF();
                                Integer idn = Integer.parseInt(id);
                                int stockToAdd = stockFile.readInt();
                                if(products.get(idn) == null){
                                    Product p = products.get(idn);
                                    p.putStock(stockToAdd);
                                }
                            }
                        } catch (FileNotFoundException e) {
                            logger.info("File has not been found!");
                        } catch (IOException e) {
                            logger.info("Some error with the file has occurred!");
                        }
                    } else{
                        System.out.println("Quant stock vols afegir/treure:");
                        try {
                            stock = scanner.nextInt();
                            scanner.nextLine();
                        } catch (StringIndexOutOfBoundsException | InputMismatchException e) {
                            stock = 0;
                        }
                    }
                } else{
                    System.out.println("Quant stock vols afegir/treure:");
                    try {
                        stock = scanner.nextInt();
                        scanner.nextLine();
                    } catch (StringIndexOutOfBoundsException | InputMismatchException e) {
                        stock = 0;
                    }
                }
                if(am.equals("m") || am.equals("M")){
                    System.out.println("Introdueix l'id:");
                    try {
                        idproduct = scanner.nextInt();
                        scanner.nextLine();
                    } catch (StringIndexOutOfBoundsException | InputMismatchException e) {
                        idproduct = products.getLastId();
                        idproduct++;
                    }
                    
                    System.out.println("Es un 'pack' o un 'producte'?");
                    try {
                        pop = scanner.nextLine();
                    } catch (StringIndexOutOfBoundsException e) {
                        pop = "producte";
                    }
    
                    
                    if (pop.equals("pack") && (am.equals("m") || am.equals("M"))) {
                        Pack pa = (Pack) products.get(idproduct);
                        if(at.charAt(0) == 'a' || at.charAt(0) ==  'A'){
                            pa.putStock(stock);
                            products.update(pa);
                        } else if(at.charAt(0) == 'r' || at.charAt(0) ==  'R'){
                            try {
                                pa.takeStock(stock);
                                products.update(pa);
                            } catch (StockInsuficientException e) {
                                logger.info(e.getMessage());
                            }
                        }
                    } else if(am.equals("m") || am.equals("M")) {
                        Product p = products.get(idproduct);
                        if(at.charAt(0) == 'a' || at.charAt(0) ==  'A'){
                            p.putStock(stock);
                            products.update(p);
                        } else if(at.charAt(0) == 'r' || at.charAt(0) ==  'R'){
                            try {
                                p.takeStock(stock);
                            } catch (StockInsuficientException e) {
                                logger.info(e.getMessage());
                            }
                            products.update(p);
                        }
                    }
                }
                break;
            case 'g':
                String msg = products.save();
                logger.info(msg); 
                break;
            case 'h':
                msg = products.load();
                logger.info(msg); 
                break;
            case 'q':
                msg = products.save();
                logger.info(msg); 
                break;
            case 'i':
                ArrayList<Integer> order =  new ArrayList<Integer>();
                while(idproduct == -1){
                    System.out.println("Introdueix l'id del producte que vol comprar: (-1 per deixar de comprar)");
                    try {
                        idproduct = scanner.nextInt();
                        scanner.nextLine();
                    } catch (StringIndexOutOfBoundsException | InputMismatchException e) {
                        idproduct = products.getLastId();
                        idproduct++;
                    }
                    
                    if (idproduct != -1) {
                        System.out.println("Introdueix la quantitat:");
                        try {
                            stock = scanner.nextInt();
                            scanner.nextLine();
                            order.add(idproduct);
                            order.add(stock);
                        } catch (StringIndexOutOfBoundsException | InputMismatchException e) {
                            stock = 0;
                        }  
                    }
                }
                if(order.size() > 0){
                    String file;
                    System.out.println("Introdueix el nom de l'arxiu a desar la comanda:");
                    try {
                        file = scanner.nextLine();
                        DataOutputStream out = new DataOutputStream(new FileOutputStream(file));
                        for (int i = 0; i < order.size(); i++) { 
                            out.writeInt(order.get(i));
                        }
                    } catch (StringIndexOutOfBoundsException e) {
                        file = "comanda00.txt";
                    } catch (FileNotFoundException e) {
                        logger.warning("File has not been found: " + e.toString());
                    } catch (IOException e) {
                        logger.warning("Problem with file has been encountered: " + e.toString());
                    }

                }
                break;
                case 'j':
                char sort = ' ';
                List<String> list = new ArrayList<String>();
                TreeMap map = products.getMap();
                list.addAll(map.entrySet());
                System.out.println("Introdueix com vols ordenar els productes: (\"0\" nom, \"1\" preu, \"2\" stock)");
                try {
                    sort = scanner.nextLine().charAt(0);
                } catch (StringIndexOutOfBoundsException e) {
                    sort = '0';
                }
                if(sort  == '0'){
                    Collections.sort(list, new ProductComparatorName());
                    System.out.println("Ordenat per nom:");
                } else if(sort  == '1'){
                    Collections.sort(list, new ProductComparatorPrice());
                    System.out.println("Ordenat per preu:");
                } else{
                    Collections.sort(list, new ProductComparatorStock());
                    System.out.println("Ordenat per stock:");
                }
                System.out.println(list);
                break;
                case 'k':
                System.out.println("Introdueix la data d'inici de cataleg: (DD-MM-YYYY)");
                try {
                    date = scanner.nextLine();
                    startDate = LocalDate.parse(date);
                } catch (DateTimeParseException e) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    date = simpleDateFormat.format(new Date());
                    startDate = LocalDate.parse(date);
                    logger.info(e.toString());
                }
                outdated = products.showOutdated(startDate);
                System.out.println(outdated.toString());
                break;
            default:
                System.out.println(NONSENSE);
                break;
            }

        } while (option != 'q');

        scanner.close();

    }

    private static <T> void printMap(Persistable<T> map) {
        System.out.println(map.getMap().toString());
    }

}
