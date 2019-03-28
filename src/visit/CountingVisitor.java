package visit;

import facade.*;

/**
 * @author RDJ
 *
 */
public class CountingVisitor implements FSVisitor {


	private int numDirs;
	private int numLinks;
	private int numFiles;

	
	/**
	 * @return the numDirs
	 */
	public int getNumDirs() {
		return numDirs;
	}
	
	/**
	 * @return the numLinks
	 */
	public int getNumLinks() {
		return numLinks;
	}

	/**
	 * @return the numFiles
	 */
	public int getNumFiles() {
		return numFiles;
	}

	@Override
	public void visit(Link link) {
		numLinks++;		
	}

	@Override
	public void visit(Directory dir) {
		numDirs++;
	}

	@Override
	public void visit(File file) {
		numFiles++;
	}

}
