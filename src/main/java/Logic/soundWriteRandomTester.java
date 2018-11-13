package Logic;

import base.Word;
/**
 * 
 * @author Kirill(github.com/TieLieFaw)
 *
 */
public final class soundWriteRandomTester extends AbstractRandomLogic {

	public soundWriteRandomTester(String firstLanguage, String secondLanguage) {
		super(firstLanguage, secondLanguage);
	}
	
	@Override
	public boolean inspect(Word w, String userValue) {
		if(userValue == null || userValue.length() == 0) return false;
		if(w.getValue().equals(userValue)) return true;
		return false;
	}

	@Override
	public String getName() {
		return "Listen and write(Random)";
	}

}