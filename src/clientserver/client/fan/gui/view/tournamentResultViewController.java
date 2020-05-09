package clientserver.client.fan.gui.view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.stage.Stage;

public class tournamentResultViewController {
	@FXML
	private Button goBackButton;

	public void goBack(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(
				getClass().getClassLoader().getResource("it/unipv/ingsw/k20/view/gui/fan/view/fanView.fxml"));
		Scene scene = new Scene(loader.load());
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		primaryStage.setTitle("Fan view");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

}
