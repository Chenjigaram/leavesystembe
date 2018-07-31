package com.infy.leave.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.infy.leave.DAO.LeavesRepository;
import com.infy.leave.DAO.RoleRepository;
import com.infy.leave.beans.Message;
import com.infy.leave.beans.RoleANDTeam;
import com.infy.leave.entities.Leave;
import com.infy.leave.entities.Role;
import com.infy.leave.exceptions.CustomErrorType;
import com.infy.leave.services.LeaveServiceImpl;
import com.infy.leave.utilities.AppLogger;



/** 
 * @author chenjigaram Naveen
 *
 */
@RestController
@RequestMapping("leavesRequest")
public class LeaveController {

	@Autowired
	private LeavesRepository leavesRepository;
	@Autowired
	private LeaveServiceImpl leaveServiceImpl;
	@Autowired
	private RoleRepository roleRepository;
	
	@RequestMapping(value = "/getallApprovalLeavesReq", method = RequestMethod.POST)
	public List<Leave> getallApprovalLeavesRequests(@RequestBody RoleANDTeam  roleANDteam) {
		
	List<Leave> leaves =null;
			try {
				Role role =roleRepository.findByRoleName(roleANDteam.getRoleNames()[0]);
				leaves=	leavesRepository.findByUser_teams_teamNameAndUser_roles_roleNameAndLeaveStatus
						(roleANDteam.getTeamNames(),role.getRoleLevel(),  "Applied");
			}catch(Exception e) {
				e.printStackTrace();
				AppLogger.logError("LeaveController", "getallApprovalLeavesRequests", e.getLocalizedMessage());
			}
		return leaves;
	}
	
	@RequestMapping(value = "/getallMyLeavesReq", method = RequestMethod.POST)
	public List<Leave> getallMyLeavesRequests(@RequestBody String  userName) {
		
	List<Leave> leaves =null;
			try {
			 
				leaves=	leavesRepository.findByUser_userName(userName);
			}catch(Exception e) {
				
				AppLogger.logError("LeaveController", "getallMyLeavesRequests", e.getLocalizedMessage());
			}
		return leaves;
	}
	
	@RequestMapping(value = "/getallTeamLeavesReq/{userName}", method = RequestMethod.POST)
	public List<Leave> getallTeamLeavesRequests(@RequestBody RoleANDTeam  roleANDteam ,@PathVariable(value="userName") String userName) {
		
	List<Leave> leaves =null;
			try {
			 
				leaves=	leavesRepository.findByUser_teams_teamNameAndUser_roles_roleName
						(roleANDteam.getTeamNames(),userName);
			}catch(Exception e) {
				
				AppLogger.logError("LeaveController", "getallTeamLeavesRequests", e.getLocalizedMessage());
			}
		return leaves;
	}
	
	@RequestMapping(value="/updateLeave",method=RequestMethod.POST)
	public Message updateLeave(@RequestBody Leave leave) {
		Message message =new Message();
		try {
			leaveServiceImpl.updateLeave(leave);
		message.setStatus(true);
		message.setMessage("Leave updated Sucessfully");
		}catch(Exception | CustomErrorType e) {
			message.setStatus(false);
			message.setMessage(e.getLocalizedMessage());
			AppLogger.logError("LeaveController", "updateLeave", e.getLocalizedMessage());
		}
		return message;
		
	}
	
	

	
	@RequestMapping(value="/deleteLeave",method=RequestMethod.POST)
	public Message deleteLeave(@RequestBody Leave leave) {
		
		Message message =new Message();
		try {
			leavesRepository.delete(leave);
		message.setStatus(true);
		message.setMessage("Leave deleted Sucessfully");
		}catch(Exception e) {
			message.setStatus(false);
			message.setMessage(e.getLocalizedMessage());
			AppLogger.logError("LeaveController", "deleteLeave", e.getLocalizedMessage());
		}
		return message;
		
	}
	@RequestMapping(value="/addLeave",method=RequestMethod.POST)
	public Message saveLeave(@RequestBody Leave leave) {
		
		Message message =new Message();
		try {
			
			leaveServiceImpl.saveLeave(leave);
		message.setStatus(true);
		message.setMessage("leave request added Sucessfully");
		}catch(Exception | CustomErrorType e) {
			message.setStatus(false);
			message.setMessage(e.getLocalizedMessage());
			AppLogger.logError("LeaveController", "saveLeave", e.getLocalizedMessage());
		}
		return message;
		
	}
	
}
