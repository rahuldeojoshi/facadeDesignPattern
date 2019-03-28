package facade;

import java.util.*;

import visit.FSVisitor;

/**
 * @author RDJ
 *
 */
public class Directory extends FSElement {

//	private FileSystem fileSystem = FileSystem.getFileSystem();
	
	private ArrayList<FSElement> children;
	/**
	 * @param parent
	 * @param name
	 * @param owner
	 * @param created
	 * @param lastModified
	 * @param size
	 */
	public Directory(Directory parent, String name, String owner, int size) {
		super(parent, name, owner, size);
		super.setLeaf(false);
		this.children = new ArrayList<FSElement>();
		if(parent != null)
			parent.appendChild(this);
	}
	
	/**
	 * @return the children
	 */
	public ArrayList<FSElement> getChildren() {
		return children;
	}
	
	// Add a child to children ArrayList
	public void appendChild(FSElement child){
		this.children.add(child);
	}
	
	//Remove Directory from children
	public void removeChild(FSElement child){
		this.children.remove(child);
	}
	
	/*
	 * Use this method when sorting 
	 * Update the children array according to the 
	 * index you get for the corresponding child.
	 * 
	 * Not usually called, this is just to implemented 
	 * pluggable sorting feature
	 */
	public void addChild(FSElement child, int index){
		this.children.add(index, child);
	}
	
	
	@Override
	public int getSize(){
		int size = 0;
		Iterator<FSElement> it = getChildren().iterator();
		while(it.hasNext()){
			FSElement element = it.next();
			size += element.getSize();
		}
		return size;
	}
	
	/* (non-Javadoc)
	 * @see filesystem.FSElement#accept(visitor.FSVisitor)
	 */
	@Override
	public void accept(FSVisitor visit) {
		visit.visit(this);
		for(FSElement v: children){
			v.accept(visit);
		}
	}

	/* (non-Javadoc)
	 * @see filesystem.FSElement#getDiskUtil()
	 */
	@Override
	public int getDiskUtil() {
		return 0;
	}
}
