package mvc.view.manager.gui.util;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class GraphicHandler {
	public static Scene getScene(String resource, Initializable controller) {
		try {        
    		return new Scene(getLoader(resource, controller).load());
    		
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
	}
	
	public static Scene getScene(String resource, Initializable controller,String style) {
		try {
			Scene scene= new Scene(getLoader(resource, controller).load());
			scene.getStylesheets().add(new Object() {}.getClass().getEnclosingClass().getResource(style).toExternalForm());
    		return scene;
    		
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
	}
	
	public static Parent getParent(String resource, Initializable controller) {
		try {
			return getLoader(resource, controller).load();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	private static FXMLLoader getLoader(String resource, Initializable controller) {
		FXMLLoader fxmlLoader=new FXMLLoader(new Object() {}.getClass().getEnclosingClass().getResource(resource));
		fxmlLoader.setController(controller);
		return fxmlLoader;
	}
}
