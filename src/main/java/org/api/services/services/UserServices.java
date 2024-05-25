/**
 * 
 */
package org.api.services.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.api.services.models.UserDetails;
import org.api.services.models.WindowsUsers;
import org.api.services.util.StringUtilities;
import org.springframework.stereotype.Service;

/**
 * @author manig
 *
 */
@Service
public class UserServices 
{
	public WindowsUsers getWindowsOSUserDetails()
	{
		WindowsUsers windowsUsers = null;
		try
		{
			Pattern pattern = Pattern.compile(StringUtilities.regex);
			ProcessBuilder processBuilder = new ProcessBuilder("net","user");
			processBuilder.redirectErrorStream(true);
			Process process = processBuilder.start();
			windowsUsers = new WindowsUsers();
			List<UserDetails> userDetailsList = new ArrayList<>();
			Scanner scanner = new Scanner(process.getInputStream());
			int linesCount=0;
			List<String> userType = new ArrayList<>();
			String currentUser = System.getProperty("user.name");
			while(scanner.hasNext())
			{
				String line = scanner.nextLine();
				Matcher matcher = pattern.matcher(line);
				if(matcher.find())
				{				
					switch(linesCount)
					{
					case 1:
					{
						windowsUsers.setSystemDescription(line);
					}
					break;
					case 4:
					{
						userType.addAll(StringUtilities.convertStringIntoList(line, " ", true));
					}
					break;
					case 5:
					{
						List<String> userNames = StringUtilities.convertStringIntoList(line, " ", true);
						int index = 0;
						UserDetails userDetails = null;
						for(String userName:userNames)
						{
							userDetails = new UserDetails();
							userDetails.setUserName(userName);
							userDetails.setUserType(userType.get(index));
							index++;
							if(currentUser.equals(userName))
							{
								userDetails.setUserHomeDirecotry(System.getProperty("user.home"));
								userDetails.setUserWorkingDirectory(System.getProperty("user.dir"));
							}
							userDetailsList.add(userDetails);
						}
						windowsUsers.setUserDetails(userDetailsList);
					}
					break;
					default:
					{
						System.out.println(line);
					}
					break;
					}
				}
				linesCount++;
			}
			scanner.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return windowsUsers;
	}
}
