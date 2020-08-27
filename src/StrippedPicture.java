
public class StrippedPicture 
{
	public String name;
	public boolean pictureIsBright;
	public double redBlueRatio;

	public StrippedPicture(String name, boolean pictureIsBright, double redBlueRatio)
	{
		this.name = name;
		this.pictureIsBright = pictureIsBright;
		this.redBlueRatio = redBlueRatio;
	}

    public boolean prettierThan(StrippedPicture other) {

		if(!this.pictureIsBright)
			return false;

		if(this.pictureIsBright && !other.pictureIsBright)
			return true;
		
		return (this.redBlueRatio > other.redBlueRatio);
    }

	public void copyTo(String otherName)
	{
		Bash bash = new Bash();
		bash.executeCommand("cp "+ name + " " + otherName);
	}

	public String toString()
	{
		String output = 
			name +"\n" + 
			pictureIsBright + "\n" + 
			redBlueRatio;

		return output;
	}

}
