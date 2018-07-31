package com.infy.leave.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.infy.leave.DAO.RoleRepository;
import com.infy.leave.DAO.UserRepository;
import com.infy.leave.beans.Message;
import com.infy.leave.beans.RoleANDTeam;
import com.infy.leave.entities.Role;
import com.infy.leave.entities.User;
import com.infy.leave.utilities.AppLogger;

/** 
 * @author chenjigaram Naveen
 *
 */
@RestController
@RequestMapping("accessRequest")
public class AccessController {

	
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	
	@RequestMapping(value = "/getaccessRequests", method = RequestMethod.POST)
	public List<User> accessRequest(@RequestBody RoleANDTeam  roleANDteam) {
		
	List<User> users =null;
			try {
			 
				Role role =roleRepository.findByRoleName(roleANDteam.getRoleNames()[0]);
				users=	userRepository.
				findByTeams_teamNameAndRoles_roleNameAndAccountStatus(roleANDteam.getTeamNames(),role.getRoleLevel()
						,  "InActive");
			}catch(Exception e) {
				
				AppLogger.logError("AccessController", "accessRequest", e.getLocalizedMessage());
			}
		return users;
	}
	
	@RequestMapping(value="/updateUser",method=RequestMethod.POST)
	public Message updateUser(@RequestBody User user) {
		Message message =new Message();
		try {
		userRepository.setAccountStatusforEmpId(user.getEmpId(), "Active");
		message.setStatus(true);
		message.setMessage("User updated Sucessfully");
		}catch(Exception e) {
			message.setStatus(false);
			message.setMessage(e.getLocalizedMessage());
			AppLogger.logError("AccessController", "updateUser", e.getLocalizedMessage());
		}
		return message;
		
	}
	
	
	@RequestMapping(value="/rejectUser",method=RequestMethod.POST)
	public Message rejectUser(@RequestBody User user) {
		
		Message message =new Message();
		try {
		userRepository.setAccountStatusforEmpId(user.getEmpId(), "Rejected");
		message.setStatus(true);
		message.setMessage("User updated Sucessfully");
		}catch(Exception e) {
			message.setStatus(false);
			message.setMessage(e.getLocalizedMessage());
			AppLogger.logError("AccessController", "rejectUser", e.getLocalizedMessage());
		}
		return message;
		
	}
	
	@RequestMapping(value="/deleteUser",method=RequestMethod.POST)
	public Message deleteUser(@RequestBody User user) {
		
		Message message =new Message();
		try {
		userRepository.delete(user);
		message.setStatus(true);
		message.setMessage("User deleted Sucessfully");
		}catch(Exception e) {
			message.setStatus(false);
			message.setMessage(e.getLocalizedMessage());
			AppLogger.logError("AccessController", "deleteUser", e.getLocalizedMessage());
		}
		return message;
		
	}
	
}
