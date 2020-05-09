package clientserver.client.fan.gui.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class fanViewController implements Initializable {
	
	@FXML
	private Button goButton;
	
	//Da vedere come prender i dati dal DB
	@FXML
	private ListView<String> tournamentNameList;
	String t1 = "tournament1";
	String t2 = "tournament2";
	String t3 = "tournament3";
	ObservableList<String> list = FXCollections.observableArrayList(t1,t2,t3);

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		tournamentNameList.setItems(list);
	}
	
	public void textClicked(ActionEvent event) throws IOException {
		ObservableList<String> names;
		names = tournamentNameList.getSelectionModel().getSelectedItems();
			for(String name : names) {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getClassLoader().getResource("it/unipv/ingsw/k20/view/gui/fan/view/tournamentResultView.fxml"));
				Scene scene = new Scene(loader.load());
				Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				primaryStage.setTitle(name);
				primaryStage.setScene(scene);
				primaryStage.show();
				
			}
	}

}
