/**
 * 
 */
package src;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.Test;

import command.*;
import facade.*;

/**
 * @author RDJ
 *
 */
public class HistoryTest {

	/**
	 * Test method for {@link command.History#History()}.
	 */
	@Test
	public void testHistory() {

		/*
		 * History history = new History();
		 *
		 * ArrayList<String[]> expected = history.getHistory();
		 *
		 * ArrayList<String[]> actual = history.getHistory();
		 */
	}

	/**
	 * Test method for {@link command.History#execute()}.
	 */
	@SuppressWarnings("unused")
	@Test
	public void testExecute() {
		/*
		 * this test is for the output
		 * 
		 * Here, we execute some Command and see if records in history We create
		 * some files and directories and links
		 */
		// create a history object
		History history = new History();

		Directory root = FileSystem.getRootDir();
		Directory system = new Directory(root, "system", "Rahul", 0);
		Directory home = new Directory(root, "home", "Rahul", 0);
		Directory pictures = new Directory(home, "Pictures", "Rahul", 0);

		// some files
		File a = new File(system, "FileA", "Easy", 20);
		File b = new File(system, "FileB", "But", 11);
		File c = new File(system, "FileC", "Confusing", 10);
		File d = new File(home, "FileD", "Rahul", 17);
		File e = new File(pictures, "FileD", "Rahul", 17);
		File f = new File(pictures, "FileF", "Rahul", 17);

		// some links
		Link linkX = new Link(home, "x", "Rahul", 0);
		Link linkY = new Link(pictures, "y", "Rahul", 0);

		// set current to pictures
		FileSystem.setCurrent(pictures);
		// execute some commands
		Cd cd = new Cd(new String[] { "cd", ".." });
		cd.execute();

		Chown chown = new Chown(new String[] { "chown", "RJ", "FileA" });
		chown.execute();

		// Also it prints the commands
		OutputStream outContent = new ByteArrayOutputStream();
		PrintStream stream = new PrintStream(outContent);
		System.setOut(stream);
		
		// execute history
		history.execute();

		ArrayList<String[]> expected = FileSystem.getHistory();
		ArrayList<String[]> actual = history.getHistory();

		// test
		assertThat(actual, is(expected));

		assertThat("\ncd .. chown RJ FileA \n", is(outContent.toString()));
	}

}
