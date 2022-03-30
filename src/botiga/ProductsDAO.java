package botiga;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ProductsDAO implements Persistable {
    private TreeMap<Integer, Product> map = new TreeMap<>();

    public TreeMap<Integer, Product> getMap() {
        return map;
    }

    public void add(Object obj) {
        Product p = (Product) obj;
        if (!map.containsKey(p.getId())) {
            this.map.put(p.getId(), p);
        }
    }

    public List<Product> getAll(){
        List<Product> list = new ArrayList<>();
        for (Integer key: map.keySet()) {
            list.add(map.get(key));
        }
        return list;
    }

    public void update(Product p) {
        if (map.containsKey(p.getId())) {
            this.map.put(p.getId(), p);
        }
    }

    public Product get(Integer id) {
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

    public ArrayList showOutdated(LocalDate date){
        ArrayList outdatedArrayList = new ArrayList<Product>();
        for(Map.Entry<Integer,Product> entry : this.map.entrySet()) {
            Integer key = entry.getKey();
            Product value = entry.getValue();
            if(value.getEndCatalog().compareTo(date) >= 0) outdatedArrayList.add(value);
        }
        return outdatedArrayList;
    }
}
