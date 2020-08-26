import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Picture {
    public final BufferedImage soul;
    public final String name;
    public double prettyScore;

	public int red;
	public int green;
	public int blue;

    /*
     * This works under the assumption that the fully
     * qualified name is provided to the program.
     */
    public Picture(String pictureName) {
        soul = getSoul(pictureName);
        name = pictureName;

		red = sumRed();
		green = sumGreen();
		blue = sumBlue();

        prettyScore = getPrettyScore();
    }


    public BufferedImage getSoul(String pictureName) {
        if (!isJpgFile(pictureName))
		{
			System.out.println(pictureName+"not a jpg, bailing");
			System.exit(1);
		}

        File input = new File(pictureName);
        BufferedImage soul = null;
        try {
            soul = ImageIO.read(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return soul;
    }

    public int getLastDot(String fileName) {
        int i;
        for (i = fileName.length() - 1; i >= 0; i--)
            if (fileName.charAt(i) == '.')
                break;

        return i;
    }

    public String getExtension(String pictureName) {
        int dotIndex = getLastDot(pictureName);
        int startIndex = dotIndex + 1;
        int endIndex = pictureName.length();
        return pictureName.substring(startIndex, endIndex);
    }

    public boolean isJpgFile(String pictureName) {
        String extension = getExtension(pictureName);
        return extension.equals("jpg");
    }

    public int getWidth() {
        return soul.getWidth();
    }

    public int getHeight() {
        return soul.getHeight();
    }

    public int sumRedAtHeight(int height) {
        int sum = 0;
        Color c;
        for (int i = 0; i < getWidth(); i++) {
            c = new Color(soul.getRGB(i, height));
            sum = sum + c.getRed();
        }
        return sum;
    }

    public int sumRed() {
        int sum = 0;
        for (int i = 0; i < getHeight(); i++)
            sum = sum + sumRedAtHeight(i);

        return sum;
    }

    public int sumGreenAtHeight(int height) {
        int sum = 0;
        Color c;
        for (int i = 0; i < getWidth(); i++) {
            c = new Color(soul.getRGB(i, height));
            sum = sum + c.getGreen();
        }
        return sum;
    }

    public int sumGreen() {
        int sum = 0;
        for (int i = 0; i < getHeight(); i++)
            sum = sum + sumGreenAtHeight(i);

        return sum;
    }

    public int sumBlueAtHeight(int height) {
        int sum = 0;
        Color c;
        for (int i = 0; i < getWidth(); i++) {
            c = new Color(soul.getRGB(i, height));
            sum = sum + c.getBlue();
        }
        return sum;
    }

    public int sumBlue() {
        int sum = 0;
        for (int i = 0; i < getHeight(); i++)
            sum = sum + sumBlueAtHeight(i);

        return sum;
    }

    public double redBlueRatioProduct() {
        double size = getWidth() * getHeight();
        double red = this.red / size;
        double green = this.green / size;
        double blue = this.blue / size;
        double yellow = red + green;

        return yellow * blue;
    }

    /*
     * Zero is a perfect score, higher score is uglier
     */
    public double getPrettyScore() {
        double rbrp = redBlueRatioProduct();
        rbrp = rbrp - 18000;
        rbrp = Math.abs(rbrp);
        return rbrp;
    }

	public double redBlueRatio()
	{
		double redDouble = red;
		double blueDouble = blue;
		double ratio = redDouble/blueDouble;
		return ratio;
	}

	public boolean pictureIsBright()
	{
		return redBlueRatioProduct() > 12000;

	}

    public boolean prettierThan(Picture other) {

		if(!this.pictureIsBright())
			return false;

		if(this.pictureIsBright() && !other.pictureIsBright())
			return true;
		
		return (this.redBlueRatio() > other.redBlueRatio());
    }


    public void writePrettyScore() {
        Bash bash = new Bash();
		String infostring = "Score: " + prettyScore;
		infostring = infostring + " | rbrp: " + redBlueRatioProduct();
		infostring = infostring + " | R:"+red+" G:"+green+" B:"+blue;
        bash.executeCommand("echo '" + infostring + "' >> ../pictures/pictureinfo.txt");
        System.out.println();
    }

    public int[][] getAllPixels() {
        int[][] pixels = new int[getWidth()][getHeight()];
        Color color;

        for (int w = 0; w < getWidth(); w++)
            for (int h = 0; h < getHeight(); h++) {
                color = new Color(soul.getRGB(w, h));
                pixels[w][h] = color.getRGB();
            }
        return pixels;
    }

    public void writePixelsToPicture(int[][] pixels, String pictureName) {
        File outputfile = new File(pictureName);
        BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);

        for (int w = 0; w < getWidth(); w++)
            for (int h = 0; h < getHeight(); h++)
                image.setRGB(w, h, pixels[w][h]);

        try {
            ImageIO.write(image, "jpg", outputfile);
        } catch (IOException e1) {
            System.out.println("Something went wrong!");
        }
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
		Bash bash = new Bash();
		bash.executeCommand("echo '"+histogram+"' > ../histograms/xxx"+name +"_histogram.txt");
	}


    public String toString() {
        return name;
    }
}


