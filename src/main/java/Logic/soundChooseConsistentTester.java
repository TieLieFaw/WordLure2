package Logic;

import base.Word;
/**
 * 
 * @author Kirill(github.com/TieLieFaw)
 *
 */
public final class soundChooseConsistentTester extends AbstractConsistentLogic {

	public soundChooseConsistentTester(String firstLanguage, String secondLanguage) {
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
		return "Listen and choose (Consistent)";
	}

}