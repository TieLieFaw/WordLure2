package base.GraphicalInterface.initialScreen;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import base.ApplicationContainer;
import base.GraphicalInterface.selectionScreen.selectionScreenController;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
/**
 * 
 * @author Kirill(github.com/TieLieFaw)
 *
 */
public final class initialScreenController implements Initializable{

	private ApplicationContainer container;
	
	private selectionScreenController selectionScreen;
	@FXML
	private ScrollPane initialScreen;
	@FXML
	private Button exitButton;
	@FXML
	private Button startButton;
	private String welcomeMessage;
	
	public initialScreenController(ApplicationContainer aContainer) {
		container = aContainer;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		final Task<Void> readWelcomeMessage = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				try {
					welcomeMessage = new String(Files.readAllBytes(Paths.get("src/main/java/base/GraphicalInterface/initialScreen/WelcomeMessage.txt")));
				} catch (IOException e) {
					throw new RuntimeException("initialScreenController:#initialize()", e);
				}
				return null;
			}
		};
		
		readWelcomeMessage.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent arg0) {
				Label h = new Label(welcomeMessage);
				h.setWrapText(true);
				initialScreen.setContent(h);
				initialScreen.setFitToWidth(true);
			}
		});
		
		Thread t = new Thread(readWelcomeMessage);
		t.setDaemon(true);
		t.start();
		
		try {
			t.join();
		} catch (InterruptedException e) {
			throw new RuntimeException("initialScreenController:#initialize()", e);
		}
		
	}
	
	public ApplicationContainer getApplicationContainer() {
		return container;
	}
	
	@FXML
	public void start() { 
		if(container.getSelectionScene() == null) {
			selectionScreen = new selectionScreenController(container.getApplicationContainer());
			FXMLLoader loader = new FXMLLoader(selectionScreenController.class.getResource("/base/GraphicalInterface/selectionScreen/selectionScreenFXML.fxml"));
			loader.setController(selectionScreen);
			try {
				Parent pane = loader.load();
				Scene selectionScene = new Scene(pane);
				container.setSelectionScene(selectionScene);
				container.setPreferredScene(selectionScene);
			} catch (IOException e) {
				throw new RuntimeException("initialScreenController:#start()", e);
			}
		} else {
			container.setPreferredScene(container.getSelectionScene());
		}
	}
	
	@FXML
	public void exit() {
		System.exit(0);
	}
	
}