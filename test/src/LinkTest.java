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
public class LinkTest {

	/**
	 * Test method for {@link facade.Link#getSize()}.
	 */
	@Test
	public void testGetSize() {
		/*
		 * create directory that is the target for link with some size ** Only
		 * for testing else Directory has no size, Files in it do **
		 */
		Directory dir = new Directory(FileSystem.getRootDir(), null, null, 0);
		// create a parent directory for link
		Directory linkParent = new Directory(dir, null, null, 3);
		// create a File for testing size
		File file = new File(dir, "testCase", "Rahul", 7);
		// create the link with parent linkParent; default size is 0
		Link link = new Link(linkParent, "x", null, 0);

		// set Target
		link.setTarget(file);

		// actual and expected
		int actualSize = link.getSize() + file.getSize();
		// get the target directory size as expected that is double since it
		// also adds File size
		int expectedSize = dir.getSize();

		// Test
		assertThat(actualSize, is(expectedSize));
	}

	/**
	 * Test method for {@link facade.Link#accept(visit.FSVisitor)}.
	 */
	@Test
	public void testAccept() {
		/*
		 * This method is basically suppose to set object of current FSElement
		 * to FSVisitor And, then FSVisitor can perform some actions
		 * 
		 * In this case, we take into consideration CountingVisitor, so accept()
		 * in file should increment the numLinks by 1 in CountingVisitor class
		 */

		/*
		 * create directory that is the target for link with some size ** Only
		 * for testing else Directory has no size, Files in it do **
		 */
		Directory dir = new Directory(FileSystem.getRootDir(), null, null, 23);

		// create a parent directory for link
		Directory linkParent = new Directory(dir, null, null, 3);

		// create the link with parent linkParent; default size is 0
		Link link = new Link(linkParent, "x", null, 0);

		// create a counting visitor
		CountingVisitor visit = new CountingVisitor();

		// call accept
		link.accept(visit);

		int expectedCount = 1;
		int actualCount = visit.getNumLinks();

		/*
		 * The count should be 1
		 */
		assertThat(actualCount, is(expectedCount));
	}

	/**
	 * Test method for {@link facade.Link#getDiskUtil()}.
	 */
	@Test
	public void testGetDiskUtil() {
		/*
		 * create directory that is the target for link with some size ** Only
		 * for testing else Directory has no size, Files in it do **
		 */
		Directory dir = new Directory(FileSystem.getRootDir(), null, null, 0);
		// create a parent directory for link
		Directory linkParent = new Directory(dir, null, null, 0);
		// create the link with parent linkParent; default size is 0
		Link link = new Link(linkParent, "x", null, 0);

		int actualDiskUtil = link.getDiskUtil();
		int expectedDiskUtil = 0;

		assertThat(actualDiskUtil, is(expectedDiskUtil));
	}

	/**
	 * Test method for {@link facade.Link#getTarget()}.
	 */
	@Test
	public void testGetTarget() {
		/*
		 * create directory that is the target for link with some size ** Only
		 * for testing else Directory has no size, Files in it do **
		 */
		Directory dir = new Directory(FileSystem.getRootDir(), null, null, 23);
		// create a parent directory for link
		Directory linkParent = new Directory(dir, null, null, 3);
		// create a File for testing size
		File file = new File(dir, "testCase", "Rahul", 7);
		// create the link with parent linkParent; default size is 0
		Link link = new Link(linkParent, "x", null, 0);

		// set Target
		link.setTarget(file);

		// actual and expected
		Directory actualParent = link.getParent();
		// get the target directory size as expected
		Directory expectedParent = linkParent;

		// Test
		assertThat(actualParent, is(expectedParent));
	}

	/**
	 * Test method for {@link facade.Link#setTarget(facade.FSElement)}.
	 */
	@Test
	public void testSetTarget() {
		/*
		 * create directory that is the target for link with some size ** Only
		 * for testing else Directory has no size, Files in it do **
		 */
		Directory dir = new Directory(FileSystem.getRootDir(), null, null, 23);
		// create a parent directory for link
		Directory linkParent = new Directory(dir, null, null, 3);
		// create a File for testing size
		File file = new File(dir, "testCase", "Rahul", 7);
		// create the link with parent linkParent; default size is 0
		Link link = new Link(linkParent, "x", null, 0);

		// set Target
		link.setTarget(file);

		// actual and expected
		FSElement actualTarget = link.getTarget();
		// get the target directory size as expected
		FSElement expectedTarget = file;

		// Test
		assertThat(actualTarget, is(expectedTarget));
	}

	/**
	 * Test method for
	 * {@link facade.Link#Link(facade.Directory, java.lang.String, java.lang.String, int)}
	 * .
	 */
	@Test
	public void testLink() {
		/*
		 * create directory that is the target for link with some size ** Only
		 * for testing else Directory has no size, Files in it do **
		 */
		Directory dir = new Directory(FileSystem.getRootDir(), null, null, 0);
		// create a parent directory for link
		Directory linkParent = new Directory(dir, null, null, 3);
		// create a File for testing size
		File file = new File(dir, "testCase", "Rahul", 7);
		// create the link with parent linkParent; default size is 0
		Link link = new Link(linkParent, "x", "Rahul", 0);

		// set Target
		link.setTarget(file);

		/*
		 * expected and actual results
		 */
		Directory actualParent = link.getParent();
		String actualName = link.getName();
		String actualOwner = link.getOwner();

		int actualSize = link.getSize();
		boolean actualIsLeaf = link.isLeaf();

		Directory expectedParent = linkParent;
		String expectedName = "x";
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
