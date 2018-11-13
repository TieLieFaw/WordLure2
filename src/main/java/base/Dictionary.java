package base;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Pattern;

import Logic.AbstractLogic;
/**
 * <p>
 * The Dictionary class provides the program dictionary which contains the object of type {@code List<Word>}<br>
 * that contains the {@code Word} objects, and varied methods to work with it.<br>
 * <p>
 * <p>
 * The dictionary class doesn't has public constructor, but have static method {@code loadDictionary(File file)}<br>
 * which creates a {@code Dictionary} object using private class constructor and return it.<br>
 * The class contains methods for loading data from .txt file in object of type {@code List<Word>} and<br>
 * creating classes of algorithms.<br>
 * <p>
 * <br>
 * <br>
 * The Dictionary class has two field:<br>
 * <br>
 * <p>
 * 1) {@code dictionary} - object of type {@code CopyOnWriteArrayList<Word>} which contains objects of type {@code Word}.<br>
 * The specific implementation of the interface {@code List<>} is {@code CopyOnWriteArrayList<>} due to the fact<br>
 * that {@code CopyOnWriteArrayList<>} is a thread-safe collection.<br>
 * <p>
 * <br>
 * <p>
 * 2) {@code languages} - Array of type String which contains two value - first and second language.<br>
 * First language is language of any word, second language is language of word's translation.<br>
 * The meanings of the languages ​​of words are taken from the first line in the .txt file.<br>
 * <p>
 * <br>
 * <br>
 * To create the Dictionary object you need to call the {@code loadDictionary(File file)} method:<br>
 * <br>
 * <hr><blockquote><pre>
 *     File f = new File("any path...");
 *     Dictionary d = Dictionary.loadDictionary(f);
 * </pre></blockquote><hr>
 * To get object of type List<> that contains words, you must call method {@code getDictionary()}
 * <hr><blockquote><pre>
 *     List<Word> dictionaryList = d.getDictionary();
 *     
 *     or
 *     
 *     CopyOnWriteArrayList<Word> dictionaryList = d.getDictionary();
 * </pre></blockquote><hr>
 * To get a classes of testing algorithms, you must call {@code getDefaultLogic()} method<br>
 * It should be noted that for two different pairs of languages(for example: Japan - English and English - Russian),<br>
 * the  {@code getDefaultLogic()} method will return different sets of testing classes
 * <hr><blockquote><pre>
 *     File f = new File("any path...");
 *     Dictionary d = Dictionary.loadDictionary(f);
 *     
 *     AbstractLogic[] standartLogic = d.getDefaultLogic();
 * </pre></blockquote><hr>
 * <br>
 * Rules for the design of the dictionary file:<br>
 * At the beginning, the source language of the words is set and the language to which the word is translated is indicated<br>
 * through a slash.It is noteworthy, the slash must also appear before the name of the first language.<br>
 * Example:<br>
 * <hr><blockquote><pre>
 *     /English/Russian
 *     /Japanese/French
 * </pre></blockquote><hr>
 * <br>
 * Further, in each subsequent line the words in the following form are indicated:<br>
 * <hr><blockquote><pre>
 *     Part of Speech/Word in original language/Transcription of the word in the original language/A translation of a word
 * </pre></blockquote><hr>
 * <br>
 * Each word takes one line. If there is a sound file of the word in the original language, then it is placed in the same<br>
 * directory with the dictionary file.<br>
 * It is important that the vocabulary file is encoded in UTF-8.<br>
 * <br>
 * @author Kirill(github.com/TieLieFaw)
 */
public class Dictionary {
	/**
	 * Object of type {@code CopyOnWriteArrayList<Word>}
	 */
	private CopyOnWriteArrayList<Word> dictionary;
	/**
	 * Array of languages
	 */
	private String[] languages;
	
	private Dictionary(ArrayList<Word> wordList, String[] languagesArray) {
		dictionary = new CopyOnWriteArrayList<>(wordList);
		languages = languagesArray;
	}
	/**
	 * Method loads a strings with word's descriptions and places their in object of type {@code List<Word>}.<br>
	 * Method returns a new Dictionary object.
	 * 
	 * @param file Object of type {@code File} which represents a vocabulary file(.txt) on disk
	 * @return new Dictionary object which contains object of type {@code List<Word>} wherein contains words<br>
	 * and array of languages
	 * @throws IOException thrown if invalid path to dictionary file
	 */
	public static Dictionary loadDictionary(File file) throws IOException {
			List<String> list = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
			
			ArrayList<Word> wordList = new ArrayList<>();
			
			String s = list.stream().findFirst().get();
			String[] languagesArray = s.split("/", 3);
			
			list.stream().skip(1).forEach(e -> { String[] splitWord = e.split(Pattern.quote("/"));
			Word newWord = new Word(splitWord[0], splitWord[1], splitWord[2], splitWord[3]);
			newWord.setSound(new StringBuilder().append(file.getParent()).append(newWord.getValue()).append(".wav").toString());
			wordList.add(newWord);
			});

			return new Dictionary(wordList, languagesArray);
	}
	/**
	 * Method returns specific set of program logic for the current object type Dictionary
	 * @return specific set of program logic for the current object type Dictionary
	 */
	public AbstractLogic[] getDefaultLogic() {
		String[] classes;
		try {
			classes = WordLureSystem.getTestersClassName("Logic");
		} catch (ClassNotFoundException | IOException e) {
			throw new RuntimeException(e);
		}
		
		ArrayList<AbstractLogic> list = new ArrayList<>();
		
		for(String s : classes){
				try {
					list.add((AbstractLogic) Class.forName(s).getConstructor(String.class, String.class).newInstance(languages[1], languages[2]));
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException | NoSuchMethodException | SecurityException
						| ClassNotFoundException e) {
					throw new RuntimeException(e);
				}
			}
		
		return list.toArray(new AbstractLogic[list.size()]);
	}
	
	public CopyOnWriteArrayList<Word> getDictionary() {
		return dictionary;
	}
	
}