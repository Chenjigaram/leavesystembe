package com.infy.leave.controller;




import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;




/** 
 * @author chenjigaram Naveen
 *
 */
@RestController
@RequestMapping("home")
public class HomeController {

	
	
	@RequestMapping(value="/test",method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('MANAGER_USER')")
	public String testMethod(){
		
		return "test";
	}
	
	
}
