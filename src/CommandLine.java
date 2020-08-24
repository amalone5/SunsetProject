import java.io.InputStreamReader;
import java.io.BufferedReader;

import java.util.Scanner;
import java.util.ArrayList;

import java.io.FileInputStream;
import java.io.FileNotFoundException;



public class CommandLine
{
	public boolean executeBashCommand(String command) 
	{
		boolean success = false;
		Runtime r = Runtime.getRuntime();
		String[] commands = {"bash", "-c", command};
		try 
		{
			Process p = r.exec(commands);

			p.waitFor();
			BufferedReader b = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = "";

			while ((line = b.readLine()) != null) 
			{
				System.out.println(line);
			}

			b.close();
			success = true;
		} 
		catch (Exception e) 
		{
			System.err.println("Failed to execute bash with command: " + command);
			e.printStackTrace();
		}
		return success;
	}

	public Scanner getFile(String filename)
	{
		Scanner fileIn = null; // Initializes fileIn to empty
		try
		{
			fileIn = new Scanner(
					new FileInputStream(filename) );
		}
		catch (FileNotFoundException e)
		{
			System.out.println("File not found.");
			System.exit(0);
		}
		return fileIn;
	}

	public String pwd()
	{
		executeBashCommand("pwd > tempPwd");
		Scanner tempPwd = getFile("tempPwd"); 
		String pwd = tempPwd.nextLine();
		executeBashCommand("rm tempPwd");
		return pwd;
	}

	public ArrayList<String> getFilesFromDirectory(String path)
	{
		executeBashCommand("find "+path+" -maxdepth 1 -type f > files");
		Scanner files = getFile("files"); 
		ArrayList<String> listOfFiles = new ArrayList<String>();

		while(files.hasNext())
			listOfFiles.add(files.nextLine());

		return listOfFiles;
	}

}
