public class ArryFun
{
	public void showIntArray(int[] array)
	{
		System.out.print("[");

		int i;
		for(i = 0; i < array.length-1; i++)
			System.out.print(array[i]+",");

		System.out.print(array[i]+"]");
	}

	/* Hopefully displays any general array */
	public void showGenArray(Object[] array)
	{
		System.out.print("[");
		int i;
		for(i = 0; i < array.length-1; i++)
			if(array[i] != null)
				System.out.print(array[i].toString()+",");


		if(array[i] != null)
			System.out.print(array[i].toString()+"]");
		else
			System.out.print("]");
	}
}
