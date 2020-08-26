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
		//	args[2] =  sample size 
			

		PictureManager manager = new PictureManager("/mnt/SlaveDrive2/BigPictureProject/pool");
		manager.writeAll();
	}
}
