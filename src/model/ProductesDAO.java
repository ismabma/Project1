package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeMap;
/**
 * Clase per gestionar la persistència de les dades de les productes
 * @author manuel
 *
 */
public class ProductesDAO {

	private Connection conexionBD;

	public ProductesDAO(Connection conexionBD) {
		this.conexionBD = conexionBD;
	}
	
	public boolean save(Producte producte){
		try {
			String sql = "";
			PreparedStatement stmt = null;
			if (this.find(producte.getId()) == null){
				sql = "INSERT INTO producto(id, nombre, precio, stock, dto, fecha_inicio, fecha_fin) VALUES(?,?,?,?,?,?)";
				stmt = conexionBD.prepareStatement(sql);
				int i = 1;
				stmt.setInt(i++, producte.getId());
				stmt.setString(i++, producte.getName());
				stmt.setDouble(i++, producte.getpriceSell());
				stmt.setInt(i++, 1);
				stmt.setInt(i++, producte.getDiscount());
				if(producte.getProducts() == null) {
					for(Object element : producte.getProducts().toArray()) {
						PreparedStatement pstmtdel = conexionBD.prepareStatement("DELETE FROM  productos_pack WHERE id_pack = ?");
						pstmtdel.setInt(1, producte.getId());
						pstmtdel.execute();
						Producte prod = (Producte) element;
						PreparedStatement pstmtins = conexionBD.prepareStatement("INSERT INTO productos_pack(id_pack, id_producto) VALUES(?,?)");
						pstmtins.setInt(1, producte.getId());
						pstmtins.setInt(1, prod.getId());
						pstmtins.execute();
					}
				}
				stmt.setDate(i++, Date.valueOf(producte.getStartCatalog()));
				stmt.setDate(i++, Date.valueOf(producte.getEndCatalog()));
			} else{
				sql = "UPDATE producte SET nombre=?, precio=?, dto=?, fecha_inicio=?, fecha_fin=? WHERE id = ?";
				stmt = conexionBD.prepareStatement(sql);
				int i = 1;
				stmt.setString(i++, producte.getName());
				stmt.setDouble(i++, producte.getpriceSell());
				stmt.setInt(i++, producte.getDiscount());
				stmt.setDate(i++, Date.valueOf(producte.getStartCatalog()));
				stmt.setDate(i++, Date.valueOf(producte.getEndCatalog()));
				if(producte.getProducts() == null) {
					for(Object element : producte.getProducts().toArray()) {
						PreparedStatement pstmtdel = conexionBD.prepareStatement("DELETE FROM  productos_pack WHERE id_pack = ?");
						pstmtdel.setInt(1, producte.getId());
						pstmtdel.execute();
						Producte prod = (Producte) element;
						PreparedStatement pstmtins = conexionBD.prepareStatement("INSERT INTO productos_pack(id_pack, id_producto) VALUES(?,?)");
						pstmtins.setInt(1, producte.getId());
						pstmtins.setInt(1, prod.getId());
						pstmtins.execute();
					}
				}
				stmt.setInt(i++, producte.getId());
			}
			int rows = stmt.executeUpdate();
			if (rows == 1) return true;
			else return false;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	public boolean delete(Integer id){
		try {
			String sql = "";
			PreparedStatement stmt = null;
			if (this.find(id) != null){
				sql = "DELETE FROM producto WHERE id = ?";
				stmt = conexionBD.prepareStatement(sql);
				int i = 1;
				stmt.setInt(i++, id);
			}
			int rows = stmt.executeUpdate();
			if (rows == 1) return true;
			else return false;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	public Producte find(Integer id){

		if (id == null || id == 0){
			return null;
		}
		
		Producte p = null;
		try (PreparedStatement stmt = conexionBD.prepareStatement("SELECT * FROM producto WHERE id = ?")) {
			stmt.setInt(1, id);
			ResultSet result = stmt.executeQuery();
			if (result.next()) {
				p = new Producte(result.getInt("id"), result.getString("nombre"), result.getDouble("precio"),result.getInt("precio"),result.getDate("fecha_inicio").toLocalDate(),result.getDate("fecha_fin").toLocalDate());
				p.imprimir();
			}	
			
			return p;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public void showAll(){
		try (ResultSet result = conexionBD.createStatement().executeQuery("SELECT * FROM producto")) {
			while (result.next()) {
				Producte p = new Producte(result.getInt("id"), result.getString("nombre"), result.getDouble("precio"),result.getInt("precio"),result.getDate("fecha_inicio").toLocalDate(),result.getDate("fecha_fin").toLocalDate());
				p.imprimir();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}

