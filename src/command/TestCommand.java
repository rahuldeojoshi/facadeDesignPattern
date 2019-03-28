package command;


import java.util.ArrayList;

import facade.*;
import visit.DirSearchVisitor;

@SuppressWarnings("unused")
public class TestCommand {

	public TestCommand() {
	}

	public static void main(String[] args) {
		FileSystem fs = FileSystem.getFileSystem();

		Directory root = FileSystem.getRootDir();
		Directory system = new Directory(root, "system", "Rahul", 0);
		Directory pictures = new Directory(root, "Pictures", "Rahul", 0);
		Directory home = new Directory(pictures, "home", "Rahul", 0);

		File a = new File(system, "FileA", "Easy", 20);
		File b = new File(system, "FileB", "But", 11);
		File c = new File(pictures, "FileC", "Confusing", 10);
		File d = new File(home, "FileD", "Rahul", 17);
		File e = new File(pictures, "FileD", "Rahul", 17);
		File f = new File(pictures, "FileF", "Rahul", 17);
		Link linkX = new Link(home, "x", "Rahul", 0);
		Link linkY = new Link(pictures, "y", "Rahul", 0);
		FileSystem.setCurrent(pictures);
////		Cd cd = new Cd(new String[]{"cd",".."});
////
////		Cd cd2 = new Cd(new String[]{"cd","system"});
////		
////		System.out.println(FileSystem.getCurrent().getName());
////		cd.execute();
////		System.out.println( FileSystem.getCurrent().getName());
////		cd2.execute();
////		System.out.println(FileSystem.getCurrent().getName());
////
//		// Testing cd command, runs good
//		Pwd pwd = new Pwd(new String[]{"pwd"});
//		pwd.execute();
////		
//		Ls ls = new Ls(new String[]{"ls"});
//		ls.execute();
//		
//		Dir dir = new Dir(new String[]{"dir","home"});		
//		
//		Mkdir mkdir = new Mkdir(new String[]{"mkdir","test"});
//		mkdir.execute();
//	
//		dir.execute();
//		
//		Touch touch = new Touch(new String[]{"touch","testTouch"});
//		touch.execute();
////		
////		Ln ln = new Ln(new String[]{"ln","FileD","link"});
////		ln.execute();
////		
//		ls.execute();
//		
////		Chown chown = new Chown(new String[]{"chown","RJ","FileA"});
////		chown.execute();
//
//		
//
//		Rmdir rmdir = new Rmdir(new String[]{"rmdir","test"});
//		rmdir.execute();
//		
//		dir.execute();
//		
//		History his = new History();
//		his.execute();
		
		Sort s = new Sort();
		s.execute();
		

	}

}
