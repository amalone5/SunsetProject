public class Main
{
	/* Grabs pictures from '/path/to/pool', sorts them based
	 * based on how pretty they are, and prints sorted pictures
	 * in '../pictures'. (This of course assumes that both of
	 * these directories exist, and that there are pictures in
	 * '/path/to/pool'.
	 */
	public static void main(String[] args)
	{
		PictureManager manager = new PictureManager("/mnt/SlaveDrive2/BigPictureProject/pool");
		manager.writeAll();
	}
}
