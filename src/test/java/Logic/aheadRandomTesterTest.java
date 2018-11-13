package Logic;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import base.Word;
/**
 * 
 * @author Kirill(github.com/TieLieFaw)
 *
 */
public class aheadRandomTesterTest {

	private final String FIRST_LANGUAGE = "English";
	private final String SECOND_LANGUAGE = "Russian";
	
	@Test
	public void testGetName() {
		AbstractLogic logic = new aheadRandomTester(FIRST_LANGUAGE, SECOND_LANGUAGE);
		assertEquals("Logic's class returns incorrect name",logic.getName(), new StringBuilder().append("Random translate from ").append(FIRST_LANGUAGE).append(" into ").append(SECOND_LANGUAGE).toString());
	}

	@Test
	public void testInspect() {
		AbstractLogic logic = new aheadRandomTester(FIRST_LANGUAGE, SECOND_LANGUAGE);
		Word firstWord = new Word("Noun", "age", "eɪdʒ", "возраст");
		assertTrue("Logic's class incorrectly performs equality check", logic.inspect(firstWord, firstWord.getTranslate()));
		assertFalse("Logic's class incorrectly performs different case arguments", logic.inspect(firstWord, firstWord.getTranslate().toUpperCase()));
		assertFalse("Logic's class incorrectly performs equality check", logic.inspect(firstWord, "способность"));
		assertFalse("Logic's class incorrectly processes the argument null", logic.inspect(firstWord, null));
		assertFalse("Logic's class incorrectly processes the zero length argument", logic.inspect(firstWord, ""));
	}
	
	@Test
	public void testGetWord() {
		AbstractLogic logic = new aheadRandomTester(FIRST_LANGUAGE, SECOND_LANGUAGE);
		List<Word> dictionary = new ArrayList<Word>();
		dictionary.add(new Word("Noun","age","eɪdʒ","возраст , век"));
		dictionary.add(new Word("Noun","able","ˈeɪb(ə)l","способный"));
		dictionary.add(new Word("Noun","ability","əˈbɪlɪti","способность"));
		dictionary.add(new Word("Verb","earn","əːn","зарабатывать"));
		
		Word temp = logic.getWord(dictionary);
		Word temp2 = logic.getWord(dictionary);
		assertFalse(temp.equals(temp2));
		temp = logic.getWord(dictionary);
		temp2 = logic.getWord(dictionary);
		assertFalse(temp.equals(temp2));
		temp = logic.getWord(dictionary);
		temp2 = logic.getWord(dictionary);
		assertFalse(temp.equals(temp2));
	}

}