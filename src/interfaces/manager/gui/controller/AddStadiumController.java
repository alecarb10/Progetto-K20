package interfaces.manager.gui.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import interfaces.manager.gui.util.GraphicControlsHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.team.Player;
import model.team.Stadium;
import services.persistence.dao.impl.FacadeImpl;

public class AddStadiumController implements Initializable {
	
//	@FXML
//	private ComboBox<String> cmbBoxTournament;
//	
//	private String username;
//	private ObservableList<String> tournaments = FXCollections.observableArrayList();
	@FXML
	private TextField txtFldStadiumName,txtFldStadiumCity,txtFldStadiumCapacity;
	@FXML
	private Button btnAddStadium;
	@FXML
	private TableView<Stadium> tblViewStadium;
	@FXML
	private TableColumn<Stadium, String> tblClmnStadiumName,tblClmnStadiumCity,tblClmnStadiumCapacity;
	
	private ObservableList<Stadium> stadiums;
	private FacadeImpl facadeImpl;
	private List<Stadium> stadiumsList;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		facadeImpl= FacadeImpl.getInstance();
		stadiums=FXCollections.observableArrayList();
		stadiumsList=getStadiums();
		for(Stadium s:stadiumsList)
			stadiums.add(s);
		tblClmnStadiumName.setCellValueFactory(new PropertyValueFactory<Stadium, String>("Name"));
		tblClmnStadiumCity.setCellValueFactory(new PropertyValueFactory<Stadium, String>("City"));
		tblClmnStadiumCapacity.setCellValueFactory(new PropertyValueFactory<Stadium, String>("Capacity"));
		tblViewStadium.setItems(stadiums);
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
			Stadium stadium= new Stadium(txtFldStadiumName.getText(), txtFldStadiumCity.getText(),Integer.parseInt(txtFldStadiumCapacity.getText()));
			stadiumsList.add(stadium);
			stadiums.add(stadium);
			facadeImpl.storeStadium(stadium);
			restoreComponents();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void restoreComponents() {
		GraphicControlsHandler.resetTextField(this.txtFldStadiumName, "Name");
		GraphicControlsHandler.resetTextField(this.txtFldStadiumCity, "City");
		GraphicControlsHandler.resetTextField(this.txtFldStadiumCapacity, "Capacity");
	}
}
