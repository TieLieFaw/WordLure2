package Logic;

import java.util.List;

import org.apache.commons.math3.random.Well19937c;

import base.Word;
/**
 * 
 * @author Kirill(github.com/TieLieFaw)
 *
 */
public abstract class AbstractRandomLogic extends AbstractLogic {

	public AbstractRandomLogic(String firstLanguage, String secondLanguage) {
		super(firstLanguage, secondLanguage);
	}

	private int previousNumber = 0;
	private int nextNumber = 0;
	private Well19937c generator = new Well19937c();
	
	@Override
	public Word getWord(List<Word> data) {
		previousNumber = nextNumber;
		nextNumber = generator.nextInt(data.size());
		while(nextNumber == previousNumber)
			nextNumber = generator.nextInt(data.size());
		return data.get(nextNumber);
	}

}