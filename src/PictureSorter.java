import java.util.Comparator;

public class PictureSorter implements Comparator<Picture>
{
	public int compare(Picture p1, Picture p2)
	{
		return p1.compareTo(p2);
	}

}
