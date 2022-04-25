package controlador;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.util.TreeSet;

import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.Asistencia;
import model.AsistenciaDAO;

public class AsistenciaController{

	//Objecte per gestionar la persistència de les dades
	private AsistenciaDAO asistencies;

	//Elements gràfics de la UI
	private Stage ventana;
	@FXML private TextField idTextField;
	@FXML private DatePicker entradaDate;
	@FXML private DatePicker sortidaDate;
	

	private ValidationSupport vs;

	public void setConexionBD(Connection conexionBD) {	
		//Crear objecte DAO de persones
		asistencies = new AsistenciaDAO(conexionBD);
	}
	
	/**
	 * Inicialitza la classe. JAVA l'executa automàticament després de carregar el fitxer fxml
	 */
	@FXML private void initialize() {

		//Validació dades
		//https://github.com/controlsfx/controlsfx/issues/1148
		//produeix error si no posem a les VM arguments això: --add-opens=javafx.graphics/javafx.scene=ALL-UNNAMED
		vs = new ValidationSupport();
		vs.registerValidator(idTextField, true, Validator.createEmptyValidator("ID obligatori"));
		vs.registerValidator(entradaDate, true, Validator.createEmptyValidator("Inici de cataleg obligatorio"));
		vs.registerValidator(sortidaDate, false, Validator.createEmptyValidator("Fi de cataleg obligatorio"));
	}

	public Stage getVentana() {
		return ventana;
	}

	public void setVentana(Stage ventana) {
		this.ventana = ventana;
	}

	@FXML private void onKeyPressedId(KeyEvent e) throws IOException {

		if (e.getCode() == KeyCode.ENTER || e.getCode() == KeyCode.TAB){
			//Comprovar si existeix la persona indicada en el control idTextField
			Asistencia asistencia = asistencies.find(Integer.parseInt(idTextField.getText()));
			if(asistencia != null){ 
				//posar els valors per modificarlos
				entradaDate.setValue(asistencia.getHoraEntrada().toLocalDate());
				sortidaDate.setValue(asistencia.getHoraSortida().toLocalDate());
			}else{ 
				entradaDate.setValue(null);
				sortidaDate.setValue(null);
			}
		}
	}
	
	@FXML private void onActionGuardar(ActionEvent e) throws IOException {
		//verificar si les dades són vàlides				
		if(isDatosValidos()){
			Asistencia asistencia = new Asistencia(Integer.parseInt(idTextField.getText()), 
					Date.valueOf(entradaDate.getValue()), Date.valueOf(sortidaDate.getValue()));
			
			asistencies.save(asistencia);
			limpiarFormulario();
			asistencies.showAll();
		}
	}

	@FXML private void onActionEliminar(ActionEvent e) throws IOException {

		if(isDatosValidos()){
			if(asistencies.delete(Integer.parseInt(idTextField.getText()))){
				limpiarFormulario();
				asistencies.showAll();
			}
		}
	}

	@FXML private void onActionSortir(ActionEvent e) throws IOException {

		sortir();

		ventana.close();
	}

	public void sortir(){
		asistencies.showAll();
	}

	private boolean isDatosValidos() {

		//Comprovar si totes les dades són vàlides
		if (vs.isInvalid()) {
			String errors = vs.getValidationResult().getMessages().toString();
			// Mostrar finestra amb els errors
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.initOwner(ventana);
			alert.setTitle("Camps incorrectes");
			alert.setHeaderText("Corregeix els camps incorrectes");
			alert.setContentText(errors);
			alert.showAndWait();
		
			return false;
		}

		return true;

	}

	private void limpiarFormulario(){
		idTextField.setText("");
		entradaDate.setValue(null);
	}
}
