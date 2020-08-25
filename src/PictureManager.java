import java.util.ArrayList;

public class PictureManager {
    public String picturePath;
    public Picture[] pictures;

    public PictureManager(String picturePath) {
        this.picturePath = picturePath;
        Sort sort = new Sort();
        pictures = sort.shellSort(getPictures());
    }

    public Picture[] getPictures() {
        Bash bash = new Bash();
        ArrayList<String> pictureNames = bash.getFilesFromDirectory(picturePath);
        Picture[] thePictures = new Picture[pictureNames.size()];

        for (int i = 0; i < thePictures.length; i++) {
            System.out.println("Generating picture " + i);
            String pictureName = pictureNames.get(i);
            thePictures[i] = new Picture(pictureName);
        }
        return thePictures;
    }

    public void writeTenPrettiest() {
        for (int i = 0; i < 10; i++)
            pictures[i].writePixelsToPicture(pictures[i].getAllPixels(), "../pictures/Pretty" + i + ".jpg");
    }

    public void writeAll() {
        for (int i = 0; i < pictures.length; i++)
            pictures[i].writePixelsToPicture(pictures[i].getAllPixels(), "../pictures/Pretty" + (10000 + i + 1) + ".jpg");

    }

    public void writeScores() {
        for (int i = 0; i < pictures.length; i++)
            pictures[i].writePrettyScore();
    }

    public String toString() {
        String output = "[";
        int i;
        for (i = 0; i < pictures.length - 1; i++)
            output = output + pictures[i].toString() + ", ";

        return output;
    }
}
