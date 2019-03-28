/**
 * 
 */
package visit;

import facade.*;

/**
 * @author RDJ
 *
 */
public class SizeCountingVisitor implements FSVisitor {

	private int totalSize;
	
	/**
	 * @return the totalSize
	 */
	public int getTotalSize() {
		return totalSize;
	}

	/* (non-Javadoc)
	 * @see visitor.FSVisitor#visit(filesystem.Link)
	 */
	@Override
	public void visit(Link link) {
		totalSize += link.getDiskUtil();
	}

	/* (non-Javadoc)
	 * @see visitor.FSVisitor#visit(filesystem.Directory)
	 */
	@Override
	public void visit(Directory dir) {
		totalSize += dir.getDiskUtil();
	}

	/* (non-Javadoc)
	 * @see visitor.FSVisitor#visit(filesystem.File)
	 */
	@Override
	public void visit(File file) {
		totalSize += file.getDiskUtil();
	}

}
