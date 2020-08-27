import java.util.ArrayList;
import java.util.Collections;

public class Test
{
	Picture picture;
	PictureManager manager;
	Bash bash;

	public StrippedPicture strippedPicture;

	/*
	 * This will take forever if there are 
	 * lots of picture files
	 */
	public void init_all()
	{
		init_bash();
		init_manager();
		init_picture();
	}

	public void init_strippedPicture()
	{
		strippedPicture = new StrippedPicture(
				"/mnt/SlaveDrive2/BigPictureProject/full/archive/2017/08/20170810-034432.jpg",true,1.5);
	}

	public void init_bash()
	{
		bash = new Bash();
	}

	public void init_manager()
	{
		manager = new PictureManager("/mnt/SlaveDrive2/BigPictureProject/full/");
	}

	public void init_picture()
	{
		picture = new Picture(
						"/home/austin/Documents/CodeBase/GitCode/SunsetProject/magnificent/Pretty10397.jpg");
	}

	/* Tests for Picture */

	public void redBlueRatio()
	{
		double ratio = picture.redBlueRatio();
		System.out.println("Ratio: "+ratio);
	}

	/* Tests for PictureManager */

	public void writeXFromPrettiest()
	{
		manager.writeXFromPrettiest(10,"/home/austin/Documents/CodeBase/GitCode/SunsetProject/pictures/");

	}

	public void writeXFromRandom()
	{
		manager.writeXFromRandom(100,"/home/austin/Documents/CodeBase/GitCode/SunsetProject/pictures/");

	}

	public void cacheArchive()
	{
		manager.cacheArchive();
	}

	/* Tests for StrippedPicture*/

	public StrippedPicture makeRandomPicture()
	{
		double random = Math.random()*2;
		StrippedPicture stripped = new StrippedPicture("dumb.jpg",true,random);
		return stripped;
	}

	public StrippedPicture[] makeManyPictures(int amountOfPictures)
	{
		StrippedPicture[] pictures = new StrippedPicture[amountOfPictures];

		for(int i = 0; i < amountOfPictures; i++)
			pictures[i] = makeRandomPicture();

		return pictures;
	}

	public void copyTo()
	{
		strippedPicture.copyTo("otherNothing.jpg");
	}

	/* Analysis */

	public static void main(String[] args)
	{
		Test test = new Test();
		test.init_manager();
		test.writeXFromPrettiest();
	}
}
