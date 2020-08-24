import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import java.util.Scanner;
import java.util.ArrayList;

public class Test
{
	Picture picture;
	PictureManager manager;
	Point point;
	CommandLine bash;
	ArryFun arrayFunctions;

	public Test(int dummy1, int dummy2)
	{

	}


	public Test(int dummy)
	{
		manager = new PictureManager("/mnt/SlaveDrive/BigPictureProject/pool");
	}


	public Test() {
		picture = new Picture("somepicture.jpg");
		manager = new PictureManager("/mnt/SlaveDrive/BigPictureProject/pool");
		point = new Point(1,2);
		bash = new CommandLine();
		arrayFunctions = new ArryFun();

	}


	public void printPictureStats(){
		picture.printPictureStats();
	}


	public void sumRedAtHeight()
	{
		System.out.println("Red sum: "+picture.sumRedAtHeight(0));
	}


	public void prettyScore()
	{
		System.out.println("Pretty score: "+picture.prettyScore());
	}


	public void prettierThan()
	{
		Picture pretty1 = new Picture("pretty1.jpg");
		Picture pretty2 = new Picture("pretty2.jpg");

		Picture ugly1 = new Picture("ugly1.jpg");
		Picture ugly2 = new Picture("ugly2.jpg");

		System.out.print("Test 1: ");

		if(pretty1.prettierThan(ugly1))
			System.out.println("Pass\n");
		else
			System.out.println("Fail\n");


		System.out.print("Test 2: ");
		if(pretty1.prettierThan(ugly2))
			System.out.println("Pass\n");
		else
			System.out.println("Fail\n");

		System.out.print("Test 3: ");
		if(pretty2.prettierThan(ugly1))
			System.out.println("Pass\n");
		else
			System.out.println("Fail\n");

		System.out.print("Test 4: ");
		if(pretty2.prettierThan(ugly2))
			System.out.println("Pass\n");
		else
			System.out.println("Fail\n");
	}

	public void prettierThan2()
	{
		Picture ugly1 = new Picture("ugly1.jpg");
		Picture superugly = new Picture("superugly.jpg");

		System.out.println(ugly1.prettierThan(superugly));
	}

	public void getRedPixels()
	{
		picture.writePixelsToPicture(picture.getRedPixels(245),"reallyred.jpg");
	}

	public void getColorGradients()
	{
		int pictureCount = 0;
		for(int threshold = 255; threshold >= 0; threshold -= 1)
		{
			picture.writePixelsToPicture(picture.getAllPixels(threshold),"THRESHOLD_"+threshold+".jpg");
			pictureCount ++;
		}

	}

	public void getBrightestPoint()
	{
		Point realBright = picture.getBrightestPoint();
		System.out.println("Brightest point is: "+realBright.toString());
	}

	public void getColorsAtThreshold()
	{
		int numberOfColors;

		for(int i = 0; i < 255; i++)
		{
			numberOfColors = picture.getColorsAtThreshold(i);
			System.out.println(numberOfColors);
		}
	}


	public void rename()
	{
		picture.rename("somenewname.jpg");
	}


	public void isJpgFile()
	{
		System.out.println(
				picture.isJpgFile("0123456789.jpeg"));
	}

	public void writeSolidPixels()
	{
		picture.writeSolidPixels(100,100);
	}


	public void chart()
	{
		picture.chart(4,11);
	}


	public void intensityHistogram()
	{
		picture.intensityHistogram();

	}

	/* Tests for Point */

	public void point_toString()
	{
		System.out.println(point.toString());
	}

	/* Tests for CommandLine*/


	public void executeBashCommand() 
	{
		bash.executeBashCommand("fortune | cowsay");
	}


	public void pwd()
	{
		System.out.println(bash.pwd());
	}

	public void getFilesFromDirectory()
	{
		String thisPath = bash.pwd();
		ArrayList<String> theList = bash.getFilesFromDirectory(thisPath);

		//for(int i = 0; i  < theList.size(); i++)
			System.out.println(theList);
	}

	public void tinkering1()
	{
		Picture Pretty10439 = new Picture("Pretty11002.jpg");
		System.out.println("Score: "+Pretty10439.redBlueRatioProduct());
	}


	/* Tests for PictureManager */


	public void writeTenPrettiest()
	{
		manager.writeTenPrettiest();
	}

	public void writeAll()
	{
		manager.writeAll();
	}

	public void getPictures()
	{
		Picture[] pictures = manager.pictures;
		System.out.println("Picture null test: "+ (pictures == null));
		arrayFunctions.showGenArray(pictures);
	}


	public static void main(String[] args)
	{
		Test test = new Test(0);
		test.writeAll();
	}

}
