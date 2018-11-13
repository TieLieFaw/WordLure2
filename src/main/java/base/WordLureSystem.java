package base;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;
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
	@SuppressWarnings("rawtypes")
	public static String[] getTestersClassName(String packageName) throws ClassNotFoundException, IOException {
		Class[] classes = getClasses(packageName);
		
		List<String> classNames = new ArrayList<>();
		
		for(Class c : classes) {
			String className = c.getName();
			if(className.indexOf("Tester") != -1)
				classNames.add(className);
		}
		
		classNames = classNames.stream().filter(s -> s.endsWith("Tester")).collect(Collectors.toList());
		
		return classNames.toArray(new String[classNames.size()]);
	}
	
	@SuppressWarnings("rawtypes")
	public static Class[] getClasses(String packageName) throws ClassNotFoundException, IOException {
	    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	    String path = packageName.replace('.', '/');
	    Enumeration<URL> resources = classLoader.getResources(path);
	    List<File> dirs = new ArrayList<File>();
	    while (resources.hasMoreElements()) {
	        URL resource = resources.nextElement();
	        dirs.add(new File(resource.getFile()));
	    }
	    ArrayList<Class> classes = new ArrayList<Class>();
	    for (File directory : dirs) {
	        classes.addAll(findClasses(directory, packageName));
	    }
	    return classes.toArray(new Class[classes.size()]);
	}
	
	@SuppressWarnings("rawtypes")
	private static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
	    List<Class> classes = new ArrayList<Class>();
	    
	    if (!directory.exists()) {
	        return classes;
	    }
	    
	    File[] files = directory.listFiles();
	    
	    for (File file : files) {
	        if (file.isDirectory()) {
	            classes.addAll(findClasses(file, packageName + "." + file.getName()));
	        } else if (file.getName().endsWith(".class")) {
	            classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
	        }
	    }
	    return classes;
	}
}