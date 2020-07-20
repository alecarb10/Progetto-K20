package interfaces.fan.gui.util;

import java.io.IOException;
import java.sql.SQLException;

import domain.tournament.Tournament;
import domain.tournament.TournamentType;
import interfaces.fan.gui.controller.LeagueRankingController;
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
	
	public void backToLeague(ActionEvent event, Tournament tournament) throws IOException, SQLException {
		StageLoader SLB = new StageLoader();
		SLB.show("interfaces/fan/gui/resources/LeagueRanking.fxml", "Ranking", event);
		LeagueRankingController lrc = SLB.getLoader().getController();
		lrc.passingData(tournament);
	}
	public void backToFanMenuOrLeague(ActionEvent event, Tournament tournament) throws IOException, SQLException {
		if(tournament.getTournamentType() == TournamentType.MIXED) {
			backToLeague(event, tournament);
		}
		if(tournament.getTournamentType() == TournamentType.KNOCKOUT_PHASE) {
		StageLoader SLB = new StageLoader();
		SLB.show("interfaces/fan/gui/resources/FanMenu.fxml", "Fan menu", event);
			}
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
