package base;

import static org.junit.Assert.*;

import org.junit.Test;
/**
 * 
 * @author Kirill(github.com/TieLieFaw)
 *
 */
public class WordTest {

	@Test
	public void testHashCode() {
		Word x = new Word("Noun", "Car", "kahr", "Машина");
		Word y = new Word("Noun", "Car", "kahr", "Машина");
		assertTrue(x.hashCode() == y.hashCode());
	}
	
	@Test
	public void testEqualsObject() {
		Word x = new Word("Noun", "Car", "kahr", "Машина");
		Word y = new Word("Noun", "Car", "kahr", "Машина");
		assertEquals(x, y);
	}
	
	@Test
	public void equalsIsReflexive() {
		Word x = new Word("Noun", "Car", "kahr", "Машина");
		assertTrue(x.equals(x));
	}
	
	@Test
	public void equalsIsSymmetric() {
		Word x = new Word("Noun", "Car", "kahr", "Машина");
		Word y = new Word("Noun", "Car", "kahr", "Машина");
		assertTrue(x.equals(y) && y.equals(x));
	}
	
	@Test
	public void equalsIsTransitive() {
		Word x = new Word("Noun", "Car", "kahr", "Машина");
		Word y = new Word("Noun", "Car", "kahr", "Машина");
		Word z = new Word("Noun", "Car", "kahr", "Машина");
		assertTrue(x.equals(y)  && y.equals(z) && x.equals(z));
	}
	
	@Test
	public void equalsReturnFalseOnNull() {
		Word x = new Word("Noun", "Car", "kahr", "Машина");
		Word y = new Word("Verb", "Consider", "kənˈsɪdə", "Рассматривать");
		assertFalse(x.equals(y) && x.equals(null));
	}
	
	@Test
	public void equalsNotSame() {
		Word x = new Word("Noun", "Car", "kahr", "Машина");
		Word y = new Word("Noun", "Car", "kahr", "Машина");
		assertNotSame(x, y);
	}
	
	@Test
	public void equalsReturnNull() {
		Word x = new Word("Noun", "Car", "kahr", "Машина");
		assertFalse(x.equals(null));
	}

}