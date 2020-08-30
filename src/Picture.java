import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Picture implements Comparable<Picture> {
    public final BufferedImage soul;
    public final String name;

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

		sumRGB();
    }


    public BufferedImage getSoul(String pictureName) {
        if (!isJpgFile(pictureName)) {
            System.out.println(pictureName + "not a jpg, bailing");
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

    public void sumRGB(){
		Color color = null;

        for (int height = 0; height < getHeight(); height += 1)
			for (int width = 0; width < getWidth(); width += 1){
				color = new Color(soul.getRGB(width, height));
				red = red + color.getRed();
				green = green + color.getGreen();
				blue = blue + color.getBlue();
			}
    }

    /*
        Not actually sure why this works. Proceed with
        caution.
    */
    public double getBrightness() {
        double size = getWidth() * getHeight();
        double red = this.red / size;
        double green = this.green / size;
        double blue = this.blue / size;
        double yellow = red + green;

        return yellow * blue;
    }

    public double redBlueRatio() {
        double redDouble = red;
        double blueDouble = blue;
        return redDouble / blueDouble;
    }

    public boolean pictureIsBright() {
        return getBrightness() > 12000;

    }

    public boolean prettierThan(Picture other) {

        if (!this.pictureIsBright())
            return false;

        if (this.pictureIsBright() && !other.pictureIsBright())
            return true;

        return (this.redBlueRatio() > other.redBlueRatio());
    }

    /*
        'this' prettier than other -> 1
        Pictures are the same -> 0
        'this' uglier than other -> -1
     */
    public int compareTo(Picture otherPicture) {

        if (this.prettierThan(otherPicture))
            return 1;
        if (otherPicture.prettierThan(this))
            return -1;

        return 0;
    }

    public void writePixelsToPicture(String pictureName) {
        File outputfile = new File(pictureName);

        try {
            ImageIO.write(soul, "jpg", outputfile);
        } catch (IOException e1) {
            System.out.println("Picture "+this.name+" didn't write to "+pictureName);
        }
    }

    public String toString() {
        return name;
    }

}

