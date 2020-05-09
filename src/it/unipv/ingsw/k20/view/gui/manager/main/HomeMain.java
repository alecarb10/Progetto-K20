package it.unipv.ingsw.k20.view.gui.manager.main;

import it.unipv.ingsw.k20.view.gui.manager.controller.HomeController;
import it.unipv.ingsw.k20.view.gui.manager.util.Constants;
import it.unipv.ingsw.k20.view.gui.manager.util.GraphicHandler;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HomeMain extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene scene=GraphicHandler.getScene(Constants.PATH_PREFIX+"/resources/Home.fxml",new HomeController(),Constants.STYLE_PATH);
		primaryStage.setTitle("Manager");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
}
