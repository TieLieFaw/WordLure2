package Logic;

import base.Word;
/**
 * 
 * @author Kirill(github.com/TieLieFaw)
 *
 */
public final class aheadRandomTester extends AbstractRandomLogic {

	public aheadRandomTester(String firstLanguage, String secondLanguage) {
		super(firstLanguage, secondLanguage);
	}

	@Override
	public boolean inspect(Word w, String userTranslate) {
		if(userTranslate == null || userTranslate.length() == 0) return false;
		if(w.getTranslate().equals(userTranslate)) return true;
		return false;
	}

	@Override
	public String getName() {
		return new StringBuilder().append("Random translate from ").append(getFirstLanguage()).append(" into ").append(getSecondLanguage()).toString();
	}

}