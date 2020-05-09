package it.unipv.ingsw.k20.view.gui.manager.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;

public class CreateTournamentController implements Initializable {
	
	@FXML
	private TextField txtFldName,txtFldTeam;
	@FXML
	private ComboBox<String> cmbBoxType,cmbBoxSize;
	@FXML
	private ListView<String> listViewTeams;
	@FXML
	private Button btnAddTeam,btnCreate;
	
	private ObservableList<String> teams = FXCollections.observableArrayList("team1", "team2");
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.listViewTeams.setItems(teams);
		this.listViewTeams.setCellFactory(TextFieldListCell.forListView());
		this.btnAddTeam.setOnAction((ActionEvent)->{
			this.listViewTeams.getItems().add(this.txtFldTeam.getText());
		});
	}

}
