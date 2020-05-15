package mvc.view.manager.gui.main;

import mvc.view.manager.gui.util.Constants;
import mvc.view.manager.gui.util.GraphicHandler;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginMain extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene scene=GraphicHandler.getScene(Constants.PATH_PREFIX+"/resources/Login.fxml",Constants.STYLE_LOGREG_PATH);	
		GraphicHandler.loadStage(scene, primaryStage);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
