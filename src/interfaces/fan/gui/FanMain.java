package interfaces.fan.gui;

import java.io.IOException;

import interfaces.fan.gui.view.util.StageLoader;
import javafx.application.Application;
import javafx.stage.Stage;

public class FanMain extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		StageLoader SLF = new StageLoader();
		SLF.show("interfaces/fan/gui/view/FanMenu.fxml", "Fan menu", primaryStage);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
