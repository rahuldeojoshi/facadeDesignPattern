package command;

import java.util.*;

import facade.*;
import visit.DirSearchVisitor;

/**
 * @author RDJ
 *
 */
public class Dir implements Command {

	private ArrayList<FSElement> information = new ArrayList<FSElement>();;
	private Directory dir;
	private String[] args;

	/**
	 * @return the information
	 */
	public ArrayList<FSElement> getInformation() {
		return information;
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
	
	/**
	 * @param args
	 */
	public Dir(String[] args) {
		this.args = args;
	}

	/*
	 * @see command.Command#execute()
	 */
	@Override
	public void execute() {
		// add to History
		// Simple DIR command
		try {
			FileSystem.setHistory(args);
			
			if (args.length == 1) {

				this.dir = FileSystem.getCurrent();

				information = dir.getChildren();

				System.out.print("\nDirectory Name: " + dir.getName() + " Directory owner: " + dir.getOwner()
						+ " Size: " + dir.getSize() + "\n");

				for (FSElement f : information) {
					if (f instanceof File) {
						System.out.println("\nFile Name: " + f.getName() + " File owner: " + f.getOwner() + " Size: "
								+ f.getSize() + "\n");
					} else if (f instanceof Link) {
						System.out.print("\nLink Name: " + f.getName() + " Link owner: " + f.getOwner() + " Size: "
								+ f.getSize() + "\n");
					} else {
						System.out.println("\nDirectory Name: " + f.getName() + " Directory owner: " + f.getOwner()
								+ " Size: " + f.getSize() + "\n");
					}
				}

			}
			
			/**
			 * command " dir .. "
			 */
			else if (args.length > 1 && args[1].equals("..")) {

				this.dir = FileSystem.getCurrent().getParent();

				information = dir.getChildren();

				System.out.print("\nParent Directory Name: " + dir.getName() + " Directory owner: " + dir.getOwner()
						+ " Size: " + dir.getSize() + "\n");

				for (FSElement f : information) {
					if (f instanceof File) {
						System.out.println("\nFile Name: " + f.getName() + " File owner: " + f.getOwner() + " Size: "
								+ f.getSize() + "\n");
					} else if (f instanceof Link) {
						System.out.print("\nLink Name: " + f.getName() + " Link owner: " + f.getOwner() + " Size: "
								+ f.getSize() + "\n");
					} else {
						System.out.println("\nDirectory Name: " + f.getName() + " Directory owner: " + f.getOwner()
								+ " Size: " + f.getSize() + "\n");
					}
				}
			}

			/**
			 * Command 'dir <dir/file name>'
			 */
			else if (args.length > 1 && !args[1].equals("") && !args[1].equals("..")) {

				DirSearchVisitor visit = new DirSearchVisitor(args[1]);

				FileSystem.getCurrent().getParent().accept(visit);

				if (visit.getFoundDirs().size() > 0) {

					this.dir = visit.getFoundDirs().get(0);

					information = dir.getChildren();

					// System.out.print("\nDirectory Name: " + dir.getName() + "
					// Directory owner: " + dir.getOwner()
					// + " Size: " + dir.getSize() + "\n");

					for (FSElement f : information) {
						if (f instanceof File) {
							System.out.println("\nFile Name: " + f.getName() + " File owner: " + f.getOwner()
									+ " Size: " + f.getSize() + "\n");
						} else if (f instanceof Link) {
							System.out.print("\nLink Name: " + f.getName() + " Link owner: " + f.getOwner() + " Size: "
									+ f.getSize() + "\n");
						} else {
							System.out.println("\nDirectory Name: " + f.getName() + " Directory owner: " + f.getOwner()
									+ " Size: " + f.getSize() + "\n");
						}
					}
				} else {
					System.out.println("\nUsage : dir || dir .. || dir <dir/file name>");
				}
			}

		} catch (Exception e) {
			System.out.println("\nError!!! Usage : dir || dir .. || dir <dir/file name>");
		}
	}

	
}
