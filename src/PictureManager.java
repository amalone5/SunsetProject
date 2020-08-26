import java.util.Scanner;
import java.util.ArrayList;

public class PictureManager {
	public String picturePath;
	public Picture[] pictures;
	public Bash bash;
	public Sort sort;

	public PictureManager(String picturePath) {
		this.picturePath = picturePath;
		bash = new Bash();
		sort = new Sort();
		pictures = null;
	}

	public Picture[] getPictures() {
		ArrayList<String> pictureNames = bash.getFilesFromDirectory(picturePath);
		Picture[] thePictures = new Picture[pictureNames.size()];

		for (int i = 0; i < thePictures.length; i++) {
			System.out.println("Generating picture " + i);
			String pictureName = pictureNames.get(i);
			thePictures[i] = new Picture(pictureName);
		}

		return sort.shellSort(thePictures);
	}

	public void sortPictures()
	{
		pictures = sort.shellSort(pictures);
	}

	public void writeTenPrettiest() {
		for (int i = 0; i < 10; i++)
			pictures[i].writePixelsToPicture(pictures[i].getAllPixels(), "../pictures/Pretty" + i + ".jpg");
	}

	public void writeAll() {
		for (int i = 0; i < pictures.length; i++)
			pictures[i].writePixelsToPicture(pictures[i].getAllPixels(), "../pictures/Pretty" + (100000 + i + 1) + ".jpg");

	}

	public void writeScores() {
		bash.executeCommand("rm ../pictures/pictureinfo.txt");
		for (int i = 0; i < pictures.length; i++)
			pictures[i].writePrettyScore();
	}

	private void writePictureCandidateNames()
	{
		String command = "find "+picturePath+" -type f | grep 'jpg' ";
		command = command + " > "+picturePath+"picturecandidates.txt";
		bash.executeCommand(command);
	}

	public ArrayList<String> getPictureCandidateNames()
	{
		writePictureCandidateNames();
		Scanner picturecandidates = bash.getFile(picturePath+"picturecandidates.txt");
		ArrayList<String> names = new ArrayList<>();

		while (picturecandidates.hasNext())
			names.add(picturecandidates.nextLine());

		bash.executeCommand("rm "+picturePath+"picturecandidates.txt");
		picturecandidates.close();
		return names;
	}

	public void writeHistograms()
	{
		for(int i = 0; i < pictures.length; i++)
		{
			pictures[i].intensityHistogram();
		}
	}

	public Picture[] takeXFromPool(int x)
	{
		Picture[] randomPictures = new Picture[x];
		ArrayList<String> names = getPictureCandidateNames();

		for(int i = 0; i < randomPictures.length; i++)
		{
			System.out.println("Generating "+i);
			int random = (int)(Math.random() * names.size());
			randomPictures[i] = new Picture(names.get(random));
		}
		return randomPictures; 
	}

	public Picture[] take1000FromPool()
	{
		Picture[] randomPictures = new Picture[1000];
		ArrayList<String> names = getPictureCandidateNames();

		for(int i = 0; i < randomPictures.length; i++)
		{
			System.out.println("Generating "+i);
			int random = (int)(Math.random() * names.size());
			randomPictures[i] = new Picture(names.get(random));
		}

		return randomPictures;
	}

	public ArrayList<StrippedPicture> getStrippedPictures()
	{
		Scanner pictureCache = bash.getFile("picturecache.txt");
		ArrayList<StrippedPicture> strippedPictures = new ArrayList<>();

		while(pictureCache.hasNext())
		{
			String name = pictureCache.next();
			boolean pictureIsBright = pictureCache.nextBoolean(); 
			double redBlueRatio = pictureCache.nextDouble(); 
			StrippedPicture nextPicture = new StrippedPicture(name,pictureIsBright,redBlueRatio);
			strippedPictures.add(nextPicture);
		}
		return strippedPictures;
	}

	public void cacheArchive()
	{
		bash.executeCommand("rm -f picturecache.txt");
		ArrayList<String> names = getPictureCandidateNames();

		Picture picture;
		StrippedPicture strippedPicture;

		for(int i = 0; i < names.size(); i++)
		{
			System.out.println("Caching "+names.get(i));
			picture = new Picture(names.get(i));
			strippedPicture = new StrippedPicture(
					picture.name,
					picture.pictureIsBright(),
					picture.redBlueRatio());

			bash.executeCommand(
					"echo '"+strippedPicture.toString()+"' >> picturecache.txt");
		}
	}

	public String toString() {
		String output = "[";
		int i;
		for (i = 0; i < pictures.length - 1; i++)
			output = output + pictures[i].toString() + ", ";

		return output;
	}
}
