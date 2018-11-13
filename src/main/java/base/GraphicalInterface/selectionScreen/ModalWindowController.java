package base.GraphicalInterface.selectionScreen;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
/**
 * 
 * @author Kirill(github.com/TieLieFaw)
 *
 */
public class ModalWindowController implements Initializable{

	@FXML
	private Label warningMessage;

	@FXML
	private Button approveButton;

	public void setMessage(String message) {
		warningMessage.setText(message);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		approveButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				Node source = (Node)  actionEvent.getSource();
				Stage stage  = (Stage) source.getScene().getWindow();
				stage.close();
			}

		});
	}

}