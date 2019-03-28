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

import facade.*;
import command.Rmdir;

/**
 * @author RDJ
 *
 */
public class RmdirTest {

	/**
	 * Test method for {@link command.Rmdir#Rmdir(java.lang.String[])}.
	 */
	@Test
	public void testRmdir() {
		/*
		 * the constructor sets the args passed
		 */

		// args
		String[] args = new String[] { "rmdir", "dir" };

		// call constructor
		Rmdir rmdir = new Rmdir(args);

		// actual and expected
		String[] expected = args;
		String[] actual = rmdir.getArgs();

		// test
		assertThat(actual, is(expected));
	}

	/**
	 * Test method for {@link command.Rmdir#execute()}.
	 */
	@Test
	public void testExecute() {
		// removes the given directory and displays a message
		// create a dir to delete
		Directory dir = new Directory(FileSystem.getRootDir(), "dir", null, 0);

		// args
		String[] args = new String[] { "rmdir", "dir" };

		// call constructor
		Rmdir rmdir = new Rmdir(args);

		// for capturing system output and testing
		OutputStream outContent = new ByteArrayOutputStream();
		PrintStream stream = new PrintStream(outContent);
		System.setOut(stream);

		// call execute
		rmdir.execute();

		// test
		assertThat("\nRemoved dir: dir\n", is(outContent.toString()));

		// also sets the dir field to parent of removed dir
		Directory expected = FileSystem.getRootDir();
		Directory actual = dir.getParent();

		// test
		assertThat(actual, is(expected));

	}

	/**
	 * Test method for {@link command.Rmdir#getDir()}.
	 */
	@Test
	public void testGetDir() {
		// create a dir to delete
		Directory dir = new Directory(FileSystem.getRootDir(), "dir", null, 0);

		// args
		String[] args = new String[] { "rmdir", "dir" };

		// call constructor
		Rmdir rmdir = new Rmdir(args);
		// call execute
		rmdir.execute();

		// actual and expected
		Directory expected = FileSystem.getRootDir();
		Directory actual = dir.getParent();

		// test
		assertThat(actual, is(expected));
	}

	/**
	 * Test method for {@link command.Rmdir#getArgs()}.
	 */
	@Test
	public void testGetArgs() {
		// args
		String[] args = new String[] { "rmdir", "dir" };

		// call constructor
		Rmdir rmdir = new Rmdir(args);

		// actual and expected
		String[] expected = args;
		String[] actual = rmdir.getArgs();

		// test
		assertThat(actual, is(expected));
	}

}
