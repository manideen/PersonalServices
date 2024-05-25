/**
 * 
 */
package org.api.services.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author manig
 *
 */
public class StringUtilities 
{

	public static String regex = "[a-zA-Z]";
	public static List<String> convertStringIntoList(String source)
	{
		return convertStringIntoList(source, " ", true);
	}
	public static List<String> convertStringIntoList(String source, String delimiter)
	{
		return convertStringIntoList(source, delimiter, true);
	}
	public static List<String> convertStringIntoList(String source, String delimiter, boolean isRemoveEmptyStringInElements)
	{
		Pattern pattern = Pattern.compile(regex);
		String[] sourceArray = source.split(delimiter);
		List<String> sourceList = new ArrayList<>();
		boolean isMatch = true;
		for(String sourceArr:sourceArray)
		{
			if(isRemoveEmptyStringInElements)
			{
				Matcher matcher = pattern.matcher(sourceArr);
				isMatch = matcher.find();
			}
			if(isMatch)
			{
				sourceList.add(sourceArr);
			}
		}
		return sourceList;
	}
}
