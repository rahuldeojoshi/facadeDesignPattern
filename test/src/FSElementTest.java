/**
 * 
 */
package src;

import static org.junit.Assert.*;

import java.util.Date;

import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;
import facade.*;
/**
 * @author RDJ
 *
 */
public class FSElementTest {

	/**
	 * Test method for {@link facade.FSElement#getParent()}.
	 */
	@Test
	public void testGetParent() {
		// create element with everything null except Directory parameter
		FSElement test = new FSElement(FileSystem.getRootDir(), null, null, 0);

		// actual and expected
		Directory actualParent = FileSystem.getRootDir();
		Directory expectedParent = test.getParent();

		// test
		assertThat(actualParent, is(expectedParent));
	}

	/**
	 * Test method for {@link facade.FSElement#setParent(facade.Directory)}.
	 */
	@Test
	public void testSetParent() {
		Directory dr = new Directory(FileSystem.getRootDir(), null, null, 0);
		FSElement fs = new FSElement(dr, null, null, 0);

		// Contains dr
		Directory actual = fs.getParent();

		// Change parent to root
		fs.setParent(FileSystem.getRootDir());
		// Contains updated parent
		Directory expected = fs.getParent();
		Directory newActual = fs.getParent();
		// Test
		assertThat(actual, is(not(expected)));
		assertThat(newActual, is(expected));
	}

	/**
	 * Test method for {@link facade.FSElement#getName()}.
	 */
	@Test
	public void testGetName() {
		// create element with everything null except FSElementName parameter
		FSElement test = new FSElement(null, "system", null, 0);

		// actual and expected
		String actualName = "system";
		String expectedName = test.getName();

		// test
		assertThat(actualName, is(expectedName));
	}

	/**
	 * Test method for {@link facade.FSElement#setName(java.lang.String)}.
	 */
	@Test
	public void testSetName() {
		FSElement fs = new FSElement(null, "CS680", null, 3);

		String actual = fs.getOwner();

		fs.setName("Rahul");
		String expected = fs.getName();
		String newActual = fs.getName();

		/*
		 * "CS680" is not equal to "Rahul"
		 */
		assertThat(actual, is(not(expected)));

		assertThat(newActual, is(expected));
	}

	/**
	 * Test method for {@link facade.FSElement#getOwner()}.
	 */
	@Test
	public void testGetOwner() {
		// create element with everything null except owner parameter
		FSElement test = new FSElement(null, null, "Rahul", 0);

		// actual and expected
		String actualOwner = "Rahul";
		String expectedOwner = test.getOwner();

		// test
		assertThat(actualOwner, is(expectedOwner));
	}

	/**
	 * Test method for {@link facade.FSElement#setOwner(java.lang.String)}.
	 */
	@Test
	public void testSetOwner() {
		FSElement fs = new FSElement(null, null, "CS680", 3);

		String actual = fs.getOwner();

		fs.setOwner("Rahul");
		String expected = fs.getOwner();
		String newActual = fs.getOwner();

		/*
		 * "CS680" is not equal to "Rahul"
		 */
		assertThat(actual, is(not(expected)));

		assertThat(newActual, is(expected));
	}

	/**
	 * Test method for {@link facade.FSElement#getSize()}.
	 */
	@Test
	public void testGetSize() {
		// create element with everything null except Size parameter
		FSElement test = new FSElement(null, null, null, 1);

		// actual and expected
		int actualSize = test.getSize();
		int expectedSize = 1;

		// test
		assertThat(actualSize, is(expectedSize));
	}

	/**
	 * Test method for {@link facade.FSElement#setSize(int)}.
	 */
	@Test
	public void testSetSize() {
		// sets size to 3
		FSElement fs = new FSElement(null, null, null, 3);

		// has size as 3
		int actualSize = fs.getSize();

		// update size to 4
		fs.setSize(4);

		// has size as 4
		int expected = fs.getSize();
		int newActual = fs.getSize();

		// Test
		// 3 is not = 4
		assertThat(actualSize, is(not(expected)));
		// 3=4
		assertThat(newActual, is(expected));
	}

