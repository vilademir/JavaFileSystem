import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

public class FileSystem {
	
	private static String logFileName;
	
	public static void main (String []args) throws IOException {
		askForInputs();
	}
	
	public static void askForInputs() throws IOException {
		
		System.out.println("Welcome to the file system!");
		
		String question = JOptionPane.showInputDialog("Would you like to dump results into a log file? (yes/no)");
		
		if (question .equals("yes")) {
			String askLog = JOptionPane.showInputDialog("What would you like to name that log?");
			PrintWriter logFileName = new PrintWriter(new FileOutputStream(askLog + ".txt"));	
			options();
		}
		if (question .equals("no")) {
			optionsTwo();	
		}
	}
	
	public static void options() throws IOException{
		System.out.println("Now, what would you like to do today?");
		System.out.println("1 = List the contents of a directory");
		System.out.println("2 = List the contents of a directory as all of its sub directories");
		System.out.println("3 = Locate a file");
		System.out.println("4 = Locate files with a given file extension");
		System.out.println("5 = Concatenate the contents of 2 files and output the result to a third file");
		System.out.println("6 = Exit");
		
		String line = JOptionPane.showInputDialog("What would you like to do?");
		
		if (line .equals("1")) {
			String ask = JOptionPane.showInputDialog("What directory would you like to list?");
			listContents(ask, false, logFileName);
		}
		if (line .equals("2")) {
			String ask = JOptionPane.showInputDialog("What directory would you like to list?");
			listContents(ask, true, logFileName);
			
		}
		if (line .equals("3")) {
			String ask = JOptionPane.showInputDialog("What directory would you like to search?");
			String askTwo = JOptionPane.showInputDialog("What file would you like to search for?");
			listContents(ask, askTwo, logFileName);
		}
		if (line .equals("4")) {
			String ask = JOptionPane.showInputDialog("What directory would you like to search?");
			String askTwo = JOptionPane.showInputDialog("What file would you like to search for?");
			listContents(ask, askTwo, logFileName);
		}
		if (line .equals("5")) {
			String ask = JOptionPane.showInputDialog("What is the first file you would like to concatenate?");
			String askTwo = JOptionPane.showInputDialog("What is the second file you would like to concatenate?");
			String askThree = JOptionPane.showInputDialog("What would you like to name the new file?");
			concatenateFiles(ask, askTwo, askThree);
		}
		if (line .equals("6")) {
			System.out.println("The program will now end. Have a nice day!");
		}
	}
		
	
	public static void optionsTwo() throws IOException {
		System.out.println("Now, what would you like to do today?");
		System.out.println("1 = List the contents of a directory");
		System.out.println("2 = List the contents of a directory as all of its sub directories");
		System.out.println("3 = Locate a file");
		System.out.println("4 = Locate files with a given file extension");
		System.out.println("5 = Concatenate the contents of 2 files and output the result to a third file");
		System.out.println("6 = Exit")
		
		String line = JOptionPane.showInputDialog("What would you like to do?");
		
		if (line .equals("1")) {
			String ask = JOptionPane.showInputDialog("What directory would you like to list?");
			listContents(ask, false);
		}
		if (line .equals("2")) {
			String ask = JOptionPane.showInputDialog("What directory would you like to list?");
			listContents(ask, true);
		}
		if (line .equals("3")) {
			String ask = JOptionPane.showInputDialog("What directory would you like to search?");
			String askTwo = JOptionPane.showInputDialog("What file would you like to search for?");
			listContents(ask, askTwo);
			
		}
		if (line .equals("4")) {
			String ask = JOptionPane.showInputDialog("What directory would you like to search?");
			String askTwo = JOptionPane.showInputDialog("What file would you like to search for?");
			listContents(ask, askTwo);
		}
		if (line .equals("5")) {
			String ask = JOptionPane.showInputDialog("What is the first file you would like to concatenate?");
			String askTwo = JOptionPane.showInputDialog("What is the second file you would like to concatenate?");
			String askThree = JOptionPane.showInputDialog("What would you like to name the new file?");
			concatenateFiles(ask, askTwo, askThree);
	
		}
		if (line .equals("6")) {
			System.out.println("The program will now end. Have a nice day!");
		}
	}
	
	public static void listContents(String directory, boolean recursion) throws FileNotFoundException {
		File createFile = new File(directory); 
		if(createFile.isDirectory() && createFile.canRead()){
			for(int pos = 0; pos < createFile.listFiles().length; pos++){
				System.out.println(createFile.listFiles()[pos]);
				if(recursion){
					listContents(createFile.listFiles()[pos].toString(), true);
				}
			}
		}
	}
	
	public static void listContents(String directory, boolean recursion, String dumplog) throws FileNotFoundException{		
		PrintWriter logFile = new PrintWriter(new FileOutputStream(dumplog + ".txt"));
		
		File creatFile = new File(directory);
		
		if(creatFile.isDirectory() && creatFile.canRead()){
			for(int pos = 0; pos < creatFile.listFiles().length; pos++){
				System.out.println(creatFile.listFiles()[pos]);
				logFile.println(creatFile.listFiles()[pos]);
				if(recursion){
					listContents(creatFile.listFiles()[pos].toString(), true, dumplog);
					logFile.println(creatFile.listFiles()[pos]);
				}
			}
		}
		logFile.close();
	}
	
	public static void listContents(String directory, String searchFile) throws FileNotFoundException{		
		File createFile = new File(directory);
		if(createFile.isDirectory() && createFile.canRead()){
			for(int pos = 0; pos < createFile.listFiles().length; pos++){
				listContents(createFile.listFiles()[pos].toString(), searchFile);
				if(createFile.listFiles()[pos].getName().contains(searchFile)){
					System.out.println("File(s) located at: " + createFile.listFiles()[pos].toPath());
				}
			}
		}
	}
	
	public static void listContents(String directory, String searchFile, String dumplog) throws FileNotFoundException
	{		
		PrintWriter logFile = new PrintWriter(new FileOutputStream(dumplog + ".txt"));
		File createFile = new File(directory);
		if(createFile.isDirectory() && createFile.canRead())
		{
			for(int pos = 0; pos < createFile.listFiles().length; pos++)
			{
				listContents(createFile.listFiles()[pos].toString(), searchFile);
				if(createFile.listFiles()[pos].getName().contains(searchFile))
				{
					System.out.println("File(s) located at: " + createFile.listFiles()[pos].toPath());
					logFile.println("File(s) located at: " + createFile.listFiles()[pos].toPath());
				}
			}
		}
		logFile.close();
	}
	
	public static void concatenateFiles(String fileOnePath, String fileTwoPath, String fileThreePath) throws IOException{
		PrintWriter fileThree = new PrintWriter(new FileOutputStream(fileThreePath));
		
		fileThree.append(readFile(fileOnePath));
		fileThree.append(readFile(fileTwoPath));
		fileThree.close();
	}
	
	
	//FOUND AT: https://stackoverflow.com/questions/16027229/reading-from-a-text-file-and-storing-in-a-string
	public static String readFile(String fileName) throws IOException {
	    BufferedReader br = new BufferedReader(new FileReader(fileName));
	    try {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append("\n");
	            line = br.readLine();
	        }
	        return sb.toString();
	    } 
	    finally {
	        br.close();
	    }
	}
}

