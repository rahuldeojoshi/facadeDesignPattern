package src;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.junit.Test;

import facade.*;

/**
 * @author RDJ
 *
 */
public class FileSystemTest {

	/**
	 * Test method for {@link facade.FileSystem#getHistory()}.
	 */
	@Test
	public void testGetHistory() {
		// sets the args passed when a command class is called to execute
		// respective command

		// args. Here, we just check the args ar set as such or not so we do not
		// create any command class object
		String[] args = new String[] { "cd" };

		// setHistory
		FileSystem.setHistory(args);

		String[] expected = args;
		// just for test case since we know it just has one element
		String[] actual = FileSystem.getHistory().get(0);

		// test
		assertThat(actual, is(expected));
	}

	/**
	 * Test method for {@link facade.FileSystem#setHistory(java.lang.String[])}.
	 */
	@Test
	public void testSetHistory() {
		// pass some args, setHistory and check if returns same results, exactly
		// same as the above test
		String[] args = new String[] { "cd" };

		// setHistory
		FileSystem.setHistory(args);

		String[] actual = args;
		// just for test case since we know it just has one element
		String[] expected = FileSystem.getHistory().get(0);

		// test
		assertThat(actual, is(expected));
	}

	/**
	 * Test method for {@link facade.FileSystem#getCurrent()}.
	 */
	@Test
	public void testGetCurrent() {
		// we getCurrent and since we haven't set any current here it should
		// return root
		Directory d = new Directory(FileSystem.getRootDir(), "dir", "Rahul", 0);

		// set Current
		FileSystem.setCurrent(d);

		// actual and expected
		Directory expected = d;
		Directory actual = FileSystem.getCurrent();

		// test
		assertThat(actual, is(expected));
	}

	/**
	 * Test method for {@link facade.FileSystem#setCurrent(facade.Directory)}.
	 */
	@Test
	public void testSetCurrent() {
		// create a directory
		Directory dir = new Directory(FileSystem.getRootDir(), "dir", "Rahul", 0);

		// create another that will be setCurrent
		Directory dir2 = new Directory(FileSystem.getRootDir(), "dir2", "Rahul", 0);

		// actual
		Directory actual = dir;

		// change the current to dir2
		FileSystem.setCurrent(dir2);

		// newActual
		Directory newActual = FileSystem.getCurrent();

		// expected
		Directory expected = dir2;

		// test
		assertThat(actual, is(not(expected)));
		assertThat(newActual, is(expected));
	}

	/**
	 * Test method for
	 * {@link facade.FileSystem#showAllElements(facade.Directory)}.
	 */
	@Test
	public void testShowAllElements() {
		// Create a file system
		FileSystem fs = FileSystem.getFileSystem();
		Directory dir = FileSystem.getRootDir();
		// Create directories as instructed in Slide 11 Lecture 14 CS680
		Directory system = new Directory(dir, "system", "Rahul", 0);
		Directory home = new Directory(dir, "home", "Rahul", 0);
		Directory pictures = new Directory(home, "Pictures", "Rahul", 0);

		// Create Files under above Directories as per Slide 11 Lecture 14
		// CS680, using @SuppressWarnings("unused")
		File a = new File(system, "FileA", "Easy", 20);
		File b = new File(system, "FileB", "But", 11);
		File c = new File(system, "FileC", "Confusing", 10);
		File d = new File(home, "FileD", "Rahul", 17);
		File e = new File(pictures, "FileD", "Rahul", 17);
		File f = new File(pictures, "FileF", "Rahul", 17);
		Link linkX = new Link(home, "x", "Rahul", 0);
		Link linkY = new Link(pictures, "y", "Rahul", 0);
		linkX.setTarget(system);
		linkY.setTarget(e);

		OutputStream outContent = new ByteArrayOutputStream();
		PrintStream stream = new PrintStream(outContent);
		System.setOut(stream);
		// call showAllElements()
		fs.showAllElements(dir);
		// test
		assertThat("root-->system\nroot-->system-->FileA\nroot-->system-->FileB\nroot-->system-->FileC"
				+ "\nroot-->home\nroot-->home-->Pictures\nhome-->Pictures-->FileD\nhome-->Pictures-->FileF"
				+ "\nhome-->Pictures-->y\nroot-->home-->FileD\nroot-->home-->x\n", is(outContent.toString()));

		int actualSize = a.getSize() + b.getSize() + c.getSize() + d.getSize() + e.getSize() + f.getSize()
				+ linkX.getSize() + linkY.getSize();
		int expectedSize = FileSystem.getRootDir().getSize();

		// test for get Size on root
		assertThat(actualSize, is(expectedSize));

		System.out.flush();
	}

}
