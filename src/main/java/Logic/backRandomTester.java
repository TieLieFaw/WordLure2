package Logic;

import base.Word;
/**
 * 
 * @author Kirill(github.com/TieLieFaw)
 *
 */
public final class backRandomTester extends AbstractRandomLogic {

	public backRandomTester(String firstLanguage, String secondLanguage) {
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
		return new StringBuilder().append("Random translate from ").append(getSecondLanguage()).append(" into ").append(getFirstLanguage()).toString();
	}
	
}