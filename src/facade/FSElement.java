package facade;

import java.util.Date;

import visit.FSVisitor;

/**
 * @author RDJ
 *
 */
public class FSElement {

	private Directory parent;
	private String name;
	private String owner;
	private final Date created;
	private Date lastModified;
	private int size;
	private boolean isLeaf;

	/**
	 * @return the parent
	 */
	public Directory getParent() {
		return parent;
	}

	/**
	 * @param parent
	 *            the parent to set
	 */
	public void setParent(Directory parent) {
		this.lastModified = new Date();
		this.parent = parent;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.lastModified = new Date();
		this.name = name;
	}

	/**
	 * @return the owner
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * @param owner
	 *            the owner to set
	 */
	public void setOwner(String owner) {
		this.lastModified = new Date();
		this.owner = owner;
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param size
	 *            the size to set
	 */
	public void setSize(int size) {
		this.lastModified = new Date();
		this.size = size;
	}

	/**
	 * @return the created
	 */
	public Date getCreated() {
		Date createdCopy = (Date) created.clone();
		return createdCopy;
	}

	/**
	 * @return the lastModified
	 */
	public Date getLastModified() {
		Date modifiedCopy = (Date) lastModified.clone();
		return modifiedCopy;
	}

	/**
	 * @return the isLeaf
	 */
	public boolean isLeaf() {
		return isLeaf;
	}

	/**
	 * @param isLeaf
	 *            the isLeaf to set
	 */
	public void setLeaf(boolean isLeaf) {
		this.lastModified = new Date();
		this.isLeaf = isLeaf;
	}

	/**
	 * Constructor
	 */
	public FSElement(Directory parent, String name, String owner, int size) {
		this.parent = parent;
		this.name = name;
		this.owner = owner;
		this.created = new Date();
		this.lastModified = new Date();
		this.size = size;
	}

	// visitor
	public void accept(FSVisitor visit) {
	}

	// SizeVisitor
	public int getDiskUtil() {
		return this.size;
	}
}
