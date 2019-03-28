/**
 * 
 */
package src;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import command.Ln;
import facade.*;

/**
 * @author RDJ
 *
 */
public class LnTest {

	/**
	 * Test method for {@link command.Ln#Ln(java.lang.String[])}.
	 */
	@Test
	public void testLn() {
		/*
		 * Sets the passed args. So, we create an object by calling constructor
		 * by passing required args
		 */

		// args to pass
		String[] expectedArgs = new String[] { "ln", "home", "link" };

		// call constructor with args
		Ln ln = new Ln(expectedArgs);

		// get args to test
		String[] actualArgs = ln.getArgs();

		// test
		assertThat(actualArgs, is(expectedArgs));
	}

	/**
	 * Test method for {@link command.Ln#execute()}.
	 */
	@SuppressWarnings("unused")
	@Test
	public void testExecute() {
		/*
		 * To test create some FSElements
		 */
		Directory home = new Directory(FileSystem.getRootDir(), "home", "Rahul", 0);

		// adding some children to above dir
		File a = new File(home, "a", "Rahul", 10);
		File b = new File(home, "b", "Rahul", 13);
		File c = new File(home, "c", "Rahul", 11);

		FileSystem.setCurrent(home);

		// args to pass
		String[] args = new String[] { "ln", "a", "linkTest" };

		// call constructor with args
		Ln ln = new Ln(args);

		// execute command
		ln.execute();

		/*
		 * there should be a link create in home directory by the name linkTest
		 * we then search for linkTest within home dir
		 */
		String expectedName = args[2];
		String actualName = "";
		for (FSElement f : home.getChildren()) {
			// runs only if a Link is found with the name we are looking for
			if (f instanceof Link) {
				if (f.getName().contains("linkTest")) {
					actualName = f.getName();
				}
			}
		}

		// test
		assertThat(actualName, is(expectedName));

		// Create a constructor with args null
		String[] nullArgs = null;

		Ln ln1 = new Ln(nullArgs);

		/*
		 * output should be a message as follows Usage: ln <target
		 * file/link/dir> <link name>
		 */

		OutputStream outContent = new ByteArrayOutputStream();
		PrintStream stream = new PrintStream(outContent);
		System.setOut(stream);

		ln1.execute();

		assertThat("Usage: ln <target file/link/dir> <link name> Exception\n", is(outContent.toString()));

	}

	@Test
	public void testExecuteSecond() {
		// Create a constructor with args that contains a file that doesn't
		// exists in the root dir
		// We call it XYZ

		String[] argsNoFSElement = new String[] { "ln", "xyz", "linkTest" };

		Ln ln2 = new Ln(argsNoFSElement);

		/*
		 * output should be a message as follows Usage: ln <target
		 * file/link/dir> <link name>
		 */

		OutputStream outContentNew = new ByteArrayOutputStream();
		PrintStream streamNew = new PrintStream(outContentNew);
		System.setOut(streamNew);

		ln2.execute();

		assertThat("Target element not found\n", is(outContentNew.toString()));
	}

	/**
	 * Test method for {@link command.Ln#getArgs()}.
	 */
	@Test
	public void testGetArgs() {
		// Create a constructor with args null
		String[] args = null;

		Ln ln = new Ln(args);

		String[] expected = args;
		String[] actual = ln.getArgs();

		// test
		assertThat(actual, is(expected));
	}

}
