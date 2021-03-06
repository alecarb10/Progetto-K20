package interfaces.manager.gui;

import interfaces.manager.gui.util.Constants;
import interfaces.manager.gui.util.GraphicHandler;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Class to run the manager gui
 */

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene scene=GraphicHandler.getScene(Constants.PATH_PREFIX+"/resources/Login.fxml",Constants.STYLE_LOGREG_PATH);	
		GraphicHandler.loadStage(scene, primaryStage);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
