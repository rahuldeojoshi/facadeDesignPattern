package command;


import facade.*;
import visit.FSElementVisit;

/**
 * @author RDJ
 *
 */
public class Ln implements Command {

	private String args[];

	/**
	 * @param link
	 * @param element
	 * @param dir
	 */
	public Ln(String args[]) {
		this.args = args;
	}

	/*
	 * @see command.Command#execute()
	 */
	@Override
	public void execute() {
		// add to History
		
		try {
			if (args.length > 3 || args.length < 3 || !args[0].equals("ln") || args[1].equals("")
					|| args[2].equals("")) {
				System.out.println("Usage: ln <target file/link/dir> <link name>");
			} else {
				// search with FSElementVisitor
				FSElementVisit visit = new FSElementVisit(args[1]);
				
				// visit the root
				FileSystem.getRootDir().accept(visit);

				// create link if found
				if (visit.getFoundFiles().size() > 0) {
					
					// if the target is a File
					Link link = new Link(FileSystem.getCurrent(), args[2], "Rahul", 0);
					link.setTarget(visit.getFoundFiles().get(0));
					
				}else if(visit.getFoundDirs().size() > 0){
					
					// if the target is a Directory
					Link link = new Link(FileSystem.getCurrent(), args[2], "Rahul", 0);
					link.setTarget(visit.getFoundDirs().get(0));
					
				}else if(visit.getFoundLinks().size() > 0){
					
					//if the target is a Link
					Link link = new Link(FileSystem.getCurrent(), args[2], "Rahul", 0);
					link.setTarget(visit.getFoundLinks().get(0));
				}else{
					System.out.println("Target element not found");
				}
			}
		} catch (Exception e) {
			System.out.println("Usage: ln <target file/link/dir> <link name> Exception");
		}
	}

	/**
	 * @return the args
	 */
	public String[] getArgs() {
		return args;
	}

}
