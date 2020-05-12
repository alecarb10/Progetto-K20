package mvc.view.manager.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;
import mvc.view.manager.gui.util.Constants;
import mvc.view.manager.gui.util.GraphicHandler;

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
		this.cmbBoxType.setItems(FXCollections.observableArrayList("League","Knockout Phase","Mixed"));
		this.cmbBoxType.setOnAction((ActionEvent)->{
			this.cmbBoxSize.setItems(this.getSizeList());
		});
		this.listViewTeams.setItems(teams);
		this.listViewTeams.setCellFactory(TextFieldListCell.forListView());
		this.btnAddTeam.setOnAction((ActionEvent)->{
			this.listViewTeams.getItems().add(this.txtFldTeam.getText());
		});
	}

	public void removeTeamFromList(ActionEvent event) {
		this.teams.remove(this.listViewTeams.getSelectionModel().getSelectedIndex());
	}
	
	private ObservableList<String> getSizeList(){
		ObservableList<String> sizeList= FXCollections.observableArrayList();
		int i,j;
		for(i=4,j=2;i<=16&&j<=4;) {
			if(this.cmbBoxType.getSelectionModel().getSelectedIndex()==0) {
				sizeList.add(Integer.toString(i));
				i+=2;
			}
			else {				
				i=2;
				sizeList.add(Integer.toString((int)Math.pow(i, j)));
				j++;
				
			}
				
		}		
		return sizeList;
	}
	
}
