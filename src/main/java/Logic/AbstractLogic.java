package Logic;

import java.util.List;

import base.Word;
/**
 * 
 * @author Kirill(github.com/TieLieFaw)
 *
 */
public abstract class AbstractLogic {
	
	private String firstLanguage;
	private String secondLanguage;
	
	public AbstractLogic(String firstLanguage, String secondLanguage) {
		this.firstLanguage = firstLanguage;
		this.secondLanguage = secondLanguage;
	}
	
	protected String getFirstLanguage() {
		return firstLanguage;
	}
	
	protected String getSecondLanguage() {
		return secondLanguage;
	}
	
	public abstract boolean inspect(Word w, String userData);
	
	public abstract Word getWord(List<Word> data);
	
	public abstract String getName();

}