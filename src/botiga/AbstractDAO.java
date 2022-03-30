package botiga;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class AbstractDAO<T extends Identificable> implements Persistable<T> {
    private TreeMap<Integer, T> map = new TreeMap<>();

    public TreeMap<Integer, T> getMap() {
        return map;
    }

    public void add(T t) {
        if (!map.containsKey(t.getId())) {
            this.map.put(t.getId(), t);
        }
    }

    public List<T> getAll(){
        List<T> list = new ArrayList<>();
        for (Integer key: map.keySet()) {
            list.add(map.get(key));
        }
        return list;
    }

    public void update(T t) {
        if (map.containsKey(t.getId())) {
            this.map.put(t.getId(), t);
        }
    }

    public T get(Integer id) {
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
            ObjectOutputStream outmap=new ObjectOutputStream(new FileOutputStream("productes.dat"));
            outmap.writeObject(this.map);
        } catch (IOException e) {
            return "A problem has occurred with the file";
        }
        
        return "HashMap saved succesfully!";
    }

    public String load() {
        try {
            ObjectInputStream inmap=new ObjectInputStream(new FileInputStream("productes.dat"));
            this.map = (TreeMap)inmap.readObject();
        } catch (IOException e) {
            return "A problem has occurred with the file";
        } catch (ClassNotFoundException e) {
            return "Class has not been found!";
        }
        
        return "HashMap loaded succesfully!";
    }
}
