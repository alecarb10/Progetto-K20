package interfaces.fan.gui.view.util;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class StageLoader {
	FXMLLoader loader;
	Parent root;
	Scene scene;
	Stage primaryStage;
	Rectangle2D primScreenBounds;
		
	
	public void show(String URL, String title, ActionEvent event) throws IOException {
		loader = new FXMLLoader();
		loader.setLocation(getClass().getClassLoader().getResource(URL));
		root = loader.load();
		scene = new Scene(root);
		scene.getStylesheets().add("interfaces/manager/gui/style/Style.css");
		primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		primaryStage.getIcons().add(new Image("interfaces/manager/gui/images/favicon.png"));
		primaryStage.setTitle(title);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.centerOnScreen();
		primaryStage.show();		
	}
	
	public void show(String URL, String title, Stage primaryStage) throws IOException {
		loader = new FXMLLoader();
		loader.setLocation(getClass().getClassLoader().getResource(URL));
		root = loader.load();
		scene = new Scene(root);
		scene.getStylesheets().add("interfaces/manager/gui/style/Style.css");
		primaryStage.getIcons().add(new Image("interfaces/manager/gui/images/favicon.png"));
		primaryStage.setTitle(title);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.centerOnScreen();
		primaryStage.show();		
	}
	public void showDaySelected(String URL, String title, ActionEvent event) throws IOException {
		loader = new FXMLLoader();
		loader.setLocation(getClass().getClassLoader().getResource(URL));
		root = loader.load();
		scene = new Scene(root);
		scene.getStylesheets().add("interfaces/manager/gui/style/Style.css");
		primaryStage = new Stage();
		primaryStage.getIcons().add(new Image("interfaces/manager/gui/images/favicon.png"));
		primaryStage.setTitle(title);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.centerOnScreen();
		primaryStage.show();
	}
	
	
	public FXMLLoader getLoader() {
		return loader;
	}
	public Parent getRoot() {
		return root;
	}
	public Scene getScene() {
		return scene;
	}
	public Stage getPrimaryStage() {
		return primaryStage;
	}
	public Rectangle2D getPrimScreenBounds() {
		return primScreenBounds;
	}

}
