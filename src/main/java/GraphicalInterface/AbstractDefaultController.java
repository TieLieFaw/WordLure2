package GraphicalInterface;

import java.io.IOException;
import java.util.List;

import Logic.AbstractLogic;
import base.AbstractController;
import base.ApplicationContainer;
import base.Word;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
/**
 * The class AbstractDefaultController defines behavior all Controller's classes.
 * 
 * @author Kirill(github.com/TieLieFaw)
 *
 */
public abstract class AbstractDefaultController implements Initializable, AbstractController{
	
	protected AbstractLogic logic;
	protected List<Word> dictionary;
	private ApplicationContainer container;
	
	protected AbstractDefaultController(AbstractLogic logic, List<Word> dictionary, ApplicationContainer container) {
		this.logic = logic;
		this.dictionary = dictionary;
		this.container = container;
	}
	
	@Override
	public void exit() {
		Stage dialog = new Stage();
		Parent root = null;
		try {
			FXMLLoader loader = new FXMLLoader(ChooseModalWindowController.class.getResource("/GraphicalInterface/ChooseModalWindow.fxml"));
			ChooseModalWindowController c = new ChooseModalWindowController(container);
			loader.setController(c);
			root = loader.load();
			c.setMessage("Do you want to exit?");
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
		dialog.getIcons().add(new Image(this.getClass().getResourceAsStream("/GraphicalInterface/questionIcon.png")));
		dialog.setTitle("Confirm action");
		dialog.setScene(new Scene(root));
		dialog.setResizable(false);
		
		dialog.initOwner(container.getContainerStage());
		dialog.initModality(Modality.APPLICATION_MODAL); 
		dialog.showAndWait();
	}
}