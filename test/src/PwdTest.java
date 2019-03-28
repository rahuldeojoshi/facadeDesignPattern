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
import facade.*;
import command.Pwd;

/**
 * @author RDJ
 *
 */
public class PwdTest {

	/**
	 * Test method for {@link command.Pwd#Pwd(java.lang.String[])}.
	 */
	@Test
	public void testPwd() {
		// constructor sets args passed and the directory to curent working
		// directory

		String[] args = new String[] { "pwd" };

		// call constructor
		Pwd pwd = new Pwd(args);

		// expected and actual
		String[] expectedArgs = args;
		String[] actualArgs = pwd.getArgs();

		Directory expected = FileSystem.getCurrent();
		Directory actual = pwd.getDir();

		// tests
		assertThat(actual, is(expected));
		assertThat(actualArgs, is(expectedArgs));
	}

	/**
	 * Test method for {@link command.Pwd#execute()}.
	 */
	@Test
	public void testExecute() {
		// prints the address of the current directory, so we create create some
		// directories for testing
		Directory home = new Directory(FileSystem.getRootDir(), "home", null, 0);
		Directory newDir = new Directory(home, "newDir", null, 0);

		FileSystem.setCurrent(newDir);

		String[] args = new String[] { "pwd" };

		// call constructor
		Pwd pwd = new Pwd(args);

		// for capturing output
		OutputStream outContent = new ByteArrayOutputStream();
		PrintStream stream = new PrintStream(outContent);
		System.setOut(stream);

		// call execute
		pwd.execute();

		assertThat("/root/home/newDir\n", is(outContent.toString()));

	}

	/**
	 * Test method for {@link command.Pwd#getParent(facade.Directory)}.
	 */
	@Test
	public void testGetParent() {
		// fills the arrayList parent with its ancestors
		Directory home = new Directory(FileSystem.getRootDir(), "home", null, 0);
		Directory newDir = new Directory(home, "newDir", null, 0);

		FileSystem.setCurrent(newDir);

		String[] args = new String[] { "pwd" };

		// call constructor
		Pwd pwd = new Pwd(args);

		ArrayList<FSElement> expected = new ArrayList<FSElement>();
		expected.add(newDir);
		expected.add(home);
		expected.add(FileSystem.getRootDir());

		ArrayList<FSElement> actual = pwd.getParents();

		// test
		assertEquals(actual, expected);
	}

	/**
	 * Test method for {@link command.Pwd#getParents()}.
	 */
	@Test
	public void testGetParents() {
		Directory newDir = new Directory(FileSystem.getRootDir(), "newDir", null, 0);

		FileSystem.setCurrent(newDir);

		String[] args = new String[] { "pwd" };

		// call constructor
		Pwd pwd = new Pwd(args);

		ArrayList<FSElement> expected = new ArrayList<FSElement>();
		expected.add(newDir);
		expected.add(FileSystem.getRootDir());

		ArrayList<FSElement> actual = pwd.getParents();

		// test
		assertEquals(actual, expected);
	}

	/**
	 * Test method for {@link command.Pwd#getDir()}.
	 */
	@Test
	public void testGetDir() {
		// create an object of the class and it will set the dir field
		Pwd pwd = new Pwd(new String[] { "pwd" });

		// expected and actual
		Directory expected = FileSystem.getCurrent();
		Directory actual = pwd.getDir();

		// tests
		assertThat(actual, is(expected));
	}

	/**
	 * Test method for {@link command.Pwd#getArgs()}.
	 */
	@Test
	public void testGetArgs() {
		// constructor sets args passed and the directory to curent working
		// directory

		String[] args = new String[] { "pwd" };

		// call constructor
		Pwd pwd = new Pwd(args);

		// expected and actual
		String[] expectedArgs = args;
		String[] actualArgs = pwd.getArgs();

		// test
		assertThat(actualArgs, is(expectedArgs));
	}

}
