package dates;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class ClientDAO implements Persistable {
    private TreeMap<Integer, Client> map = new TreeMap<>();

    public TreeMap<Integer, Client> getMap() {
        return map;
    }

    public void add(Object obj) {
        Client c = (Client) obj;
        if (!map.containsKey(c.getId())) {
            this.map.put(c.getId(), c);
        }
    }

    public List<Client> getAll(){
        List<Client> list = new ArrayList<>();
        for (Integer key: map.keySet()) {
            list.add(map.get(key));
        }
        return list;
    }

    public void update(Client c) {
        if (map.containsKey(c.getId())) {
            this.map.put(c.getId(), c);
        }
    }

    public Client get(Integer id) {
        return this.map.get(id);
    }

    public Integer getLastId() {
        Integer bigger=0;
        for (Integer key: map.keySet()) {
            if(bigger < key) bigger = key;
        }
        return bigger;
    }

    public void delete(Integer id) {
        this.map.remove(id);
    }

    public String save() {
        try {
            ObjectOutputStream outmap=new ObjectOutputStream(new FileOutputStream("Clientes.dat"));
            outmap.writeObject(this.map);
        } catch (IOException e) {
            return "A problem has occurred with the file";
        }
        
        return "HashMap saved succesfully!";
    }

    public String load() {
        try {
            ObjectInputStream inmap=new ObjectInputStream(new FileInputStream("Clientes.dat"));
            this.map = (TreeMap)inmap.readObject();
        } catch (IOException e) {
            return "A problem has occurred with the file";
        } catch (ClassNotFoundException e) {
            return "Class has not been found!";
        }
        
        return "HashMap loaded succesfully!";
    }
}
