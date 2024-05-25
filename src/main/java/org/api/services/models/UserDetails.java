/**
 * 
 */
package org.api.services.models;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * @author manig
 *
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDetails 
{
	String userName;
	String userType;
	String userHomeDirecotry;
	String userWorkingDirectory;
}
