/**
 * 
 */
package src;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.io.*;
import java.util.ArrayList;

import org.junit.Test;

import command.Dir;
import facade.*;
import facade.File;

/**
 * @author RDJ
 *
 */
public class DirTest {

	/**
	 * Test method for {@link command.Dir#Dir(java.lang.String[])}.
	 */
	@Test
	public void testDir() {
		// constructor call sets the String [] arguments passed as params
		String[] expected = new String[] { "dir" };

		// for printing the information of current directory and all its
		// children
		Dir dir = new Dir(expected);

		// expected and actual
		String[] actual = dir.getArgs();

		// test
		assertThat(actual, is(expected));
	}

	/**
	 * Test method for {@link command.Dir#execute()}.
	 */
	@SuppressWarnings("unused")
	@Test
	public void testExecute() {
		// three different commands 'dir' , 'dir ..', 'dir someDir'
		String[] args = new String[] { "dir" };

		Dir dir = new Dir(args);

		// create some files/dirs
		Directory directory = new Directory(FileSystem.getRootDir(), "dir", "Rahul", 0);
		File file = new File(directory, "file", "Rahul", 12);
		Link link = new Link(directory, "link", "Rahul", 0);
		FileSystem.setCurrent(directory);

		// for testing the output
		OutputStream outContent = new ByteArrayOutputStream();
		PrintStream stream = new PrintStream(outContent);
		System.setOut(stream);

		// execute command
		dir.execute();

		// test
		assertThat(
				"\nDirectory Name: dir Directory owner: Rahul Size: 12\n\nFile Name: file File owner: Rahul Size: 12\n"
						+ "\n\nLink Name: link Link owner: Rahul Size: 0\n",
				is(outContent.toString()));
	}

	/**
	 * Test method for {@link command.Dir#execute()}.
	 */
	@Test
	public void testExecuteSecond() {
		// three different commands 'dir' , 'dir ..', 'dir someDir'
		String[] args = new String[] { "dir", ".." };

		Dir dir = new Dir(args);

		// create some files/dirs
		Directory directory = new Directory(FileSystem.getRootDir(), "dir", "Rahul", 0);

		FileSystem.setCurrent(directory);

		// for testing the output, should print root and its child that is the
		// directory we created above
		OutputStream outContentNew = new ByteArrayOutputStream();
		PrintStream streamNew = new PrintStream(outContentNew);
		System.setOut(streamNew);

		// execute command
		dir.execute();

		// test
		// The output will contain Directory we created in second test case
		assertThat("\nParent Directory Name: root Directory owner: Rahul Size: 12\n"
				+ "\nDirectory Name: dir Directory owner: Rahul Size: 12\n\n"
				+ "\nDirectory Name: dir Directory owner: Rahul Size: 0\n\n", is(outContentNew.toString()));
	}

	/**
	 * Test method for getDir()
	 */
	@Test
	public void testGetDir() {
		// getDir() returns the current directory of FileSystem when we call
		// execute function of the class with proper parameters
		// here we expect the root

		Dir dir = new Dir(new String[] { "dir" });

		// call execute
		dir.execute();

		// expected and actual
		Directory expected = FileSystem.getCurrent();
		Directory actual = dir.getDir();

		// test
		assertThat(actual, is(expected));
	}

	/**
	 * Test method for getDir()
	 */
	@Test
	public void testGetArgs() {
		// returns the argument passed in constructor
		String[] args = new String[] { "dir" };
		Dir dir = new Dir(args);

		// expected and actual
		String[] expected = args;
		String[] actual = dir.getArgs();

		// test
		assertThat(actual, is(expected));
	}

	/**
	 * Test method for getDir()
	 */
	@Test
	public void testGetInformation() {
		// returns the arrayList that is filled with children of current
		// directory when execute function is called

		String[] args = new String[] { "dir" };
		Dir dir = new Dir(args);

		// call execute
		dir.execute();
		
		/*
		 * In this case is will return null 
		 */
		ArrayList<FSElement> expected = FileSystem.getCurrent().getChildren();
		ArrayList<FSElement> actual = dir.getInformation();
		
		// test 
		assertThat(actual, is(expected));
	}
}
