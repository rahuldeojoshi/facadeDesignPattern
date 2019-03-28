/**
 * 
 */
package src;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import facade.*;
import visit.CountingVisitor;

/**
 * @author RDJ
 *
 */
public class FileTest {

	/**
	 * Test method for {@link facade.File#accept(visit.FSVisitor)}.
	 */
	@Test
	public void testAccept() {
		/*
		 * This method is basically suppose to set object of current FSElement
		 * to FSVisitor And, then FSVisitor can perform some actions
		 * 
		 * In this case, we take into consideration CountingVisitor, so accept()
		 * in file should increment the numFiles by 1 in CountingVisitor class
		 */

		File file = new File(FileSystem.getRootDir(), "testCase", "Rahul", 7);

		// create a counting visitor
		CountingVisitor visit = new CountingVisitor();

		// call accept
		file.accept(visit);

		int expectedCount = 1;
		int actualCount = visit.getNumFiles();

		/*
		 * The count should be 1
		 */
		assertThat(actualCount, is(expectedCount));
	}

	/**
	 * Test method for {@link facade.File#getDiskUtil()}.
	 */
	@Test
	public void testGetDiskUtil() {
		File file = new File(FileSystem.getRootDir(), "testCase", "Rahul", 7);

		int actualDiskUtil = file.getDiskUtil();
		int expectedDiskUtil = file.getSize();

		assertThat(actualDiskUtil, is(expectedDiskUtil));
	}

	/**
	 * Test method for
	 * {@link facade.File#File(facade.Directory, java.lang.String, java.lang.String, int)}
	 * .
	 */
	@Test
	public void testFile() {
		File file = new File(FileSystem.getRootDir(), "testCase", "Rahul", 7);

		/*
		 * expected and actual results
		 */
		Directory actualParent = file.getParent();
		String actualName = file.getName();
		String actualOwner = file.getOwner();

		int actualSize = file.getSize();
		boolean actualIsLeaf = file.isLeaf();

		Directory expectedParent = FileSystem.getRootDir();
		String expectedName = "testCase";
		String expectedOwner = "Rahul";
		int expectedSize = 7;
		boolean expectedIsLeaf = true;

		/**
		 * check each field, since calling constructor sets all the above fields
		 */
		assertThat(actualParent, is(expectedParent));
		assertThat(actualName, is(expectedName));
		assertThat(actualOwner, is(expectedOwner));
		assertThat(actualSize, is(expectedSize));
		assertThat(actualIsLeaf, is(expectedIsLeaf));
	}

}
