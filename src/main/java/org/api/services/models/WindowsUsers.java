/**
 * 
 */
package org.api.services.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * @author manig
 *
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WindowsUsers 
{
	String systemDescription;
	List<UserDetails> userDetails;

}
