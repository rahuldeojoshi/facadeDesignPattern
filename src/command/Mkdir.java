/**
 * 
 */
package command;

import facade.*;

/**
 * @author RDJ
 *
 */
public class Mkdir implements Command {

	private Directory dir;
	private String[] args;
	
	/*
	 * Constructor accepts 1 arg
	 * the Directory to be added in the current directory 
	 */
	public Mkdir(String[] args){
			this.args = args;	
	}
	
	/* 
	 * @see command.Command#execute()
	 */
	@Override
	public void execute() {
		// May throw NullPointerException
		try{
			FileSystem.setHistory(args);
			this.dir = new Directory(FileSystem.getCurrent(), args[1],"Rahhhhh",0);
			System.out.println("Created dir '" + dir.getName() +"' in "+ FileSystem.getCurrent().getName());
		}catch(Exception e){
			System.out.println("'Usage: mkdir <dir name>', arguments cannot be null."); 
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
