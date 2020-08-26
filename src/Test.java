import java.util.ArrayList;

public class Test
{
	Picture picture;
	PictureManager manager;
	Bash bash;
	Sort sort;

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

	public void getStrippedPictures()
	{
		ArrayList<StrippedPicture> strippedPictures = manager.getStrippedPictures();
		System.out.println(strippedPictures);

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
		test.getStrippedPictures();
	}
}
