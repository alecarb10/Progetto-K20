package it.unipv.ingsw.k20.view.gui;


import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoginForm extends Application  {
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Login Form");

		GridPane gPane = new GridPane();
		gPane.setAlignment(Pos.CENTER);
		gPane.setHgap(10);
		gPane.setVgap(10);

		Label lblUsername = new Label("Username");

		TextField txtFldUsername = new TextField();
		txtFldUsername.setPromptText("username");

		Label lblPwd = new Label("Password");

		PasswordField pwdFldPwd = new PasswordField();
		pwdFldPwd.setPromptText("password");

		Button btnLogin = new Button("Log In");
		btnLogin.setOnAction(e ->{
		new Alert(Alert.AlertType.INFORMATION, "Hello World!").showAndWait();
		});

		gPane.add(lblUsername, 0, 1);
		gPane.add(txtFldUsername, 1, 1);
		gPane.add(lblPwd, 0, 2);
		gPane.add(pwdFldPwd, 1, 2);
		gPane.add(btnLogin, 1, 3);

		Scene scene = new Scene(gPane, 500, 500);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}