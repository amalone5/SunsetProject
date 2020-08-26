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
		manager = new PictureManager("/mnt/SlaveDrive2/BigPictureProject/pool");
	}

	public void init_picture()
	{
		picture = new Picture("../pictures/Pretty10000.jpg");
	}

	public void writeAndAnalyzePool()
	{
		manager.writeAll();
		manager.writeScores();
	}	

	public static void main(String[] args)
	{
		Test test = new Test();
		test.init_manager();
		test.writeAndAnalyzePool();
	}
}
