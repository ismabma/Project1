package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.TreeMap;
/**
 * Clase per gestionar la persistència de les dades de les productes
 * @author manuel
 *
 */
public class ProductesDAO {

	private TreeMap<Integer, Producte> productes = new TreeMap<Integer,Producte>();

	public boolean save(Producte producte){
		productes.put(producte.getId(), producte);
		return true;
	}

	public boolean delete(Integer id){

		if (productes.containsKey(id)){
			productes.remove(id);
			return true;
		}

		return false;
	}

	public Producte find(Integer id){

		if (id == null || id == 0){
			return null;
		}

		return productes.get(id);
	}

	public void saveAll(){

		try (ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream("productes.dat"))) {
			oo.writeObject(productes);
		} catch (IOException e) {
			System.out.println("Error escribiendo fichero");
		}

	}

	@SuppressWarnings("unchecked")
	public void openAll(){

		File file = new File("productes.dat");
		if (file.exists()) {
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
				productes = (TreeMap<Integer, Producte>) ois.readObject();
			} catch (Exception e) {
				System.out.println("Error leyendo fichero");
			}
		}
	}

	public void showAll(){

		System.out.println("-------------------");
		System.out.println("Todos los productos");
		System.out.println("-------------------");
		
		for (Producte producte : productes.values()) {
			producte.imprimir();
		    System.out.println("-------------------");
		}
	}
}

