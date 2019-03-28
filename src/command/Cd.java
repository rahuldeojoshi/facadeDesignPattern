/**
 * 
 */
package command;

import java.util.ArrayList;

import facade.*;

/**
 * @author RDJ
 *
 */
public class Cd implements Command {

	private Directory dirName;
	private Directory current;
	private String args[];

	public Cd(String args[]) {
		this.args = args;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see command.Command#execute()
	 */
	@Override
	public void execute() {
		try {
			// add to History
			FileSystem.setHistory(args);
			// Check for arguments
			if (args.length == 1) {
				// Set to root when plain CD command
				FileSystem.setCurrent(FileSystem.getRootDir());
			} else if (args.length > 1) {

				if (args[1].equals("..")) {
					// Move one Directory up when CD with ".."
					this.current = FileSystem.getCurrent().getParent();
					if(this.current instanceof Directory){
						FileSystem.setCurrent(current);
					}else{
						System.out.println("This is root folder, cannot move up.");
					}
				} else {
					/*
					 * Check for Directory when CD with 'Directory Name'
					 * setCurrent to 'Directory Name' if its a child of current
					 */
					ArrayList<FSElement> search = new ArrayList<FSElement>();
					search = FileSystem.getCurrent().getChildren();
					boolean found = false;

					// Search for the Directory
					for (FSElement f : search) {
						if (f.getName().contains(args[1])) {
							this.dirName = (Directory) f;
							found = true;
						}
					}
					if (found) {
						// setCurrent if directory found
						FileSystem.setCurrent(dirName);
					} else {
						System.out.println("No such directory in " + FileSystem.getCurrent().getName());
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Usage: cd || cd <dirName> || cd ..");
		}
	}

	/**
	 * @return the dirName
	 */
	public Directory getDirName() {
		return dirName;
	}

	/**
	 * @return the current
	 */
	public Directory getCurrent() {
		return current;
	}

	/**
	 * @return the args
	 */
	public String[] getArgs() {
		return args;
	}

}

// public Cd(Directory dir){
// ArrayList<FSElement> search = new ArrayList<FSElement>();
// search = FileSystem.getCurrent().getChildren();
// boolean found=false;
// for(FSElement f: search){
// if(f.equals(dir)){
// found=true;
// }
// }
// if(found){
// this.dirName = dir;
// }else{
// System.out.println("No such directory in " +
// FileSystem.getCurrent().getName());
// }
// }
// public Cd(){
// }
// public Cd(String str){
// if(str == ".."){
// this.current = FileSystem.getCurrent().getParent();
// FileSystem.setCurrent(current);
// }
// }
