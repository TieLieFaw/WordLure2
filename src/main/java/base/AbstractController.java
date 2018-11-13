package base;
/**
 * The interface AbstractController provides methods which classes of FXML's controllers must have
 * 
 * @author Kirill(github.com/TieLieFaw)
 *
 */
public interface AbstractController {
	/**
	 * The method {@code setWord()} will define method mounting a new word in graphics interface
	 */
	public void setWord();
	
	/**
	 * The method {@code verify()} will contains a data validation algorithm
	 */
	public void verify();
	
	/**
	 * The method {@code exit()} will define exit order from testing process
	 */
	public void exit();
}