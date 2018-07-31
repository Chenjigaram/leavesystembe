package com.infy.leave.controller;



/** 
 * @author chenjigaram Naveen
 *
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.infy.leave.beans.Message;
import com.infy.leave.entities.Role;
import com.infy.leave.entities.Team;
import com.infy.leave.entities.User;
import com.infy.leave.exceptions.CustomErrorType;
import com.infy.leave.services.RoleServiceImpl;
import com.infy.leave.services.TeamServiceImpl;
import com.infy.leave.services.UserServiceImpl;
import com.infy.leave.utilities.AppLogger;
@RestController
@RequestMapping("signup")
public class SignUpController {
	
	@Autowired
	private RoleServiceImpl roleService;
	@Autowired
	private TeamServiceImpl teamService;
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@RequestMapping(value="/allTeams",method=RequestMethod.GET)
	public List<Team> getAllTeams(){
		
		return teamService.getAllTeams();
	}
	@RequestMapping(value="/allRoles",method=RequestMethod.GET)
	public List<Role> getAllRoles(){
		
		return roleService.getAllRoles();
	}
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public Message register(@RequestBody User user) {
		Message message =null;
		try {
			message = new Message();
			if(userServiceImpl.find(user.getEmpId())!=null || userServiceImpl.find(user.getUserName())!=null) {
				
				throw new CustomErrorType("User Already Exists with EMP Id");
			}
		user.setAccountStatus("InActive");
		userServiceImpl.save(user);
		message.setStatus(true);
		message.setMessage("Registration Completed");
		AppLogger.loginfo("SignUpController", "register",user.toString());
		}catch(Exception | CustomErrorType e) {
			AppLogger.logError("SignUpController", "register", e.getLocalizedMessage());
			message.setStatus(false);
			message.setMessage(e.getMessage());
		}
		return message;
		
	}
	
	@RequestMapping(value="/getUser",method=RequestMethod.POST)
	public User getUser(@RequestBody Long empId) {
		User user = null;
		try {
			user= userServiceImpl.find(empId);
		}catch(Exception e) {
			AppLogger.logError("SignUpController", "getUser for empID : "+empId, e.getLocalizedMessage());
			
		}
		return user;
	
	}
	
	@RequestMapping(value="/updateUser",method=RequestMethod.POST)
	public Message updateUser(@RequestBody User user) {
		
		Message message =null;
		try {
			message = new Message();
		user.setAccountStatus("InActive");
		userServiceImpl.save(user);
		message.setStatus(true);
		message.setMessage("Update Completed");
		AppLogger.loginfo("SignUpController", "updateUser",user.toString());
		}catch(Exception e) {
			AppLogger.logError("SignUpController", "register", e.getLocalizedMessage());
			message.setStatus(false);
			message.setMessage(e.getLocalizedMessage());
		}
		return message;
		
	}
	
	
	
}
