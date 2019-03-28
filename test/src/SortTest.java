package src;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;

import command.Sort;
import facade.*;

/**
 * @author RDJ
 *
 */
public class SortTest {

	/**
	 * Test method for {@link command.Sort#execute()}.
	 */
	@SuppressWarnings("unused")
	@Test
	public void testExecute() {
		// sort the children in current dir alphabetically

		// first we create some random FSElements that will be then sorted
		Directory directory = new Directory(FileSystem.getRootDir(), "dir", "Rahul", 0);

		// FSElements under the dir created above
		File file = new File(directory, "file", "Rahul", 12);
		File aFile = new File(directory, "aFile", "Rahul", 24);
		Link link = new Link(directory, "link", "Rahul", 0);
		Directory XyzDir = new Directory(directory, "XyzDir", "Rahul", 0);
		// Directory home = new Directory (directory, "dir", "asd", 0);
		// Directory xyz = new Directory (directory, "XyzDir", "asd", 0);

		// setCurrent to directory whose children are to be sorted
		FileSystem.setCurrent(directory);

		// sort
		Sort sort = new Sort();

		// for capturing output
		OutputStream outContent = new ByteArrayOutputStream();
		PrintStream stream = new PrintStream(outContent);
		System.setOut(stream);

		// execute
		sort.execute();
		// test
		assertThat("aFile\nfile\nlink\nXyzDir\n", is(outContent.toString()));

	}

}
