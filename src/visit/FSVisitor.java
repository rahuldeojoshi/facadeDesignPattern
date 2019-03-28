/**
 * 
 */
package visit;

import facade.*;

/**
 * @author RDJ
 *
 */
public interface FSVisitor {
	
	/*
	 * Visitor methods for respective FSElements
	 */
	void visit(Link link);
	void visit(Directory dir);
	void visit(File file);

}
