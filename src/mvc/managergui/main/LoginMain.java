package it.unipv.ingsw.k20.view.gui.manager.main;

import it.unipv.ingsw.k20.view.gui.manager.controller.LoginController;
import it.unipv.ingsw.k20.view.gui.manager.util.Constants;
import it.unipv.ingsw.k20.view.gui.manager.util.GraphicHandler;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginMain extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene scene=GraphicHandler.getScene(Constants.PATH_PREFIX+"/resources/Login.fxml", new LoginController(),Constants.STYLE_LOGREG_PATH);	
		primaryStage.setTitle("Manager");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
