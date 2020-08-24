import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Picture
{
	public BufferedImage soul;
	public String name;

	public int redScore;
	public int greenScore;
	public int blueScore;

	public int darknessTopHalfPicture;

	/*
	 * This works under the assumption that the fully
	 * qualified name is provided to the program.
	 */
	public Picture(String pictureName)
	{
		soul = getSoul(pictureName);
		name = pictureName;

		if(soul != null)
		{
			redScore = sumRed();
			greenScore = sumGreen();
			blueScore = sumBlue();

//			darknessTopHalfPicture = sumTopHalf();
			//yellowScore = sumYellow();
		}
	}

	public Picture(int width, int height)
	{
		writeSolidPixels(width,height);

	}

	public BufferedImage getSoul(String pictureName)
	{
		if(!isJpgFile(pictureName))
			return null;

		File input = new File(pictureName);
		BufferedImage soul = null;
		try {
			soul = ImageIO.read(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return soul;
	}

	public String getExtension(String pictureName)
	{
		int dotIndex = pictureName.indexOf(".");
		int startIndex = dotIndex+1;
		int endIndex = pictureName.length();
		return pictureName.substring(startIndex,endIndex);
	}

	public boolean isJpgFile(String pictureName)
	{
		String extension = getExtension(pictureName);
		return extension.equals("jpg");
	}

	public void rename(String newName)
	{
		CommandLine bash = new CommandLine();
		bash.executeBashCommand("mv "+name+" "+newName);
	}

	public int getWidth()
	{
		return soul.getWidth();
	}

	public int getHeight()
	{
		return soul.getHeight();
	}

	public void showPixelAtIndex(int width, int height)
	{
		Color c = new Color(soul.getRGB(width, height));
		System.out.println("Pixel ("+width+", "+height+"): " + "(" + c.getRed() +"," + c.getGreen() + "," + c.getBlue()+")");
	}

	public void printPictureStats()
	{
		for(int h = 0; h < getHeight(); h++)
			for(int w = 0; w < getWidth(); w++)
				showPixelAtIndex(w,h);
	}

	public int sumRedAtHeight(int height)
	{
		int sum = 0;
		Color c;
		for(int i = 0; i < getWidth(); i++)
		{
			c = new Color(soul.getRGB(i, height));
			sum = sum + c.getRed();
		}
		return sum;
	}

	public int sumGreenAtHeight(int height)
	{
		int sum = 0;
		Color c;
		for(int i = 0; i < getWidth(); i++)
		{
			c = new Color(soul.getRGB(i, height));
			sum = sum + c.getGreen();
		}
		return sum;
	}

	public int sumBlueAtHeight(int height)
	{
		int sum = 0;
		Color c;
		for(int i = 0; i < getWidth(); i++)
		{
			c = new Color(soul.getRGB(i, height));
			sum = sum + c.getBlue();
		}
		return sum;
	}

	public int sumRed()
	{
		int sum = 0;
		for(int i = 0; i < getHeight(); i++)
			sum = sum + sumRedAtHeight(i);

		return sum;
	}

	public int sumRedBottomHalf(){
		int sum = 0;
		for(int i = getHeight()/2; i < getHeight(); i++)
			sum = sum + sumRedAtHeight(i);

		return sum;
	}

	public int sumTopHalf()
	{
		int sum = 0;
		for(int i = 0; i < getHeight() / 2; i++)
		{
			int colorsum = 
				sumRedAtHeight(i) +
				sumGreenAtHeight(i) +
				sumBlueAtHeight(i); 

			sum = sum + colorsum;
		}
		return sum;
	}

	public int sumGreen()
	{
		int sum = 0;
		for(int i = 0; i < getHeight(); i++)
			sum = sum + sumGreenAtHeight(i);

		return sum;
	}

	public int sumBlue()
	{
		int sum = 0;
		for(int i = 0; i < getHeight(); i++)
			sum = sum + sumBlueAtHeight(i);

		return sum;
	}

	public int sumYellow()
	{
		return sumRed() + sumGreen();
	}


	/* Here I am defining "skew" to be what makes 
	 * sunsets pretty. Lots of color variation towards
	 * the red-yellow end of the color spectrum gives
	 * a row of a picture a higher score. 
	 * 
	 * Note: This doesn't work at all.
	 */
	public int calculateSkewAtHeight(int height)
	{
		int redSkew = sumRedAtHeight(height);
		int greenSkew = sumGreenAtHeight(height);
		int blueSkew = sumBlueAtHeight(height);

		int warmSkew = redSkew + greenSkew;

		return (warmSkew - blueSkew) ;
	}

//	public int prettyScore()
//	{
//		int score = 0;
//		for(int height = 0; height < getHeight(); height++)
//			score = score + sumRedAtHeight(height);
//
//		return score;
//	}
//

	public double redBlueRatioProduct()
	{
		int size = getWidth() * getHeight();
		double red = redScore / size;
		double green = greenScore / size;
		double blue = blueScore / size;

		double yellow = red + green;

		return yellow*blue;
	}
	public int prettyScore()
	{
		return redScore - darknessTopHalfPicture;
	}
	public boolean prettierThan(Picture other)
	{
		if(other == null)
			return true;
		if(this == null)
			return false;
		//int thisScore = this.prettyScore();
		//int otherScore = other.prettyScore();

		if(this.redBlueRatioProduct() >= other.redBlueRatioProduct())
			return true;

		return false;
	}


	public int[][] getRedPixels(int threshold)
	{
		int[][] pixels = new int[getWidth()][getHeight()];

		for(int w = 0; w < getWidth(); w++)
			for(int h = 0; h < getHeight(); h++)
			{
				Color color = new Color(soul.getRGB(w,h));
				Color red = null;

				if(threshold <= color.getRed())
					red = new Color(color.getRed(),0,0);
				else 
					red = new Color(255,255,255);

				pixels[w][h] = red.getRGB();
			}
		return pixels;
	}

	public int[][] getAllPixels(int threshold)
	{
		int[][] pixels = new int[getWidth()][getHeight()];
		Color white = new Color(255,255,255);

		for(int w = 0; w < getWidth(); w++)
			for(int h = 0; h < getHeight(); h++)
			{
				Color color = new Color(soul.getRGB(w,h));

				if( (threshold <= color.getRed()) || 
					(threshold <= color.getGreen()) ||
					(threshold <= color.getBlue()) )
						pixels[w][h] = color.getRGB();
				else
						pixels[w][h] = white.getRGB();
			}

		return pixels;
	}


	public int[][] makeSolidPixels(int width, int height)
	{
		int[][] pixels = new int[width][height];

		Color color = new Color(255,255,0);

		for(int w = 0; w < width; w++)
			for(int h = 0; h < height; h++)
				pixels[w][h] = color.getRGB();


		return pixels;
	}

	public void writeSolidPixels(int width, int height)
	{
		File outputfile = new File("testpicture.jpg");
		BufferedImage image = new BufferedImage(width ,height , BufferedImage.TYPE_INT_RGB);
		int[][] solidpixels = makeSolidPixels(width,height); 

		for(int w = 0; w < width; w++)
			for(int h = 0; h < height; h++)
				image.setRGB(w,h,solidpixels[w][h]);

		try 
		{
			ImageIO.write(image, "jpg", outputfile);
		}
		catch (IOException e1) 
		{
			System.out.println("Something went wrong!");
		}


	}

	public void writePixelsToPicture(int[][] pixels, String pictureName)
	{
		File outputfile = new File(pictureName);
		BufferedImage image = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_RGB);

		for(int w = 0; w < getWidth(); w++)
			for(int h = 0; h < getHeight(); h++)
				image.setRGB(w,h,pixels[w][h]);

		try 
		{
			ImageIO.write(image, "jpg", outputfile);
		}
		catch (IOException e1) 
		{
			System.out.println("Something went wrong!");
		}
		
	}

	public int sumRGB(Color someColor)
	{
		int red = someColor.getRed();
		int green = someColor.getGreen();
		int blue = someColor.getBlue();
		return red + green + blue;
	}

	public Point getBrightestPoint()
	{
		Color brightest = new Color(soul.getRGB(0,0));
		Point point = new Point(0,0);

		for(int w = 0; w < getWidth(); w++)
			for(int h = 0; h < getHeight(); h++)
			{
				Color next = new Color(soul.getRGB(w,h));
				int sumNext = sumRGB(next);
				int sumBrightest = sumRGB(brightest);

				if(sumNext > sumBrightest)
				{
					brightest = next;
					point = new Point(w,h);
				}

			}
		return point;
	}

	public int getColorsAtThreshold(int threshold)
	{
		int count = 0;

		for(int w = 0; w < getWidth(); w++)
			for(int h = 0; h < getHeight(); h++)
			{
				Color color = new Color(soul.getRGB(w,h));

				if( (threshold <= color.getRed()) || 
					(threshold <= color.getGreen()) ||
					(threshold <= color.getBlue()) )
				{
					count ++;
				}
			}
		return count;
	}

	public String chart(int input, int output)
	{
		String line = "\n"+input + ": ";
		for(int i = 0; i < output; i += 1908)
			line = line + "*";

		return line;

	}


	public void intensityHistogram()
	{
		String histogram = "";
		for(int threshold = 255; threshold >= 0; threshold --)
		{
			int colorsAtThreshold = getColorsAtThreshold(threshold);
			histogram = histogram + chart(threshold, colorsAtThreshold);
		}
		CommandLine bash = new CommandLine();
		bash.executeBashCommand("echo '"+histogram+"' > "+name +"_histogram.txt");
	}
}


