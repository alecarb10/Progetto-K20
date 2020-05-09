package mvc.view.manager.gui.main;

import mvc.view.manager.gui.controller.HomeController;
import mvc.view.manager.gui.util.Constants;
import mvc.view.manager.gui.util.GraphicHandler;
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
