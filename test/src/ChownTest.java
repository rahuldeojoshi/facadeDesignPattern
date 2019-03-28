/**
 * 
 */
package src;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import command.Chown;
import facade.*;

/**
 * @author RDJ
 *
 */
public class ChownTest {

	/**
	 * Test method for {@link command.Chown#Chown(java.lang.String[])}.
	 */
	@Test
	public void testChown() {
		/*
		 * the constructor sets the arguments passed as String
		 */

		// Arguments to pass in the class
		String[] expectedArgs = new String[] { "chown", "Rahul", "home" };

		// create an object by calling constructor of class with some arguments
		Chown chown = new Chown(expectedArgs);

		// actual args by getArgs();
		String[] actualArgs = chown.getArgs();

		assertThat(actualArgs, is(expectedArgs));
	}

	/**
	 * Test method for {@link command.Chown#execute()}.
	 */
	@Test
	public void testExecuteFile() {
		/*
		 * This case we test simple "cd"
		 */

		Directory home = new Directory(FileSystem.getRootDir(), "home", "CS680", 0);
		File fileA = new File(home, "FileA", "XYZ", 10);

		Chown chownFile = new Chown(new String[] { "chown", "Rahul", "FileA" });
		// it should change the owner of FileA from XYZ to Rahul
		chownFile.execute();

		String expectedFileON = fileA.getOwner();
		String actualFileON = chownFile.getOwner();

		// test for file owner change
		assertThat(actualFileON, is(expectedFileON));

	}

	/**
	 * Test method for {@link command.Chown#execute()}.
	 */
	@Test
	public void testExecuteLink() {

		// create a parent dir
		Directory home = new Directory(FileSystem.getRootDir(), "home", "CS680", 0);

		// create a link to test
		Link link = new Link(home, "LinkA", "XYZ", 0);

		Chown chownLink = new Chown(new String[] { "chown", "Rahul", "LinkA" });
		// it should change the owner of LinkA from XYZ to Rahul
		chownLink.execute();

		String expectedLinkON = link.getOwner();
		String actualLinkON = chownLink.getOwner();

		// test for link owner change
		assertThat(actualLinkON, is(expectedLinkON));
	}

	/**
	 * Test method for {@link command.Chown#execute()}.
	 */
	@Test
	public void testExecuteDir() {

		// create a parent dir
		Directory home = new Directory(FileSystem.getRootDir(), "home", "CS680", 0);

		// create a dir 
		Directory dir = new Directory(home, "dir", "CS", 0);
		Chown chownDir = new Chown(new String[] { "chown", "Rahul", "dir" });
		// it should change the owner of dir from XYZ to Rahul
		chownDir.execute();

		String expectedDirON = dir.getOwner();
		String actualDirON = chownDir.getOwner();

		// test for dir owner change
		assertThat(actualDirON, is(expectedDirON));
	}

}
