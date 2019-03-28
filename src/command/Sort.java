package command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import facade.*;

/**
 * @author RDJ
 *
 */
public class Sort implements Command {

	/*
	 * @see command.Command#execute()
	 */
	@Override
	public void execute() {
		// get Current Directory's children
		ArrayList<FSElement> element = FileSystem.getCurrent().getChildren();
		List<String> list = new ArrayList<String>();
		for (FSElement f : element) {
			list.add(f.getName());
		}
		

		// Sorting
		Collections.sort(list, String.CASE_INSENSITIVE_ORDER);

//		// Print the sorted arrayList
//		for (FSElement f : element) {
//			System.out.println(f.getName());
//		}
		 // Print the sorted arrayList
		 for(String f: list){
		 System.out.println(f);
		 }
	}

}
