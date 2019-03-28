/**
 * 
 */
package command;

import facade.File;
import facade.FileSystem;

/**
 * @author RDJ
 *
 */
public class Touch implements Command {

	private File newFile;
	private String[] args;

	public Touch(String[] args) {
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
			FileSystem.setHistory(args);
			newFile = new File(FileSystem.getCurrent(), args[1], FileSystem.getCurrent().getOwner(), 1);
			System.out.println("File " + newFile.getName() + " created.");
		} catch (Exception e) {
			System.out.println("Usage: touch <new file name>");
		}
	}

}
