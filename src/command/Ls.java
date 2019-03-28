package command;

import java.util.*;

import facade.*;

/**
 * @author RDJ
 *
 */
public class Ls implements Command {

	private ArrayList<FSElement> children;
	private String args[];

	public Ls(String args[]) {
		this.children = FileSystem.getCurrent().getChildren();
		this.args = args;
	}

	/*
	 * @see command.Command#execute()
	 */
	@Override
	public void execute() {
		// add to History

		int i = 0;
		if (this.children.size() > 0) {
			try {
				// add to history
				FileSystem.setHistory(args);
				
				//get children
				//children = FileSystem.getCurrent().getChildren();
				// print the current directory's children
				for (FSElement f : children) {
					i += 1;
					System.out.print(f.getName() + "   ");
					if (i > 3) {
						System.out.println("\n");
						i = 0;
					}
				}
				System.out.println();
			} catch (Exception e) {
				System.out.println("Invalid..");
			}
		} else {
			System.out.println("No directories to list");
		}
	}

	/**
	 * @return the args
	 */
	public String[] getArgs() {
		return args;
	}

	/**
	 * @return the children
	 */
	public ArrayList<FSElement> getChildren() {
		return children;
	}

}
