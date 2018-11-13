package base;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.junit.Test;

import Logic.AbstractLogic;
/**
 * 
 * @author Kirill(github.com/TieLieFaw)
 *
 */
public class DictionaryTest {

	@Test
	public void testLoadDictionary() throws IOException{
		File file = new File("src/test/java/base/TestDictionary.txt");
		Dictionary dictionary = Dictionary.loadDictionary(file);
		List<Word> wordList = dictionary.getDictionary();
		List<String> stringList = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8).stream().skip(1).collect(Collectors.toList());
		assertTrue("Method incorrectly loaded data from file",wordList.size() == stringList.size());
		for(int j = 0; j < wordList.size(); j++) {
			List<String> splittedWord = Arrays.asList(stringList.get(j).split(Pattern.quote("/")));
			assertEquals(wordList.get(j).getPartOfSpeech(), splittedWord.get(0));
			assertEquals(wordList.get(j).getValue(), splittedWord.get(1));
			assertEquals(wordList.get(j).getTranscription(), splittedWord.get(2));
			assertEquals(wordList.get(j).getTranslate(), splittedWord.get(3));
		}
	}
	
	@Test(expected = IOException.class)
	public void testLoadDictionaryThrowsIOException() throws IOException {
		File file = new File("src/test/java/base/TestDictionary1.txt");
		@SuppressWarnings("unused")
		Dictionary dictionary = Dictionary.loadDictionary(file);
	}

	@Test
	public void testGetDefaultLogic() throws IOException {
		File file = new File("src/test/java/base/TestDictionary.txt");
		Dictionary dictionary = Dictionary.loadDictionary(file);
		AbstractLogic[] logics = dictionary.getDefaultLogic();
		for(AbstractLogic a : logics) {
			assertTrue(a instanceof AbstractLogic);
		}
	}

}