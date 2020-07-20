package interfaces.fan.gui;

import java.io.IOException;

import interfaces.fan.gui.util.StageLoader;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Class to run the fan GUI
 *
 */
public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		StageLoader SLF = new StageLoader();
		SLF.show("interfaces/fan/gui/resources/FanMenu.fxml", "Fan menu", primaryStage);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
