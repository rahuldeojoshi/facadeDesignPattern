package facade;

import java.util.ArrayList;


/**
 * @author RDJ
 *
 */
public class FileSystem {
	
	

	private static ArrayList<String[]> history;
	/**
	 * @return the history
	 */
	public static ArrayList<String[]> getHistory() {
		return history;
	}

	/**
	 * @param history the history to set
	 */
	public static void setHistory(String[] history) {
		FileSystem.history.add(history);
	}

	private static Directory current;
	//private static ArrayList<FSElement> getChildren;
	private static Directory rootDir = new Directory(null, "root", "Rahul", 0);;
	private static FileSystem fileSystem = new FileSystem();

	/**
	 * private constructor to avoid instantiation
	 */
	private FileSystem() {
	//	getChildren = new ArrayList<FSElement>();
		history = new ArrayList<String[]>();
		FileSystem.setCurrent(rootDir);
	}

	/**
	 * @return the fileSystem
	 */
	public static FileSystem getFileSystem() {
		return fileSystem;
	}

	/**
	 * @param rootDir
	 *            the rootDir to set
	 */
	public static void setRootDir(Directory rootDir) {
		FileSystem.rootDir = rootDir;
	}

	/**
	 * @return the rootDir
	 */
	public static Directory getRootDir() {
		return rootDir;
	}

	/**
	 * @return the current
	 */
	public static Directory getCurrent() {
		return current;
	}

	/**
	 * @param current
	 *            the current to set
	 */
	public static void setCurrent(Directory current) {
		FileSystem.current = current;
	}

//	/**
//	 * @return the getChildren
//	 */
//	public static ArrayList<FSElement> getGetChildren() {
//		return getChildren;
//	}
//
//	/**
//	 * @param getChildren
//	 *            the getChildren to set
//	 */
//	public static void setGetChildren(ArrayList<FSElement> getChildren) {
//		FileSystem.getChildren = getChildren;
//	}

	/**
	 * Show the complete file system
	 */
	public void showAllElements(Directory directory) {
		// get the directory children
		ArrayList<FSElement> fs = directory.getChildren();

		// for traversing
		for (FSElement fsElement : fs) {

			// Check if the element is directory, call readChildren if true

			if (!fsElement.isLeaf()) {
				// Printing Directory visited
				if (directory.getParent() == null)
					System.out.println(directory.getName() + "-->" + fsElement.getName());
				else
					System.out.println(directory.getParent().getName() + "-->" + directory.getName() + "-->"
							+ fsElement.getName());

				// Type cast the FSElement to call getChildren of the
				// encountered Directory
				Directory rec = (Directory) fsElement;

				// recursive call
				showAllElements(rec);
			} else {
				// printing the leaf
				if (directory.getParent() == null)
					System.out.println(directory.getName() + "-->" + fsElement.getName());
				else
					System.out.println(directory.getParent().getName() + "-->" + directory.getName() + "-->"
							+ fsElement.getName());
			}
		}

	}

//	/**
//	 * @param Directory
//	 * @param FSElement
//	 */
//	public static void addChild(Directory current, FSElement child) {
//		FileSystem.getChildren.add(child);
//	}
}