package interfaces.fan.gui;

import java.io.IOException;

import interfaces.fan.gui.view.util.StageLoader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class FanMain extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		
		StageLoader SLF = new StageLoader();
		SLF.show("clientserver/client/fan/gui/view/FanMenu.fxml", "Fan menu", primaryStage);
		/*
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getClassLoader().getResource("clientserver/client/fan/gui/view/FanMenu.fxml"));
		Scene scene = new Scene(loader.load());
		primaryStage.setTitle("Fan menu");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
		primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
		primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);
		primaryStage.show();
	*/
	}

	public static void main(String[] args) {
		launch(args);
	}
}
