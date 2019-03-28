package src;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;

import command.Cd;
import facade.Directory;
import facade.FileSystem;

/**
 * @author RDJ
 *
 */
public class CdTest {

	/**
	 * Test method for {@link command.Cd#Cd(java.lang.String[])}.
	 */
	@Test
	public void testCd() {
		/*
		 * the constructor sets the arguments passed as String
		 */

		// Arguments to pass in the class
		String[] expectedArgs = new String[] { "cd", ".." };

		// create an object by calling constructor of class with some arguments
		Cd cd = new Cd(expectedArgs);

		// actual args by getArgs();
		String[] actualArgs = cd.getArgs();

		assertThat(actualArgs, is(expectedArgs));

	}

	/**
	 * Test method for {@link command.Cd#execute()}.
	 */
	@Test
	public void testExecuteFirst() {
		/*
		 * This case we test simple "cd"
		 */

		Cd cd = new Cd(new String[] { "cd" });

		// it should set the current directory to root
		cd.execute();

		// expected Directory is root
		Directory dir = FileSystem.getRootDir();

		// actual is what we get
		Directory actual = FileSystem.getCurrent();

		// test
		assertThat(actual, is(dir));
	}

	/**
	 * Test method for {@link command.Cd#execute()}.
	 */
	@Test
	public void testExecuteSecond() {
		/*
		 * This case we test "cd .."
		 */

		Cd cd = new Cd(new String[] { "cd", ".." });

		Directory dir = new Directory(FileSystem.getRootDir(), "dir", "Rahul", 0);
		// set FileSystem to some directory
		FileSystem.setCurrent(dir);
		// Current directory's parent before this command
		Directory currentExpected = dir.getParent();

		// it should set the current directory to its parent
		cd.execute();

		// Current Directory should be now currentBeforeParent
		Directory currentActual = FileSystem.getCurrent();

		// test
		assertThat(currentActual, is(currentExpected));

	}

	/**
	 * Test method for {@link command.Cd#execute()}.
	 */
	@Test
	public void testExecuteThird() {
		/*
		 * This case we test "cd <dir name>"
		 */
		Directory home = new Directory(FileSystem.getRootDir(), "home", "Rahul", 0);
		Directory pictures = new Directory(home, "Pictures", "Rahul", 0);

		Cd cd = new Cd(new String[] { "cd", "Pictures" });

		// set FileSystem to some directory
		FileSystem.setCurrent(home);

		// Current directory's parent before this command
		Directory expected = pictures;

		// it should set the current directory to its parent
		cd.execute();

		// Current Directory should be now currentBeforeParent
		Directory actual = FileSystem.getCurrent();

		// test
		assertThat(actual, is(expected));

	}

	/**
	 * Test method for {@link command.Cd#execute()}.
	 */
	@Test
	public void testExecuteFourth() {
		/*
		 * This case we test "null"
		 */

		Cd cd = new Cd(null);

		OutputStream outContent = new ByteArrayOutputStream();
		PrintStream stream = new PrintStream(outContent);
		System.setOut(stream);

		cd.execute();
		// test
		assertThat("Usage: cd || cd <dirName> || cd ..\n", is(outContent.toString()));

	}

}
