/**
 * 
 */
package src;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;

import org.junit.Test;

import facade.*;
import visit.CountingVisitor;

/**
 * @author RDJ
 *
 */
public class DirectoryTest {

	/**
	 * Test method for {@link facade.Directory#getSize()}.
	 */
	@Test
	public void testGetSize() {
		// created directory for testing
		Directory dir = new Directory(FileSystem.getRootDir(), null, null, 0);

		// creating some files under the directory declared above
		File file1 = new File(dir, "file1", null, 18);
		File file2 = new File(dir, "file2", null, 9);
		File file3 = new File(dir, "file3", null, 8);
		File file4 = new File(dir, "file4", null, 23);
		File file5 = new File(dir, "file5", null, 60);
		File file6 = new File(dir, "file6", null, 43);

		// test
		int actualSize = file1.getSize() + file2.getSize() + file3.getSize() + file4.getSize() + file5.getSize()
				+ file6.getSize();
		int expectedSize = dir.getSize();

		// test
		assertThat(actualSize, is(expectedSize));
	}

	/**
	 * Test method for {@link facade.Directory#accept(visit.FSVisitor)}.
	 */
	@Test
	public void testAccept() {
		// creating a directory for testing
		Directory dir = new Directory(FileSystem.getRootDir(), "testDirectory", "Rahul", 0);

		// create a counting visitor
		CountingVisitor visit = new CountingVisitor();

		// call accept
		dir.accept(visit);

		int expectedCount = 1;
		int actualCount = visit.getNumDirs();

		/*
		 * The count should be 1
		 */
		assertThat(actualCount, is(expectedCount));
	}

	/**
	 * Test method for {@link facade.Directory#getDiskUtil()}.
	 */
	@Test
	public void testGetDiskUtil() {
		// creating a directory for testing
		Directory dir = new Directory(FileSystem.getRootDir(), "testDirectory", "Rahul", 0);

		int actualSize = dir.getDiskUtil();
		int expectedSize = dir.getSize();

		assertThat(actualSize, is(expectedSize));
	}

	/**
	 * Test method for
	 * {@link facade.Directory#Directory(facade.Directory, java.lang.String, java.lang.String, int)}
	 * .
	 */
	@Test
	public void testDirectory() {
		// creating a directory for testing
		Directory dir = new Directory(FileSystem.getRootDir(), "testDirectory", "Rahul", 0);

		/*
		 * expected and actual results
		 */
		// actual params
		Directory actualParent = dir.getParent();
		String actualName = dir.getName();
		String actualOwner = dir.getOwner();
		int actualSize = dir.getSize();
		boolean actualIsLeaf = dir.isLeaf();

		// expected params
		Directory expectedParent = FileSystem.getRootDir();
		String expectedName = "testDirectory";
		String expectedOwner = "Rahul";
		int expectedSize = 0;
		boolean expectedIsLeaf = false;

		/**
		 * check each field, since calling constructor sets all the above fields
		 */
		assertThat(actualParent, is(expectedParent));
		assertThat(actualName, is(expectedName));
		assertThat(actualOwner, is(expectedOwner));

		assertThat(actualSize, is(expectedSize));
		assertThat(actualIsLeaf, is(expectedIsLeaf));
	}

	/**
	 * Test method for {@link facade.Directory#getChildren()}.
	 */
	@Test
	public void testGetChildren() {
		// created Directory for testing
		Directory dir = new Directory(FileSystem.getRootDir(), null, null, 0);

		// create some children of above dir
		Directory dir1 = new Directory(dir, null, null, 0);
		Directory dir2 = new Directory(dir, null, null, 0);
		Directory dir3 = new Directory(dir, null, null, 0);

		// expected will be the children created above
		ArrayList<Directory> expectedChildren = new ArrayList<Directory>();
		expectedChildren.add(dir1);
		expectedChildren.add(dir2);
		expectedChildren.add(dir3);

		// actual is what we get from getChildren()
		ArrayList<FSElement> actualChildren = dir.getChildren();

		// test if getChildren is working as it is suppose to...
		assertThat(actualChildren, is(expectedChildren));
	}

	/**
	 * Test method for {@link facade.Directory#appendChild(facade.FSElement)}.
	 */
	@Test
	public void testAppendChild() {
		Directory dir = new Directory(FileSystem.getRootDir(), null, null, 0);

		Directory dir1 = new Directory(dir, null, null, 0);
		Directory dir2 = new Directory(dir, null, null, 0);
		Directory dir3 = new Directory(dir, null, null, 0);

		ArrayList<Directory> expectedChildren = new ArrayList<Directory>();
		expectedChildren.add(dir1);
		expectedChildren.add(dir2);
		expectedChildren.add(dir3);

		ArrayList<FSElement> actualChildren = dir.getChildren();

		assertThat(actualChildren, is(expectedChildren));

		// append new dir
		Directory dir4 = new Directory(dir, null, null, 0);
		expectedChildren.add(dir4);

		ArrayList<FSElement> actualChildUpdated = dir.getChildren();

		assertThat(actualChildUpdated, is(expectedChildren));
	}

	/**
	 * Test method for {@link facade.Directory#removeChild(facade.FSElement)}.
	 */
	@Test
	public void testRemoveChild() {
		// created Directory for testing
		Directory dir = new Directory(FileSystem.getRootDir(), null, null, 0);

		// create some children of above dir
		Directory dir1 = new Directory(dir, null, null, 0);
		Directory dir2 = new Directory(dir, null, null, 0);
		Directory dir3 = new Directory(dir, null, null, 0);

		// expected will be the children created above
		ArrayList<Directory> addChildren = new ArrayList<Directory>();
		addChildren.add(dir1);
		addChildren.add(dir2);
		addChildren.add(dir3);

		dir.removeChild(dir3);

		// expected
		ArrayList<Directory> expected = addChildren;
		expected.remove(2); // since dir3 that was removed was at 3rd index in
							// arrayList

		// actual is what we get from getChildren()
		ArrayList<FSElement> actualChildren = dir.getChildren();
		
		// test
		assertEquals(actualChildren, expected);
	}

}
