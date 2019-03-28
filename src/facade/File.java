package facade;

import visit.FSVisitor;

/**
 * @author RDJ
 *
 */
public class File extends FSElement {

	/**
	 * @param parent
	 * @param name
	 * @param owner
	 * @param created
	 * @param lastModified
	 * @param size
	 */
	public File(Directory parent, String name, String owner, int size) {
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
		return this.getSize();
	}

}
