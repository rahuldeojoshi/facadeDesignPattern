package sorting;

import java.util.Comparator;

import facade.FSElement;

/**
 * @author RDJ
 *
 */
public class NameSort implements Comparator<FSElement> {

	@Override
	public int compare(FSElement o1, FSElement o2) {
		return o2.getName().compareTo(o1.getName());
	}

}
