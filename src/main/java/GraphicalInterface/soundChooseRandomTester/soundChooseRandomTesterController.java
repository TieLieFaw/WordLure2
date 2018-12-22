package GraphicalInterface.soundChooseRandomTester;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

import GraphicalInterface.AbstractDefaultController;
import Logic.AbstractLogic;
import base.ApplicationContainer;
import base.Word;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
/**
 * 
 * @author Kirill(github.com/TieLieFaw)
 *
 */
public final class soundChooseRandomTesterController extends AbstractDefaultController {

	private Word word;
	private String userTranslate;

	public soundChooseRandomTesterController(AbstractLogic logic, List<Word> dictionary, ApplicationContainer container) {
		super(logic,dictionary, container);
	}

	@FXML
	private Label resultLabel;

	@FXML
	private Button playButton;

	@FXML
	private Button firstOption;

	@FXML
	private Label firstOptionLabel;

	@FXML
	private Button secondOption;

	@FXML
	private Label secondOptionLabel;

	@FXML
	private Button thirdOption;

	@FXML
	private Label thirdOptionLabel;

	@FXML
	private Button exitButton;

	@Override
	public void setWord() {
Word[] wordCases = new Word[3];
		
		final Task<Void> setWordTask = new Task<Void>() {
			@Override
			public Void call() throws Exception {

				word = logic.getWord(dictionary);
				
				wordCases[0] = word;

				if(dictionary.size() == 1) {
					wordCases[1] = word;
					wordCases[2] = word;

					} else if(dictionary.size() == 2) {

						Word secondWord = logic.getWord(dictionary);
							while(secondWord.getValue() == word.getValue())
								secondWord = logic.getWord(dictionary);
							wordCases[1] = secondWord;
							wordCases[2] = secondWord;

						} else if(dictionary.size() >= 3) {

							Word secondWord = logic.getWord(dictionary);
							Word thirdWord = logic.getWord(dictionary);

							while(secondWord.getValue().equals(word.getValue()))
								secondWord = logic.getWord(dictionary);

							verifyEqual: while(thirdWord.getValue().equals(word.getValue()) | thirdWord.getValue().equals(secondWord.getValue())) {

								if(thirdWord.getValue().equals(word.getValue())) {
									thirdWord = logic.getWord(dictionary);
									continue verifyEqual;
								}

								if(thirdWord.getValue().equals(secondWord.getValue())) {
									thirdWord = logic.getWord(dictionary);
									continue verifyEqual;
								}
							}

							wordCases[1] = secondWord;
							wordCases[2] = thirdWord;

							}
				return null;
			}
		};
		
		setWordTask.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				
				List<Integer> sequence = new ArrayList<Integer>(3);
				sequence.add(0);
				sequence.add(1);
				sequence.add(2);
				Collections.shuffle(sequence);
				
				
				firstOptionLabel.setText(wordCases[sequence.get(0)].getTranslate());
				secondOptionLabel.setText(wordCases[sequence.get(1)].getTranslate());
				thirdOptionLabel.setText(wordCases[sequence.get(2)].getTranslate());

			}
		});
		
		Thread t = new Thread(setWordTask);
		t.setDaemon(true);
		t.start();
		
		try {
			t.join();
		} catch (InterruptedException e) {
			throw new RuntimeException("soundChooseRandomTesterController:#setWord()", e);
		}
		
	}
	
	@FXML
	public void firstOptionAction() {
		userTranslate = firstOptionLabel.getText();
		verify();
	}
	
	@FXML
	public void secondOptionAction() {
		userTranslate = secondOptionLabel.getText();
		verify();
	}
	
	@FXML
	public void thirdOptionAction() {
		userTranslate = thirdOptionLabel.getText();
		verify();
	}

	@Override
	public void verify() {
		
		final Task<Boolean> verifyTranslateTask = new Task<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				return logic.inspect(word, userTranslate);
			}
		};
		
		verifyTranslateTask.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				Boolean verification = null;
				
				try {
					verification = verifyTranslateTask.get();
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
				
				if(verification.booleanValue() == true) {
					setTrueResult(userTranslate);
				} else {
					setFalseResult(userTranslate);
				}
				
			}
		});
		
		Thread t = new Thread(verifyTranslateTask);
		t.setDaemon(true);
		t.start();
		
		try {
			t.join();
		} catch (InterruptedException e) {
			throw new RuntimeException("soundChooseRandomTesterController:#verify()", e);
		}
		
		setWord();
	}
	
	private void setTrueResult(String result) {
		resultLabel.setText(new StringBuilder().append("Your translate: ").append("'").append(result).append("'").append(" is correct").toString());
		resultLabel.setTextFill(Color.web("#04ff00"));
	}
	
	private void setFalseResult(String result) {
		resultLabel.setText(new StringBuilder().append("Your translate: ").append("'").append(result).append("'").append(" isn't correct").append(" That's right - ").append("'").append(word.getTranslate()).append("'").toString());
		resultLabel.setTextFill(Color.web("#ff4500"));
	}

	@Override
	public void exit() {
		super.exit();
	}
	
	@FXML
	public void playSound() {
		final Task<Void> playWordSound = new Task<Void>() {
			@Override
			public Void call() throws Exception {
				word.playSound();
				return null;
			}
		};
		
		Thread t = new Thread(playWordSound);
		t.setDaemon(true);
		t.start();
		
		try {
			t.join();
		} catch (InterruptedException e) {
			throw new RuntimeException("soundChooseRandomTesterController:#playSound()", e);
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		firstOption.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("cursor.png"))));
		secondOption.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("cursor.png"))));
		thirdOption.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("cursor.png"))));
		playButton.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("playButtonIcon.png"))));
	}

}