package interfaces.manager.gui.main;

import interfaces.manager.gui.util.Constants;
import interfaces.manager.gui.util.GraphicHandler;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HomeMain extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene scene=GraphicHandler.getScene(Constants.PATH_PREFIX+"/resources/Home.fxml",Constants.STYLE_PATH);
		GraphicHandler.loadStage(scene, primaryStage);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
}
