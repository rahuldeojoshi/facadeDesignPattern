/**
 * 
 */
package command;

import java.util.ArrayList;

import facade.FileSystem;

/**
 * @author RDJ
 *
 */
public class History implements Command {

	private ArrayList<String[]> history;
	
	/**
	 * @return the history
	 */
	public ArrayList<String[]> getHistory() {
		return history;
	}
	
	/**
	 * Constructor
	 */
	public History(){
		history = new ArrayList<String[]>();
	}
	/*
	 * @see command.Command#execute()
	 */
	@Override
	public void execute() {
		try{		
			history = FileSystem.getHistory();
			// Printing History
			System.out.println();
			for(int i = 0; i < history.size(); i++){
				String [] args = history.get(i);
				for (int j = 0; j < args.length; j++){
					System.out.print(args[j] + " ");
				}
			}
			System.out.println();
		}catch(Exception e){
			System.out.println("No history");
		}
	}

}
