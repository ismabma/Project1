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
public class AsistenciaDAO {

	private Connection conexionBD;

	public AsistenciaDAO(Connection conexionBD) {
		this.conexionBD = conexionBD;
	}
	
	public boolean save(Asistencia asistencia){
		try {
			String sql = "";
			PreparedStatement stmt = null;
			if (this.find(asistencia.getIdPersona()) == null){
				sql = "INSERT INTO asistencia(id, fecha_entrada, fecha_salida) VALUES(?,?,?)";
				stmt = conexionBD.prepareStatement(sql);
				int i = 1;
				stmt.setInt(i++, asistencia.getIdPersona());
				stmt.setDate(i++, asistencia.getHoraEntrada());
				stmt.setDate(i++, asistencia.getHoraSortida());
			} else{
				sql = "UPDATE asistencia SET fecha_entrada=?, fecha_salida=? WHERE id = ?";
				stmt = conexionBD.prepareStatement(sql);
				int i = 1;
				stmt.setDate(i++, asistencia.getHoraEntrada());
				stmt.setDate(i++, asistencia.getHoraSortida());
				stmt.setInt(i++, asistencia.getIdPersona());
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
				sql = "DELETE FROM asistencia WHERE id = ?";
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

	public Asistencia find(Integer id){

		if (id == null || id == 0){
			return null;
		}
		
		Asistencia a = null;
		try (PreparedStatement stmt = conexionBD.prepareStatement("SELECT * FROM asistencia WHERE id = ?")) {
			stmt.setInt(1, id);
			ResultSet result = stmt.executeQuery();
			if (result.next()) {
				a = new Asistencia(result.getInt("id"),result.getDate("fecha_inicio"),result.getDate("fecha_fin"));
				a.imprimir();
			}	
			
			return a;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public void showAll(){
		try (ResultSet result = conexionBD.createStatement().executeQuery("SELECT * FROM asistencia")) {
			while (result.next()) {
				Asistencia p = new Asistencia(result.getInt("id"),result.getDate("fecha_entrada"),result.getDate("fecha_salida"));
				p.imprimir();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}

