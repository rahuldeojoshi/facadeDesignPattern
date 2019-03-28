/**
 * 
 */
package command;

import facade.*;
import visit.*;

/**
 * @author RDJ
 *
 */
public class Chown implements Command {

	private String owner;
	private FSElement element, found;
	private String[] args;
	/*
	 * (non-Javadoc)
	 * 
	 * @see command.Command#execute()
	 */

	/**
	 * @return the owner
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * @return the element
	 */
	public FSElement getElement() {
		return element;
	}

	/**
	 * @return the found
	 */
	public FSElement getFound() {
		return found;
	}

	/**
	 * @return the args
	 */
	public String[] getArgs() {
		return args;
	}

	public Chown(String[] args) {
		this.args = args;
	}

	@Override
	public void execute() {
		try {
			this.owner = args[1];
			FileSystem.setHistory(args);
			this.element = FileSystem.getRootDir();
			
			// Visit FSElement
			FSElementVisit visit = new FSElementVisit(args[2]);
			element.accept(visit);
			
			/*
			 * change owner of respective FSElement
			 */
			if (visit.getFoundFiles().size() > 0) {
				
				// check foundFiles
				for (FSElement f : visit.getFoundFiles()) {
					if (f.getName().contains(args[2])) {
						found = (File) f;
					}
					found.setOwner(owner);
				}
			} else if (visit.getFoundDirs().size() > 0) {
				
				//check foundDirs
				for (FSElement f : visit.getFoundDirs()) {
					if (f.getName().contains(args[2])) {
						found = (Directory) f;
					}
					found.setOwner(owner);
				}
			} else if(visit.getFoundLinks().size() > 0){
				
				//check foundLinks
				for (FSElement f : visit.getFoundLinks()) {
					if (f.getName().contains(args[2])) {
						found = (Link) f;
					}
					found.setOwner(owner);
				}
			}else{
				System.out.println("Element not found in the current Directory");
			}
		} catch (Exception e) {
			System.out.println("Usage: chown <new owner> <file/dir/link name>");
		}
	}


}
