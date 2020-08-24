import java.util.ArrayList;

public class PictureManager
{
	public String picturePath;
	public Picture[] pictures;

	public PictureManager(String picturePath)
	{
		this.picturePath = picturePath;
		pictures = getPictures();
	}

	public Picture[] getPictures()
	{
		CommandLine bash = new CommandLine();
		ArrayList<String> pictureNames = bash.getFilesFromDirectory(picturePath);
		Picture[] thePictures = new Picture[pictureNames.size()];

		for(int i = 0; i < thePictures.length; i++)
		{
			System.out.println("Generating picture "+i);
			String pictureName = pictureNames.get(i);
			thePictures[i] = new Picture(pictureName);
		}
		return thePictures;
	}

	//TODO make Sort class so program is prettier
	public void bubbleSort() 
	{ 
		int n = pictures.length; 
		for (int i = 0; i < n-1; i++) 
			for (int j = 0; j < n-i-1; j++) 
				if (!pictures[j].prettierThan(pictures[j+1])) 
				{ 
					Picture temp = pictures[j]; 
					pictures[j] = pictures[j+1]; 
					pictures[j+1] = temp; 
				} 
	} 

	//TODO make Sort class so program is prettier
	public void shellSort()
	{
		int inner, outer;
		int nElems = pictures.length;
		Picture temp;
		int h = 1;
		while(h <= nElems/3)
			h = h*3 + 1;
		while(h>0)
		{
			for(outer=h; outer<nElems; outer++)
			{
				temp = pictures[outer];
				inner = outer;

				while(inner > h-1 && !pictures[inner-h].prettierThan(temp))
				{
					pictures[inner] = pictures[inner-h];
					inner -= h;
				}
				pictures[inner] = temp;
			} 
			h = (h-1) / 3;
		} 
	} 

	public void writeTenPrettiest()
	{
		shellSort();

		for(int i = 0; i < 10; i++)
			pictures[i].writePixelsToPicture(pictures[i].getAllPixels(0),"Pretty"+ i +".jpg");

	}

	public void writeAll(){
		shellSort();

		for(int i = 0; i < pictures.length; i++)
			pictures[i].writePixelsToPicture(pictures[i].getAllPixels(0),"Pretty"+(10000+i)+".jpg");

	}
}
