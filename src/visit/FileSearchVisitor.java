/**
 * 
 */
package visit;

import java.util.ArrayList;

import facade.*;

/**
 * @author RDJ
 *
 */
public class FileSearchVisitor implements FSVisitor {

	private String extension;
	private ArrayList<File> foundFiles;
	
	/**
	 * @return the extension
	 */
	public String getExtension() {
		return extension;
	}

	/**
	 * @return the foundFiles
	 */
	public ArrayList<File> getFoundFiles() {
		return foundFiles;
	}

	/**
	 * 
	 */
	public FileSearchVisitor(String extension) {
		this.extension = extension;
		foundFiles = new ArrayList<File>();
	}

	/* (non-Javadoc)
	 * @see visitor.FSVisitor#visit(filesystem.Link)
	 */
	@Override
	public void visit(Link link) {
	}

	/* (non-Javadoc)
	 * @see visitor.FSVisitor#visit(filesystem.Directory)
	 */
	@Override
	public void visit(Directory dir) {
	}

	/* (non-Javadoc)
	 * @see visitor.FSVisitor#visit(filesystem.File)
	 */
	@Override
	public void visit(File file) {
		if(file.getName().contains(extension))
			foundFiles.add(file);		
	}

}
