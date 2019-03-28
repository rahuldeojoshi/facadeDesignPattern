package command;

import java.util.ArrayList;

import facade.*;

/**
 * @author RDJ
 *
 */
public class Pwd implements Command {

	// Data Fields
	private ArrayList<FSElement> parents = new ArrayList<FSElement>();
	private Directory dir;
	private String[] args;

	/*
	 * Constructor initiates 'parents' ArrayList gets FileSystem gets fileSystem
	 * current Directory fills 'parents' by calling getParent()
	 */
	public Pwd(String[] args) {
		this.args = args;
		this.dir = FileSystem.getCurrent();
		getParent(this.dir);
	}

	/*
	 * @see command.Command#execute()
	 */
	@Override
	public void execute() {
		try {
			FileSystem.setHistory(args);
			// PWD printing all the parents from currentDirectory
			for (int i = parents.size() - 1; i >= 0; i--) {
				System.out.print("/" + parents.get(i).getName());
			}
			System.out.println();
		} catch (Exception e) {
			System.out.println("Usage: pwd");
		}

	}

	/*
	 * printing PWD logic
	 */
	public void getParent(Directory parent) {
		// check for parents all the way up to root
		if (parent != null) {
			this.parents.add(parent);
			getParent(parent.getParent());
		}
	}

	/**
	 * @return the parents
	 */
	public ArrayList<FSElement> getParents() {
		return parents;
	}

	/**
	 * @return the dir
	 */
	public Directory getDir() {
		return dir;
	}

	/**
	 * @return the args
	 */
	public String[] getArgs() {
		return args;
	}
}
