package mvc.view.manager.gui.util;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

public class GraphicControlsHandler {

	public static void resetComboBox(ComboBox<String> cmbBox,String promptText) {
		cmbBox.getSelectionModel().clearSelection();
		cmbBox.setButtonCell(new ListCell<String>() {
	        @Override
	        protected void updateItem(String item, boolean empty) {
	            super.updateItem(item, empty) ;
	            if (empty || item == null) {
	                setText(promptText);
	            } 
	            else {
	                setText(item);
	            }
	        }
	    });
	}
	
	public static void resetObservableList(ObservableList<String> list) {
		list.clear();
	}
	
	public static void resetTextField(TextField txtFld, String promptText) {
		txtFld.clear();
		txtFld.setPromptText(promptText);
	}
	
	public static void resetSpinner(Spinner<Integer> spinner) {
		spinner.getValueFactory().setValue(0);
	}
}
