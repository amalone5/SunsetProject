public class Sort
{
	public Picture[] shellSort(Picture[] unsorted)
	{
		Picture[] output = clonePictures(unsorted);

		int inner, outer;
		int nElems = output.length;
		Picture temp;
		int h = 1;

		while(h <= nElems/3)
			h = h*3 + 1;

		while(h>0)
		{
			for(outer=h; outer<nElems; outer++)
			{
				temp = output[outer];
				inner = outer;

				while(inner > h-1 && !output[inner-h].prettierThan(temp))
				{
					output[inner] = output[inner-h];
					inner -= h;
				}
				output[inner] = temp;
			} 
			h = (h-1) / 3;
		}
		return output;
	}

	public Picture[] clonePictures(Picture[] original)
	{
		int size = original.length;
		Picture[] output = new Picture[size];

		for(int i = 0; i < size; i++)
			output[i] = original[i];

		return output;
	}
}
