package interfaces.manager.gui.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import domain.team.Stadium;
import interfaces.manager.gui.util.GraphicControlsHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import services.persistence.dao.impl.FacadeImpl;

/**
 * Controller for add the stadium
 * 
 * @see Initializable
 * @see Stadium
 */

public class AddStadiumController implements Initializable {

	@FXML
	private TextField txtFldStadiumName, txtFldStadiumCity, txtFldStadiumCapacity;
	@FXML
	private Button btnAddStadium;
	@FXML
	private TableView<Stadium> tblViewStadium;
	@FXML
	private TableColumn<Stadium, String> tblClmnStadiumName, tblClmnStadiumCity, tblClmnStadiumCapacity;

	private ObservableList<Stadium> stadiums;
	private FacadeImpl facadeImpl;
	private List<Stadium> stadiumsList;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		facadeImpl = FacadeImpl.getInstance();
		stadiums = FXCollections.observableArrayList();
		stadiumsList = getStadiums();
		populateTblViewStadium();
	}

	private List<Stadium> getStadiums() {
		try {
			return facadeImpl.getStadiums();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public void addStadium(ActionEvent event) {
		try {
			if (isNotBlankControl()) {
				Stadium stadium = new Stadium(txtFldStadiumName.getText(), txtFldStadiumCity.getText(), Integer.parseInt(txtFldStadiumCapacity.getText()));
				facadeImpl.storeStadium(stadium);
				stadiumsList.add(stadium);
				stadiums.add(stadium);
				Alert alert=new Alert(AlertType.INFORMATION,"Stadium successfully created.",ButtonType.OK);
				alert.showAndWait();
				if(alert.getResult()==ButtonType.OK) 
					restoreComponents();
			} 
			else 
				new Alert(AlertType.ERROR, "Fields cannot be empty.", ButtonType.OK).show();
		} 
		catch (NumberFormatException nfe) {
			new Alert(AlertType.ERROR, "Stadium capacity must be a number.", ButtonType.OK).show();
		} 
		catch (Exception e) {
			new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK).show();
		}
	}

	private void restoreComponents() {
		GraphicControlsHandler.resetTextField(this.txtFldStadiumName, "Name");
		GraphicControlsHandler.resetTextField(this.txtFldStadiumCity, "City");
		GraphicControlsHandler.resetTextField(this.txtFldStadiumCapacity, "Capacity");
	}

	private boolean isNotBlankControl() {
		return !txtFldStadiumName.getText().isBlank() && !txtFldStadiumCity.getText().isBlank() && !txtFldStadiumCapacity.getText().isBlank();
	}
	
	private void populateTblViewStadium() {
		tblClmnStadiumName.setCellValueFactory(new PropertyValueFactory<Stadium, String>("Name"));
		tblClmnStadiumCity.setCellValueFactory(new PropertyValueFactory<Stadium, String>("City"));
		tblClmnStadiumCapacity.setCellValueFactory(new PropertyValueFactory<Stadium, String>("Capacity"));
		for (Stadium s : stadiumsList)
			stadiums.add(s);
		tblViewStadium.setItems(stadiums);
	}
	
}
