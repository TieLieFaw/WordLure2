package Logic;

import java.util.List;

import base.Word;
/**
 * 
 * @author Kirill(github.com/TieLieFaw)
 *
 */
public abstract class AbstractConsistentLogic extends AbstractLogic {

	public AbstractConsistentLogic(String firstLanguage, String secondLanguage) {
		super(firstLanguage, secondLanguage);
	}
	
	private int currentWordCounter = 0;
	
	@Override
	public Word getWord(List<Word> data) {
		if(currentWordCounter > data.size() - 1) currentWordCounter = 0;
		return data.get(currentWordCounter++);
	}

}
