package Utils;

public class Spliter {
	/**
	 * splitByDelimiter -Splits this string by delimiter and return part of it per given index 
	 * @param val - string to be work on
	 * @param delimiter - by which delimiter to split
	 * @param i - index of the needed result
	 * @return result of the split at specified index
	 */
	public static String splitByDelimiter(String val, String delimiter, int i)
	{
		String[] parts = val.split(" ");
		return parts[i];
	}
}
