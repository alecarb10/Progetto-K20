package mvc.view.manager.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

public class InsertResultController implements Initializable {
	
	@FXML
	private ListView<String> listViewMatches;
	
	private ObservableList<String> matches = FXCollections.observableArrayList("team1 vs team2", "team3 vs team4");
	private Dialog<Pair<String, String>> dialog = new Dialog<>();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.listViewMatches.setItems(matches);
		this.listViewMatches.setCellFactory(TextFieldListCell.forListView());
		this.listViewMatches.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				dialog.getDialogPane().setContent(getDialogGridPane());
				dialog.showAndWait();
				
			}
			
		});
	}
	
	private GridPane getDialogGridPane() {
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);

		ComboBox<Integer> cmbBoxHomeTeamScore=new ComboBox<Integer>();
		cmbBoxHomeTeamScore.setPromptText("Score Home Team");
		ComboBox<Integer> cmbBoxAwayTeamScore=new ComboBox<Integer>();
		cmbBoxAwayTeamScore.setPromptText("Score Away Team");
		
		CheckBox chkBoxIsPlayed=new CheckBox("Played");
		
		ButtonType btnTypeSaveResult= new ButtonType("Save",ButtonData.OK_DONE);

		grid.add(cmbBoxHomeTeamScore, 0,0);
		grid.add(cmbBoxAwayTeamScore, 1,0);
		grid.add(chkBoxIsPlayed, 2, 0);
		dialog.getDialogPane().getButtonTypes().add(btnTypeSaveResult);
		return grid;
	}
}
