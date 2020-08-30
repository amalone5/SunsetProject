import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class PictureManager {
	public String pathToArchive;
	public String pathToPool;
	public Bash bash;
	public PictureSorter pictureSorter;
	public StrippedPictureSorter strippedPictureSorter;

	public PictureManager(String pathToArchive, String pathToPool) {
		this.pathToPool = pathToPool;
		this.pathToArchive = pathToArchive;
		bash = new Bash();;
		pictureSorter = new PictureSorter();
		strippedPictureSorter = new StrippedPictureSorter();
	}

	public PictureManager(String pathToArchive) {
		this.pathToArchive = pathToArchive;
		bash = new Bash();
		pictureSorter = new PictureSorter();
		strippedPictureSorter = new StrippedPictureSorter();
	}

	public boolean hasCache(){
		File f = new File("picturecache.txt");
		if(f.exists() && !f.isDirectory()) 
			return true;

		return false;
	}

	public ArrayList<Picture> getXRandomPictures(int x)
	{
		ArrayList<Picture> randomPictures = new ArrayList<>();
		ArrayList<String> names = getPictureCandidateNames();

		for(int i = 0; i < x; i++)
		{
			System.out.println("Generating "+i);
			int random = (int)(Math.random() * names.size());
			randomPictures.add( new Picture(names.get(random)) );
		}

		return randomPictures;
	}

	public ArrayList<StrippedPicture> takeXRandomFromList(
			int x, ArrayList<StrippedPicture> pictures)
	{
		int SAMPLE_SIZE = 250;
		ArrayList<StrippedPicture> randomPictures = new ArrayList<>();
		for(int i = 0; i < x; i++)
		{
			int random = (int)(Math.random() * SAMPLE_SIZE);
			randomPictures.add( pictures.get(random) );
		}

		return randomPictures;
	}

	public void writeXFromPrettiest(int x)
	{
		ArrayList<StrippedPicture> pictures = getStrippedPictures();
		pictures.sort(strippedPictureSorter);
		Collections.reverse(pictures);
		ArrayList<StrippedPicture> random = takeXRandomFromList(x,pictures);

		for(int i = 0; (i < random.size()) &&
					   (i < x);  i++)
			random.get(i).copyTo(pathToPool+"Pretty"+(10000000 + i + 1));
	}


	public void writeXFromRandom(int x)
	{
		int SAMPLE_SIZE = 1000; 
		ArrayList<Picture> randomPictures = getXRandomPictures(SAMPLE_SIZE);

		randomPictures.sort(pictureSorter);
		Collections.reverse(randomPictures);

  		for (int i = 0; i < x; i++)
		{
			Picture current = randomPictures.get(i);
			current.writePixelsToPicture(pathToPool+"Pretty"+(10000000 + i + 1) );
		}
	}

	private void writePictureCandidateNames()
	{
		String command = "find "+pathToArchive+" -type f | grep 'jpg' ";
		command = command + " > "+pathToArchive+"picturecandidates.txt";
		bash.executeCommand(command);
	}

	public ArrayList<String> getPictureCandidateNames()
	{
		writePictureCandidateNames();
		Scanner picturecandidates = bash.getFile(pathToArchive+"picturecandidates.txt");
		ArrayList<String> names = new ArrayList<>();

		while (picturecandidates.hasNext())
			names.add(picturecandidates.nextLine());

		bash.executeCommand("rm "+pathToArchive+"picturecandidates.txt");
		picturecandidates.close();
		return names;
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

	public void writeStats(ArrayList<Picture> pictures){

		for(int i = 0; i < pictures.size(); i++)
			bash.executeCommand("echo '"
					+pictures.get(i).toString()+"' >> ../pictures/pictureStats.txt");

	}

	public void writePictures(ArrayList<Picture> pictures){
		for(int i = 0; i < pictures.size(); i++)
		{
			pictures.get(i).writePixelsToPicture("../pictures/Picture"+(10000)+(i+1)+".jpg");
		}
	}
}
