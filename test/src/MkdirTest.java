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

import command.Mkdir;
import facade.*;

/**
 * @author RDJ
 *
 */
public class MkdirTest {

	/**
	 * Test method for {@link command.Mkdir#Mkdir(java.lang.String[])}.
	 */
	@Test
	public void testMkdir() {
		/*
		 * the constructor sets the arguments passed as String
		 */
		// args
		String[] args = new String[] { "mkdir", "home" };

		// call the constructor by creating an object
		Mkdir mkdir = new Mkdir(args);

		// expected and actual
		String[] expected = args;
		String[] actual = mkdir.getArgs();

		// test
		assertThat(actual, is(expected));
	}

	/**
	 * Test method for {@link command.Mkdir#execute()}.
	 */
	@Test
	public void testExecute() {
		/*
		 * the constructor sets the arguments passed as String
		 */
		// args
		String[] args = new String[] { "mkdir", "home" };

		// call the constructor by creating an object
		Mkdir mkdir = new Mkdir(args);

		// execute; it will create a directory name 'home' under root since root
		// is the current directory
		mkdir.execute();

		Directory expected = null;
		Directory actual = mkdir.getDir();

		// search in the children of root directory since it is the current
		// directory for 'home'
		for (FSElement f : FileSystem.getRootDir().getChildren()) {
			// runs only if a Link is found with the name we are looking for
			if (f instanceof Directory) {
				expected = (Directory) f;
			}
		}

		// test
		assertThat(actual, is(expected));

		// if the constructor is called by passing null in the parameters, it
		// displays a usage message
		Mkdir mkdir1 = new Mkdir(null);

		// for capturing system output and testing
		OutputStream outContent = new ByteArrayOutputStream();
		PrintStream stream = new PrintStream(outContent);
		System.setOut(stream);

		// call execute
		mkdir1.execute();

		// test
		assertThat("'Usage: mkdir <dir name>', arguments cannot be null.\n", is(outContent.toString()));
	}

	/**
	 * Test method for {@link command.Mkdir#getDir()}.
	 */
	@Test
	public void testGetDir() {

		// args
		String[] args = new String[] { "mkdir", "home" };

		// call the constructor by creating an object
		Mkdir mkdir = new Mkdir(args);

		// execute; it will add a new directory under current directory of
		// FileSystem namely 'home'
		mkdir.execute();

		Directory expected = null;
		Directory actual = mkdir.getDir();

		// search in the children of root directory since it is the current
		// directory for 'home'
		for (FSElement f : FileSystem.getRootDir().getChildren()) {
			// runs only if a Link is found with the name we are looking for
			if (f instanceof Directory) {
				expected = (Directory) f;
			}
		}

		// test
		assertThat(actual, is(expected));

	}

	/**
	 * Test method for {@link command.Mkdir#getArgs()}.
	 */
	@Test
	public void testGetArgs() {
		// args
		String[] args = new String[] { "mkdir", "home" };

		// call the constructor by creating an object
		Mkdir mkdir = new Mkdir(args);

		// expected and actual
		String[] expected = args;
		String[] actual = mkdir.getArgs();

		// test
		assertThat(actual, is(expected));
	}

}