	/**
	 * Test method for {@link facade.FSElement#getCreated()}.
	 */
	@Test
	public void testGetCreated() {
		// create element with everything null except createdDate parameter
		FSElement test = new FSElement(null, null, null, 0);

		// actual and expected
		Date actualDate = test.getCreated();
		Date expectedDate = new Date();

		// test
		assertThat(actualDate, is(expectedDate));
	}

	/**
	 * Test method for {@link facade.FSElement#getLastModified()}.
	 */
	@Test
	public void testGetLastModified() {
		// create element with everything null except ModifiedDate parameter
		FSElement test = new FSElement(null, null, null, 0);

		// actual and expected
		Date actualDate = test.getLastModified();
		Date expectedDate = new Date();

		// test
		assertThat(actualDate, is(expectedDate));
	}

	/**
	 * Test method for {@link facade.FSElement#isLeaf()}.
	 */
	@Test
	public void testIsLeaf() {
		// create element with everything null except isLeaf parameter
		Directory test = new Directory(null, null, null, 0);

		// parent for file cannot be null
		File testFile = new File(null, null, null, 0);

		// actual and expected
		boolean actualResultDir = test.isLeaf();
		boolean expectedResultDir = false;

		// actual and expected
		boolean actualResult = testFile.isLeaf();
		boolean expectedResult = true;

		// test
		assertThat(actualResultDir, is(expectedResultDir));
		assertThat(actualResult, is(expectedResult));
	}

	/**
	 * Test method for {@link facade.FSElement#setLeaf(boolean)}.
	 */
	@Test
	public void testSetLeaf() {
		// create element with everything null except isLeaf parameter
		Directory test = new Directory(null, null, null, 0);

		// parent for file cannot be null
		File testFile = new File(null, null, null, 0);

		// actual and expected
		boolean actualResultDir = test.isLeaf();
		boolean expectedResultDir = false;

		/*
		 ****
		 * Only for test case, should be set once when calling respective
		 * constructor (Directory or File)
		 **** 
		 */
		test.setLeaf(true);
		boolean newActualDir = test.isLeaf();
		boolean newExpectedDir = true;

		// actual and expected
		boolean actualResult = testFile.isLeaf();
		boolean expectedResult = true;

		/*
		 ****
		 * Only for test case, should be set once when calling respective
		 * constructor (Directory or File)
		 **** 
		 */
		testFile.setLeaf(false);
		boolean newActual = testFile.isLeaf();
		boolean newExpected = false;
		// test
		assertThat(actualResultDir, is(expectedResultDir));
		assertThat(actualResult, is(expectedResult));
		// not condition, after updating
		assertThat(newActual, is(newExpected));
		assertThat(newActualDir, is(newExpectedDir));
	}

	/**
	 * Test method for
	 * {@link facade.FSElement#FSElement(facade.Directory, java.lang.String, java.lang.String, int)}
	 * .
	 */
	@Test
	public void testFSElement() {
		// create FSElement to test
		FSElement test = new FSElement(FileSystem.getRootDir(), "system", "Rahul", 0);

		/*
		 * expected and actual results
		 */
		Directory actualParent = FileSystem.getRootDir();
		String actualName = "system";
		String actualOwner = "Rahul";

		int actualSize = 0;
		boolean actualIsLeaf = false;

		Directory expectedParent = test.getParent();
		String expectedName = test.getName();
		String expectedOwner = test.getOwner();
		int expectedSize = test.getSize();
		boolean expectedIsLeaf = test.isLeaf();

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
	 * Test method for {@link facade.FSElement#getDiskUtil()}.
	 */
	@Test
	public void testGetDiskUtil() {
		// create FSElement to test
		FSElement test = new FSElement(FileSystem.getRootDir(), "system", "Rahul", 0);

		int actualSize = test.getDiskUtil();
		int expectedSize = test.getSize();

		assertThat(actualSize, is(expectedSize));
	}

}
