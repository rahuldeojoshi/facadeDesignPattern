/**
 * 
 */
package visit;

import java.util.ArrayList;

import facade.Directory;
import facade.File;
import facade.Link;

/**
 * @author RDJ
 *
 */
public class FSElementVisit implements FSVisitor{

	private String name;
	private ArrayList<Directory> foundDirs;
	private ArrayList<File> foundFiles;
	private ArrayList<Link> foundLinks;

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
	 * @return the foundFiles
	 */
	public ArrayList<File> getFoundFiles() {
		return foundFiles;
	}
	/**
	 * @return the foundLinks
	 */
	public ArrayList<Link> getFoundLinks() {
		return foundLinks;
	}
	public FSElementVisit(String name){
		this.name = name;
		this.foundDirs = new ArrayList<Directory>();
		this.foundFiles = new ArrayList<File>();
		this.foundLinks = new ArrayList<Link>();
	}
	@Override
	public void visit(Link link) {
		if(link.getName().contains(name)){
			foundLinks.add(link);
		}		
	}

	@Override
	public void visit(Directory dir) {
		if(dir.getName().contains(name)){
			foundDirs.add(dir);
		}		
	}

	@Override
	public void visit(File file) {
		if(file.getName().contains(name)){
			foundFiles.add(file);
		}		
	}

}
