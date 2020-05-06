package it.unipv.ingsw.k20.view.gui.fan.main;

import it.unipv.ingsw.k20.view.gui.fan.controller.controllerFan;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class mainFan extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader root = new FXMLLoader(
				getClass().getResource("/it/unipv/ingsw/k20/view/gui//fan/resources/viewFan.fxml"));
		root.setController(new controllerFan());
		Scene scene = new Scene(root.load());
		scene.getStylesheets()
				.add(getClass().getResource("/it/unipv/ingsw/k20/view/gui//fan/style/styleFan.css").toExternalForm());
		primaryStage.setTitle("user");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();

	}

	public static void main(String args[]) {
		launch(args);
	}

}
