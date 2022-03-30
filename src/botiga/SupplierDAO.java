package botiga;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class SupplierDAO implements Persistable {
    private TreeMap<Integer, Supplier> map = new TreeMap<>();

    public TreeMap<Integer, Supplier> getMap() {
        return map;
    }

    public void add(Object obj) {
        Supplier s = (Supplier) obj;
        if (!map.containsKey(s.getId())) {
            this.map.put(s.getId(), s);
        }
    }

    public List<Supplier> getAll(){
        List<Supplier> list = new ArrayList<>();
        for (Integer key: map.keySet()) {
            list.add(map.get(key));
        }
        return list;
    }

    public void update(Supplier s) {
        if (map.containsKey(s.getId())) {
            this.map.put(s.getId(), s);
        }
    }

    public Supplier get(Integer id) {
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
            ObjectOutputStream outmap=new ObjectOutputStream(new FileOutputStream("Supplieres.dat"));
            outmap.writeObject(this.map);
        } catch (IOException e) {
            return "A problem has occurred with the file";
        }
        
        return "HashMap saved succesfully!";
    }

    public String load() {
        try {
            ObjectInputStream inmap=new ObjectInputStream(new FileInputStream("Supplieres.dat"));
            this.map = (TreeMap)inmap.readObject();
        } catch (IOException e) {
            return "A problem has occurred with the file";
        } catch (ClassNotFoundException e) {
            return "Class has not been found!";
        }
        
        return "HashMap loaded succesfully!";
    }
}
