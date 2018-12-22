package base.GraphicalInterface.selectionScreen;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import GraphicalInterface.AbstractDefaultController;
import Logic.AbstractLogic;
import base.AbstractController;
import base.ApplicationContainer;
import base.Dictionary;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
/**
 * 
 * @author Kirill(github.com/TieLieFaw)
 *
 */
public class selectionScreenController {

	@FXML
	private Button selectionFile;
	private FileChooser fileChooser;
	@FXML
	private Button backButton;
	@FXML
	private Button nextButton;
	@FXML
	private ComboBox<String> selectionMode;
	private String dictionaryFilePath;
	private String testingModeName;
	private Dictionary dictionary;
	private ApplicationContainer container;
	
	public selectionScreenController(ApplicationContainer aContainer) {
		container = aContainer;
	}
	
	public ApplicationContainer getApplicationContainer() {
		return container;
	}
	
	@FXML
	public void selectFile() {
		fileChooser = new FileChooser();
		fileChooser.setTitle("Please, select a dictionary file");
		File file = fileChooser.showOpenDialog(null);
		dictionaryFilePath = file.getAbsolutePath();
		
		final Task<String[]> readDictionaryFile = new Task<String[]>() {
			@Override
			protected String[] call() throws Exception {
				try {
					dictionary = Dictionary.loadDictionary(new File(dictionaryFilePath));
				} catch(IOException e) {
					Platform.runLater(() -> {
						Stage dialog = new Stage();
						Parent root = null;
						try {
							FXMLLoader loader = new FXMLLoader(ModalWindowController.class.getResource("ModalWindow.fxml"));
							ModalWindowController c = new ModalWindowController();
							loader.setController(c);
							root = loader.load();
							c.setMessage("Invalid file path. Please, try again.");
						} catch (IOException ex) {
							throw new RuntimeException(ex);
						}
						dialog.setTitle("Warning");
						dialog.getIcons().add(new Image(this.getClass().getResourceAsStream("warning.png")));
						dialog.setScene(new Scene(root));
						dialog.setResizable(false);
						
						dialog.initOwner(container.getContainerStage());
						dialog.initModality(Modality.APPLICATION_MODAL); 
						dialog.showAndWait();
					});
				}
				
				AbstractLogic[] comboAbstractLogic = Arrays.copyOf(dictionary.getDefaultLogic(), dictionary.getDefaultLogic().length);
				String[] testingModeNames = new String[comboAbstractLogic.length];
				
				for(int i = 0; i < testingModeNames.length; i++) {
					testingModeNames[i] = comboAbstractLogic[i].getName();
				}
				return testingModeNames;
			}
		};
		
		readDictionaryFile.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent arg0) {
				ObservableList<String> testingModeList = null;
				try {
					testingModeList = FXCollections.observableArrayList(readDictionaryFile.get());
				} catch (InterruptedException | ExecutionException e) {
					throw new RuntimeException(e);
				}
				selectionMode.setItems(testingModeList);
				selectionMode.getSelectionModel().selectFirst();
			}
		});
		
		Thread t = new Thread(readDictionaryFile);
		t.setDaemon(true);
		t.start();
		
		try {
			t.join();
		} catch (InterruptedException e) {
			throw new RuntimeException("selectionScreenController:#selectFile()", e);
		}

	}
	
	@FXML
	public void back() {	
		container.setPreferredScene(container.getStartScene());
	}
	
	@FXML
	public void next() {
		testingModeName = selectionMode.getSelectionModel().getSelectedItem();
		if(testingModeName == null) {
			Platform.runLater(() -> {
				Stage dialog = new Stage();
				Parent root = null;
				try {
					FXMLLoader loader = new FXMLLoader(ModalWindowController.class.getResource("ModalWindow.fxml"));
					ModalWindowController c = new ModalWindowController();
					loader.setController(c);
					root = loader.load();
					c.setMessage("Please, select dictionary file");
				} catch (IOException ex) {
					throw new RuntimeException(ex);
				}
				dialog.getIcons().add(new Image(this.getClass().getResourceAsStream("warning.png")));
				dialog.setTitle("Warning");
				dialog.setScene(new Scene(root));
				dialog.setResizable(false);
				
				dialog.initOwner(container.getContainerStage());
				dialog.initModality(Modality.APPLICATION_MODAL); 
				dialog.showAndWait();
			});
		} else {
			AbstractLogic choosedLogic = null;
			for(AbstractLogic a : dictionary.getDefaultLogic()) {
				if(a.getName().equals(testingModeName)) choosedLogic = a;
			}
			container.setContainerLogic(choosedLogic);
			container.setContainerDictionary(dictionary.getDictionary());
			
			AbstractController c2;
			try {
				c2 = (AbstractController) Class.forName(new StringBuilder().append(container.getContainerLogic().getClass().getName()).delete(0, 5).insert(0, "GraphicalInterface").append(new StringBuilder().append(container.getContainerLogic().getClass().getName()).delete(0, 5).append("Controller").toString()).toString()).getConstructor(AbstractLogic.class, List.class, ApplicationContainer.class).newInstance(container.getContainerLogic(), container.getContainerDictionary(), container.getApplicationContainer());
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
				throw new RuntimeException(e);
			}
			
			FXMLLoader loader = new FXMLLoader(AbstractDefaultController.class.getResource(new StringBuilder().append("/GraphicalInterface/").append(new StringBuilder().append(container.getContainerLogic().getClass().getName()).delete(0, 6).toString()).append("/").append(new StringBuilder().append(container.getContainerLogic().getClass().getName()).delete(0, 6).append(".fxml").toString()).toString()));
			loader.setController(c2);
			
			try {
				Parent pane = loader.load();
				Scene trainingScene = new Scene(pane);
				container.setTrainingScene(trainingScene);
				container.setPreferredScene(trainingScene);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}

			c2.setWord();
		}
	}

}