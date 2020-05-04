package it.unipv.ingsw.k20.view.gui.manager.main;

import it.unipv.ingsw.k20.view.gui.manager.controller.LoginController;
import it.unipv.ingsw.k20.view.gui.manager.util.Constants;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginMain extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader=new FXMLLoader(getClass().getResource(Constants.PATH_PREFIX+"/resources/Login.fxml"));
		loader.setController(new LoginController());
		Scene scene= new Scene(loader.load());
		scene.getStylesheets().add(getClass().getResource(Constants.STYLE_PATH).toExternalForm());
		primaryStage.setTitle("Manager");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
