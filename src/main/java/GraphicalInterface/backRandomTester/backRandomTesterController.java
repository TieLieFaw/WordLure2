package GraphicalInterface.backRandomTester;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

import GraphicalInterface.AbstractDefaultController;
import Logic.AbstractLogic;
import base.ApplicationContainer;
import base.Word;
/**
 * 
 * @author Kirill(github.com/TieLieFaw)
 *
 */
public final class backRandomTesterController extends AbstractDefaultController {
	
	private Word word;
	
	public backRandomTesterController(AbstractLogic logic, List<Word> dictionary, ApplicationContainer container) {
		super(logic,dictionary, container);
	}
	
	@FXML
	private Label resultLabel;
	
	@FXML
	private Label wordLabel;
	
	@FXML
	private TextField entryField;
	
	@FXML
	private Button nextButton;
	
	@FXML
	private Button exitButton;
	
	@FXML
	@Override
	public void setWord() {
		
		final Task<Void> setWordTask = new Task<Void>() {
			@Override
			public Void call() throws Exception {
				word = logic.getWord(dictionary);
				return null;
			}
		};
		
		setWordTask.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				entryField.setText("");
				wordLabel.setText(new StringBuilder().append(word.getPartOfSpeech()).append(": ").append(word.getTranslate()).toString());
			}
		});
		
		Thread t = new Thread(setWordTask);
		t.setDaemon(true);
		t.start();
		
		try {
			t.join();
		} catch (InterruptedException e) {
			throw new RuntimeException("backRandomTesterController:#setWord()", e);
		}

	}
	
	@FXML
	@Override
	public void verify() {
		
		final Task<String> verifyTask = new Task<String> () {
			@Override
			protected String call() throws Exception {
				String userValue = entryField.getText().trim();
				boolean verification = logic.inspect(word, userValue);
				if(verification)
					return new StringBuilder().append("T").append("Your translation: ").append("'").append(userValue).append("'").append(" is correct. ").toString();
				else
					return new StringBuilder().append("F").append("Your translation: ").append("'").append(userValue).append("'").append(" isn't correct. ").append("That's right - ").append(word.getValue()).toString();
			}
		};
		
		verifyTask.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				
				String verification = null;
				
				try {
					verification = verifyTask.get();
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
				
				if(verification.startsWith("T")) {
					verification = verification.substring(1);
					setTrueResult(verification);
				} else {
					verification = verification.substring(1);
					setFalseResult(verification);
				}
				
			}
		});
		
		Thread t = new Thread(verifyTask);
		t.setDaemon(true);
		t.start();
		
		try {
			t.join();
		} catch (InterruptedException e) {
			throw new RuntimeException("backRandomTesterController:#verify()", e);
		}
		
		setWord();
			
	}
	
	@FXML
	@Override
	public void exit() {
		super.exit();
	}
	
	private void setTrueResult(String result) {
		resultLabel.setText(result);
		resultLabel.setTextFill(Color.web("#04ff00"));
	}
	
	private void setFalseResult(String result) {
		resultLabel.setText(result);
		resultLabel.setTextFill(Color.web("#ff4500"));
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		nextButton.setDefaultButton(true);
	}
	
}