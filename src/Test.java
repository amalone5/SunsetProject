import java.util.ArrayList;
import java.util.Collections;

public class Test
{
	Picture picture;
	PictureManager manager;
	Bash bash;

	StrippedPicture strippedPicture;

	/*
	 * This will take forever if there are 
	 * lots of picture files
	 */

	public Test()
	{
		strippedPicture = new StrippedPicture(
				"/mnt/SlaveDrive2/BigPictureProject/full/archive/2017/08/20170810-034432.jpg",true,1.5);

		bash = new Bash();

		manager = new PictureManager("/mnt/SlaveDrive2/BigPictureProject/full/");

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
	}
}
