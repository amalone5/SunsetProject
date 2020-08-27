
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

    /*
        'this' prettier than other -> 1
        Pictures are the same -> 0
        'this' uglier than other -> -1
     */
    public int compareTo(StrippedPicture otherPicture) {

        if (this.prettierThan(otherPicture))
            return 1;
        if (otherPicture.prettierThan(this))
            return -1;

        return 0;
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
