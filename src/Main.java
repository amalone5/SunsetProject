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
		//TODO make this a real app! 
		//
		//	args[0] = '/path/to/archive/' 
		//	args[1] = '/path/to/pool/' 
		//	args[2] =  no cache flag -> --no-cache 
			
		if(args.length < 2 || args.length > 3){
			System.out.println("Wrong number of args!");
			return;
		}

		if(args.length == 3 && !args[2].equals("--no-cache")){
			System.out.println("Don't know what "+args[2]+" means, bailing");
			return;
		}

		int AMOUNT_OF_PICTURES = 10;
		PictureManager pictureManager = new PictureManager(args[0]);

		if(args.length == 2) // default - try to use cache, bail to random pictures if no cache
			if(pictureManager.hasCache()){
				pictureManager.writeXFromPrettiest(AMOUNT_OF_PICTURES, args[1]);
				return;
			}
		
		pictureManager.writeXFromRandom(AMOUNT_OF_PICTURES, args[1]);
	}
}
