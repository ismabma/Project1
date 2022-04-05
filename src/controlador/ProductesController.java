package controlador;

import java.io.IOException;
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
import model.Producte;
import model.ProductesDAO;

public class ProductesController{

	//Objecte per gestionar la persistència de les dades
	private ProductesDAO productes;

	//Elements gràfics de la UI
	private Stage ventana;
	@FXML private TextField idTextField;
	@FXML private TextField nomTextField;
	@FXML private TextField preuVendaTextField;
	@FXML private TextField stockTextField;
	@FXML private DatePicker iniciCatDatePicker;
	@FXML private DatePicker fiCatDatePicker;
	@FXML private CheckBox packCheckBox;
	@FXML private TextArea packProductesTextArea;
	@FXML private TextField descompteTextField;
	

	private ValidationSupport vs;

	/**
	 * Inicialitza la classe. JAVA l'executa automàticament després de carregar el fitxer fxml
	 */
	@FXML private void initialize() {
		//Obrir el fitxer de persones
		productes = new ProductesDAO();
		productes.openAll();

		//Validació dades
		//https://github.com/controlsfx/controlsfx/issues/1148
		//produeix error si no posem a les VM arguments això: --add-opens=javafx.graphics/javafx.scene=ALL-UNNAMED
		vs = new ValidationSupport();
		vs.registerValidator(idTextField, true, Validator.createEmptyValidator("ID obligatori"));
		vs.registerValidator(nomTextField, true, Validator.createEmptyValidator("Nom obligatori"));
		vs.registerValidator(preuVendaTextField, true, Validator.createEmptyValidator("Preu de venda obligatorio"));
		vs.registerValidator(iniciCatDatePicker, true, Validator.createEmptyValidator("Inici de cataleg obligatorio"));
		vs.registerValidator(fiCatDatePicker, true, Validator.createEmptyValidator("Fi de cataleg obligatorio"));
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
			Producte producte = productes.find(Integer.parseInt(idTextField.getText()));
			if(producte != null){ 
				//posar els valors per modificarlos
				nomTextField.setText(producte.getName());
				preuVendaTextField.setText(Double.toString(producte.getpriceSell()));
				stockTextField.setText(Integer.toString(producte.getStock()));
				iniciCatDatePicker.setValue(producte.getStartCatalog());
				fiCatDatePicker.setValue(producte.getEndCatalog());
			}else{ 
				//nou registre
				nomTextField.setText("");
				preuVendaTextField.setText("");
				stockTextField.setText("");
				iniciCatDatePicker.setValue(null);
				fiCatDatePicker.setValue(null);
			}
		}
	}
	 
	@FXML private void onActionCheck(ActionEvent e) throws IOException {
		if(packCheckBox.isSelected()) {
			packProductesTextArea.setEditable(true);
			descompteTextField.setDisable(false);
		} else{
			packProductesTextArea.setEditable(false);
			descompteTextField.setDisable(true);
		}
	}
	
	@FXML private void onActionGuardar(ActionEvent e) throws IOException {
		//verificar si les dades són vàlides				
		if(isDatosValidos()){
			Producte producte;
			if(packCheckBox.isSelected()) {
				TreeSet prodPack = new TreeSet<Integer>();
				
				for(String prod : packProductesTextArea.getText().split(",")) {
					prodPack.add(Integer.parseInt(prod));
				}
				producte = new Producte(Integer.parseInt(idTextField.getText()),  nomTextField.getText(), 
						Double.parseDouble(preuVendaTextField.getText()), 0, iniciCatDatePicker.getValue(), 
						fiCatDatePicker.getValue(), prodPack, Integer.parseInt(descompteTextField.getText()));
			} else {
				producte = new Producte(Integer.parseInt(idTextField.getText()),  nomTextField.getText(), 
						Double.parseDouble(preuVendaTextField.getText()), 0, 
						iniciCatDatePicker.getValue(), fiCatDatePicker.getValue());
			}
			
			productes.save(producte);
			limpiarFormulario();
			productes.showAll();
		}
	}

	@FXML private void onActionEliminar(ActionEvent e) throws IOException {

		if(isDatosValidos()){
			if(productes.delete(Integer.parseInt(idTextField.getText()))){
				limpiarFormulario();
				productes.showAll();
			}
		}
	}

	@FXML private void onActionSortir(ActionEvent e) throws IOException {

		sortir();

		ventana.close();
	}

	public void sortir(){
		productes.saveAll();
		productes.showAll();
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
		nomTextField.setText("");
		preuVendaTextField.setText("");
		stockTextField.setText("");
		iniciCatDatePicker.setValue(null);
		fiCatDatePicker.setValue(null);
		packProductesTextArea.setText("");
		descompteTextField.setText("");
		packCheckBox.setSelected(false);
	}
}
