package facade;

import java.util.Scanner;

import command.*;

/**
 * @author RDJ
 *
 */
public class ClientMain {

	private static Scanner scan;
	private static File a;
	private static File b;
	private static File c;

	public static void main(String[] args) {

		// create a directory add some children and execute ls on that
		Directory home = new Directory(FileSystem.getRootDir(), "home", "Rahul", 0);

		a = new File(home, "a", "Rahul", 10);
		b = new File(home, "b", "Rahul", 13);
		c = new File(home, "c", "Rahul", 11);

		System.out.println("Creating Directory '" + home.getName() + "' in root Directory"
				+ "\nAnd the files under 'home' are\n" + a.getName() + " " + b.getName() + " " + c.getName() + "\n");

		boolean exit = false;

		scan = new Scanner(System.in);

		System.out.println(
				"Use 'touch' command to create new file under current directory " + "\nUsage : touch <file name>");
		System.out.println("\nType 'exit' to stop.");

		// String s = scan.nextLine();
		while (!exit) {

			System.out.println(">");

			String s = scan.nextLine();

			args = s.split(" ");

			switch (args.length) {

			case 1:
				if (s.equals("exit")) {
					exit = true;
				} else if (s.equals("ls")) {
					Ls ls = new Ls(args);
					ls.execute();
				} else if (s.equals("dir")) {
					Dir dir = new Dir(args);
					dir.execute();
				} else if (s.equals("pwd")) {
					Pwd pwd = new Pwd(args);
					pwd.execute();
				} else if (s.equals("sort")) {
					Sort sort = new Sort();
					sort.execute();
				} else if (s.equals("history")) {
					History history = new History();
					history.execute();
				} else if (s.equals("cd")) {
					Cd cd = new Cd(args);
					cd.execute();
				} else if (s.equals("chown")) {
					Chown chown = new Chown(args);
					chown.execute();
				} else if (s.equals("rmdir")) {
					Rmdir rmdir = new Rmdir(args);
					rmdir.execute();
				} else if (s.equals("mkdir")) {
					Mkdir mkdir = new Mkdir(args);
					mkdir.execute();
				} else if (s.equals("touch")) {
					Touch touch = new Touch(args);
					touch.execute();
				} else if (s.equals("ln")) {
					Ln ln = new Ln(args);
					ln.execute();
				} else if (s.equals("redo")) {
					Redo redo = new Redo();
					redo.execute();
				} else {
					System.out.println("Command not found");
				}
				break;

			case 2:
				if (s.contains("mk")) {
					Mkdir mkdir = new Mkdir(args);
					mkdir.execute();
				} else if (s.contains("rm")) {
					Rmdir rmdir = new Rmdir(args);
					rmdir.execute();
				} else if (s.contains("dir")) {
					Dir dir = new Dir(args);
					dir.execute();
				} else if (s.contains("cd")) {
					Cd cd = new Cd(args);
					cd.execute();
				} else if (s.contains("touch")) {
					Touch touch = new Touch(args);
					touch.execute();
				} else {
					System.out.println("Command not found");
				}
				break;

			case 3:
				if (s.contains("ln")) {
					Ln ln = new Ln(args);
					ln.execute();
				} else if (s.contains("chown")) {
					Chown chown = new Chown(args);
					chown.execute();
				} else {
					System.out.println("Command not found");
				}
				break;

			default:
				System.out.println("Please check the command");
				break;
			}
		}
	}
}
