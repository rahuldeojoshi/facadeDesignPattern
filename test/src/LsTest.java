/**
 * 
 */
package src;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import command.Ls;
import facade.*;

/**
 * @author RDJ
 *
 */
public class LsTest {

	/**
	 * Test method for {@link command.Ls#Ls(java.lang.String[])}.
	 */
	@Test
	public void testLs() {
		/*
		 * The constructor of this class sets children ArrayList and the args
		 */

		// Create constructor with argument "ls"
		Ls ls = new Ls(new String[] { "ls" });

		// args
		String[] expectedArgs = new String[] { "ls" };
		String[] actualArgs = ls.getArgs();

		// children arrayList
		ArrayList<FSElement> expectedArrayList = FileSystem.getCurrent().getChildren();
		ArrayList<FSElement> actualArrayList = ls.getChildren();

		// Test
		assertThat(actualArgs, is(expectedArgs));
		assertThat(actualArrayList, is(expectedArrayList));
	}

	/**
	 * Test method for {@link command.Ls#execute()}.
	 */
	@SuppressWarnings("unused")
	@Test
	public void testExecute() {

		// create a directory add some children and execute ls on that
		Directory home = new Directory(FileSystem.getRootDir(), "home", "Rahul", 0);
		
		// adding some children to above dir
		File a = new File (home, "a", "Rahul", 10);
		File b = new File (home, "b", "Rahul", 13);
		File c = new File (home, "c", "Rahul", 11);

		FileSystem.setCurrent(home);
		
		// Create constructor with argument "ls"
		Ls ls = new Ls(new String[] { "ls" });
						
		OutputStream outContent = new ByteArrayOutputStream();
		PrintStream stream = new PrintStream(outContent);
		System.setOut(stream);
		
		ls.execute();

		assertThat("a   b   c   \n", is(outContent.toString()));
	}

	/**
	 * Test method for {@link command.Ls#getArgs()}.
	 */
	@Test
	public void testGetArgs() {
		
		// Create args to pass 
		String [] expectedArgs = new String[]{"ls"};
		
		// Create the constructor 
		Ls ls = new Ls(expectedArgs);
		
		// call getArgs(); to check if it returns what is expected
		String [] actualArgs = ls.getArgs();
		
		// test
		assertThat(actualArgs, is(expectedArgs));
	}

}
