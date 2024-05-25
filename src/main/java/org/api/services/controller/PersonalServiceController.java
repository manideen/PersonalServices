/**
 * 
 */
package org.api.services.controller;

import org.api.services.models.UserDetails;
import org.api.services.models.WindowsUsers;
import org.api.services.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Manikandan Elumalai
 *
 */
@RestController
@RequestMapping("/v1/services")
public class PersonalServiceController 
{
	@Autowired
	private UserServices userServices;

	@GetMapping
	public ResponseEntity<String> getAppDetails() 
	{
		return ResponseEntity.ok("Your App is ready to use");
	}

	@GetMapping(value = "/getCurrentUserDetails")
	public ResponseEntity<String> getCurrentUserDetails() 
	{
		StringBuilder userDetailsSB = new StringBuilder("<b><u>UserDetails:</b></u>");
		WindowsUsers windowsUsers = userServices.getWindowsOSUserDetails();
		userDetailsSB.append("<br>").append(windowsUsers.getSystemDescription()).append("<br>");
		int index = 0;
		for(UserDetails userDetails:windowsUsers.getUserDetails())
		{
			userDetailsSB.append("<br><b><u>").append("User ").append(++index).append(":</u></b><br>").append("<b>User Name:</b>").append(userDetails.getUserName()).append("<br>")
			.append("<b>User Type:</b>").append(userDetails.getUserType()).append("<br>");
			if(userDetails.getUserHomeDirecotry() != null)
				userDetailsSB.append("<b>User Home Directory:</b>").append(userDetails.getUserHomeDirecotry()).append("<br>");
			if(userDetails.getUserWorkingDirectory() != null)
				userDetailsSB.append("<b>User's current working Directory:</b>").append(userDetails.getUserWorkingDirectory());
			userDetailsSB.append("<br>");
		}
		return ResponseEntity.ok(userDetailsSB.toString());
	}

	@GetMapping(value="/getCurrentUserInfo")
	public ResponseEntity<WindowsUsers> getCurrentUserInfo()
	{
		/*Map<String, String> user = (Map<String, String>) userDetails.get("UserDetails");
		user.put("UserName", System.getProperty("user.name"));
		user.put("UserHomeDirectory", System.getProperty("user.home"));
		user.put("UserWorkingDirectory", System.getProperty("user.dir"));*/
		
		return ResponseEntity.ok(userServices.getWindowsOSUserDetails());
	}

	@GetMapping(value = "/connectToOpenAI")
	public ResponseEntity<Object> connectToOpenAI(@RequestParam(value = "word", required = true) String word) 
	{
		
		String url = "https://api.openai.com/v1/models";

		return ResponseEntity.ok("Yet to Implement the Service for:" + word);
	}
	
}
