/**
 * The Logic package is a standard set of classes that describe the logic of the program.<br>
 * The Logic package includes the following abstract classes:<br>
 * <br>
 * {@code AbstractLogic} is the abstract class which defines behavior all subclasses.<br>
 * Defines the main features of all subclasses of Logic in the program.<br>
 * <br>
 * {@code AbstractConsistentLogic} is the abstract class which defines behavior all subclasses<br>
 * which based on consistent output of words from the dictionary.<br>
 * <br>
 * {@code AbstractRandomLogic} is the abstract class which defines behavior all subclasses<br>
 * which based on random output of words from the dictionary.<br>
 * <br>
 * Classes which ends with "Tester" is concrete implementation one of the abstract classes<br>
 * and contain program logic that can be used in the working process.<br>
 * <br>
 * Classes {@code aheadConsistentTester} and {@code aheadRandomTester} define program logic<br>
 * wherein the word is given in the original language and you need to choose one of three translation options<br>
 * <br>
 * Classes {@code backConsistentTester} and {@code backRandomTester} define program logic<br>
 * wherein the word is given in the language into which it is necessary to translate it,<br>
 * and it is necessary to write the word in the original language<br>
 * <br>
 * Classes {@code soundChooseConsistentTester}, {@code soundChooseRandomTester} and {@code soundWriteConsistentTester}, {@code soundWriteRandomTester} define program logic<br>
 * wherein you must listen to the pronunciation of the word in the original language and<br>
 * record or select a translation variant.
 * @author Kirill(github.com/TieLieFaw)
 */
package Logic;