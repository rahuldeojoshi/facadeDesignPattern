/**
 * 
 */
package command;

import facade.Directory;
import facade.FileSystem;
import visit.DirSearchVisitor;

/**
 * @author RDJ
 *
 */
public class Rmdir implements Command {

	private Directory dir;
	private String[] args;

	/*
	 * Class Constructor Accepts name of the directory as an argument finds and
	 * removes the directory in current directory
	 */
	public Rmdir(String[] args) {
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
			DirSearchVisitor visit = new DirSearchVisitor(args[1]);
			Directory dit = FileSystem.getCurrent();
			dit.accept(visit);
			if (visit.getFoundDirs().size() > 0) {
				this.dir = visit.getFoundDirs().get(0).getParent();
				Directory d = visit.getFoundDirs().get(0);
				dir.removeChild(d);
				System.out.println("\nRemoved dir: " + d.getName());
			} else {
				System.out.println("\nDir not found in " + FileSystem.getCurrent().getName());
			}
		} catch (Exception e) {
			System.out.println("\nUsage: rmdir <dir name>");
		}
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
