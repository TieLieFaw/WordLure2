package base;

import java.util.List;

import Logic.AbstractLogic;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * The {@code ApplicationContainer} class contains the fields that are required when the program is running.<br>
 * It also overrides the class {@code Application }, whose methods are necessary for running a JavaFx application.<br>
 * 
 * 
 * @author Kirill (github.com/TieLieFaw)
 *
 */
public abstract class ApplicationContainer extends Application {
	
	protected Stage stage;
	protected Scene scene;
	protected Scene startScene;
	protected Scene selectionScene;
	protected Scene trainingScene;
	
	protected AbstractLogic logic;
	protected List<Word> dictionary;
	
	public Scene getStartScene() {
		return startScene;
	}

	public void setStartScene(Scene startScene) {
		this.startScene = startScene;
	}

	public Scene getSelectionScene() {
		return selectionScene;
	}

	public void setSelectionScene(Scene selectionScene) {
		this.selectionScene = selectionScene;
	}

	public Scene getTrainingScene() {
		return trainingScene;
	}

	public void setTrainingScene(Scene trainingScene) {
		this.trainingScene = trainingScene;
	}
	
	public Stage getContainerStage() {
		return stage;
	}
	
	public void setContainerStage(Stage stage) {
		this.stage = stage;
	}
	
	public void setContainerScene(Scene scene) {
		this.scene = scene;
	}
	
	public void setContainerLogic(AbstractLogic logic) {
		this.logic = logic;
	}
	
	public void setContainerDictionary(List<Word> dictionary) {
		this.dictionary = dictionary;
	}
	
	public Scene getContainerScene() {
		return scene;
	}
	
	public AbstractLogic getContainerLogic() {
		return logic;
	}
	
	public List<Word> getContainerDictionary() {
		return dictionary;
	}
	
	public abstract ApplicationContainer getApplicationContainer();
	public abstract void setPreferredScene(Scene scene);
	
}