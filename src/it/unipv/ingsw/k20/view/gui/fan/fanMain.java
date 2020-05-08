package it.unipv.ingsw.k20.view.gui.fan;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class fanMain extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getClassLoader().getResource("it/unipv/ingsw/k20/view/gui/fan/view/fanView.fxml"));
		Scene scene = new Scene(loader.load());
		primaryStage.setTitle("Fan view");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
