import java.util.ArrayList;

public class Test
{
	Picture picture;
	PictureManager manager;
	Bash bash;
	Sort sort;

	public StrippedPicture strippedPicture;

	/*
	 * This will take forever if there are 
	 * lots of picture files
	 */
	public void init_all()
	{
		init_sort();
		init_bash();
		init_manager();
		init_picture();
	}


	public void init_strippedPicture()
	{
		strippedPicture = new StrippedPicture(
				"/mnt/SlaveDrive2/BigPictureProject/full/archive/2017/08/20170810-034432.jpg",true,1.5);
	}


	public void init_sort()
	{
		sort = new Sort();
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

//	public void getStrippedPictures()
//	{
//		ArrayList<StrippedPicture> strippedPictures = manager.getStrippedPictures();
//		System.out.println(strippedPictures);
//
//	}


	public void writeXPrettyPictures()
	{
		bash.executeCommand("touch startstamp.txt");
		manager.writeXPrettyPictures(1000000000);
		bash.executeCommand("touch endstamp.txt");
	}

	public void take1000FromPool()
	{
		manager.pictures = manager.take1000FromPool();
		System.out.println("Sorting...");
		manager.sortPictures();
//		System.out.println("Writing Scores...");
//		manager.writeScores();
		System.out.println("Writing Pictures...");
		manager.writeAll();
//		System.out.println("Writing Histograms...");
//		manager.writeHistograms();
	}


	public void cacheArchive()
	{
		manager.cacheArchive();
	}

	/* Tests for Sort*/


    public void shellSort() 
	{
		StrippedPicture[] unsorted = makeManyPictures(10);

		System.out.println("### Unsorted ###");
		for(int i = 0; i < unsorted.length; i++)
			System.out.println(unsorted[i].redBlueRatio );

		unsorted = sort.shellSort(unsorted);

		System.out.println("\n\n\n### Sorted ###");

		for(int i = 0; i < unsorted.length; i++)
			System.out.println(unsorted[i].redBlueRatio );

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

	public void writeAndAnalyzePool()
	{
		manager.pictures = manager.takeXFromPool(20);
		manager.sortPictures();
		manager.writeAll();
	}	

	public void calculateGoodAnchor()
	{
		double rbrp = picture.redBlueRatioProduct();
		System.out.println("Good rbrp: "+rbrp);
	}

	public static void main(String[] args)
	{
		Test test = new Test();
		test.init_manager();
		test.init_bash();
		test.writeXPrettyPictures();
	}
}
