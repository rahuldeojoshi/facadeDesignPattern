/**
 * 
 */
package facade;

import visit.FSVisitor;

/**
 * @author RDJ
 *
 */
public class Link extends FSElement {

	
	private FSElement target;
	private int size;
	
	/**
	 * @return the linkElement
	 */
	public FSElement getTarget() {
		return target;
	}
	/**
	 * @param linkElement the linkElement to set
	 */
	public void setTarget(FSElement target) {
		this.target = target;
		this.size = target.getSize();
	}
	
	/**
	 * @return the size
	 */
	@Override
	public int getSize() {
		return size;
	}

	/**
	 * @param parent
	 * @param name
	 * @param owner
	 * @param size
	 */
	public Link(Directory parent, String name, String owner, int size) {
		super(parent, name, owner, size);
		super.setLeaf(true);
		if(parent!=null)
			parent.appendChild(this);
		else
			FileSystem.getRootDir().appendChild(this);
	}
	
	/* (non-Javadoc)
	 * @see filesystem.FSElement#accept(visitor.FSVisitor)
	 */
	@Override
	public void accept(FSVisitor visit) {
		visit.visit(this);
	}

	/* (non-Javadoc)
	 * @see filesystem.FSElement#getDiskUtil()
	 */
	@Override
	public int getDiskUtil() {
		return 0;
	}

}
