package base;
/**
 * The WordLureSystem class is the special class which provides funds required for the program
 * 
 * @author Kirill(github.com/TieLieFaw)
 *
 */
public class WordLureSystem {
	/**
	 * Special method which returns the names of specific classes of testing algorithms.(Names of specific classes of testing algorithms ends in string "Tester")
	 */
	public static String[] getTestersClassName() {
		String[] testersClassNames = new String[8];
		testersClassNames[0] = "Logic.aheadConsistentTester";
		testersClassNames[1] = "Logic.aheadRandomTester";
		testersClassNames[2] = "Logic.backConsistentTester";
		testersClassNames[3] = "Logic.backRandomTester";
		testersClassNames[4] = "Logic.soundChooseConsistentTester";
		testersClassNames[5] = "Logic.soundChooseRandomTester";
		testersClassNames[6] = "Logic.soundWriteConsistentTester";
		testersClassNames[7] = "Logic.soundWriteRandomTester";
		return testersClassNames;
	}
}