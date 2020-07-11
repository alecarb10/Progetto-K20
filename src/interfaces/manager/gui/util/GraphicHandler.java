package interfaces.manager.gui.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GraphicHandler {
	
	public static Scene getScene(String resource) {
		try {        
    		return new Scene(getLoader(resource).load());
    		
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
	}
	
	public static Scene getScene(String resource,String style) {
		try {
			Scene scene= new Scene(getLoader(resource).load());
			scene.getStylesheets().add(new Object() {}.getClass().getEnclosingClass().getResource(style).toExternalForm());
    		return scene;
    		
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
	}
	
	public static Scene getScene(Parent root,String style) {
		try {
			Scene scene= new Scene(root);
			scene.getStylesheets().add(new Object() {}.getClass().getEnclosingClass().getResource(style).toExternalForm());
    		return scene;
    		
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
	}
	
	public static Parent getParent(String resource) {
		try {
			return getLoader(resource).load();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public static FXMLLoader getLoader(String resource) {
		return new FXMLLoader(new Object() {}.getClass().getEnclosingClass().getResource(resource));
	}
	
	public static void loadStage(Scene scene,Stage primaryStage) {
		primaryStage.setTitle("Manager");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
}
