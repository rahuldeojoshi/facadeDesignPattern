/**
 * 
 */
package visit;

import java.util.ArrayList;

import facade.*;

/**
 * @author RDJ
 *
 */
public class DirSearchVisitor implements FSVisitor {

	private String name;
	private ArrayList<Directory> foundDirs;
	

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the foundDirs
	 */
	public ArrayList<Directory> getFoundDirs() {
		return foundDirs;
	}

	/**
	 * 
	 */
	public DirSearchVisitor(String name) {
		this.name = name;
		foundDirs = new ArrayList<Directory>();
	}

	/* (non-Javadoc)
	 * @see visitor.FSVisitor#visit(filesystem.Link)
	 */
	@Override
	public void visit(Link link) {
	}

	/* (non-Javadoc)
	 * @see visitor.FSVisitor#visit(filesystem.Directory)
	 */
	@Override
	public void visit(Directory dir) {
		if(dir.getName().contains(name)){
			foundDirs.add(dir);
		}
	}

	/* (non-Javadoc)
	 * @see visitor.FSVisitor#visit(filesystem.File)
	 */
	@Override
	public void visit(File file) {	
	}

}
