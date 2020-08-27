import java.util.Comparator;

public class StrippedPictureSorter implements Comparator<StrippedPicture>
{
	public int compare(StrippedPicture p1, StrippedPicture p2)
	{
		return p1.compareTo(p2);
	}
}
