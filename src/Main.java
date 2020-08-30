public class Main
{
	/* Grabs pictures from '/path/to/pool', sorts them based
	 * based on how pretty they are, and prints sorted pictures
	 * in '../pictures'. (This of course assumes that both of
	 * these directories exist, and that there are pictures in
	 * '/path/to/pool'.
	 *
	 */
	public static void main(String[] args)
	{
		if(!validNumberOfArgs(args)){
			System.out.println("Wrong number of args!");
			return;
		}

		int AMOUNT_OF_PICTURES = 10;
		PictureManager pictureManager = new PictureManager(args[0]);

		if(runWithoutCache(args, pictureManager)){
			System.out.println("Running without using cache");
			pictureManager.writeXFromRandom(AMOUNT_OF_PICTURES, args[1]);
		}
		else if(runDefault(args, pictureManager)){
			System.out.println("Running using cache");
			pictureManager.writeXFromPrettiest(AMOUNT_OF_PICTURES, args[1]);
		}
		else
			System.out.println("Bad flag somewhere!");

	}

	public static boolean validNumberOfArgs(String[] args)
	{
		return (args.length >= 2 && args.length <= 3);
	}


	public static boolean runWithoutCache(String[] args, PictureManager manager)
	{
		if(args.length == 3 && args[2].equals("--no-cache"))
			return true;

		if(args.length == 2 && !manager.hasCache())
			return true;

		return false;
	}

	public static boolean runDefault(String[] args, PictureManager manager)
	{
		return (args.length == 2) ;
	}
}
