package sorting;

import java.util.Comparator;

import facade.FSElement;

/**
 * @author RDJ
 *
 */
public class ReverseAlphabetSort implements Comparator<FSElement> {

	@Override
	public int compare(FSElement o1, FSElement o2) {
		// TODO Auto-generated method stub
		return o1.getCreated().compareTo(o2.getCreated());
	}

}
