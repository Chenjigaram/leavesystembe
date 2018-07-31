package com.infy.leave.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.leave.DAO.LeavesRepository;
import com.infy.leave.DAO.UserRepository;

import com.infy.leave.entities.Leave;
import com.infy.leave.entities.User;
import com.infy.leave.exceptions.CustomErrorType;

@Service
public class LeaveServiceImpl {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private LeavesRepository leaveRepository;
	
	
	public void saveLeave(Leave leave) throws CustomErrorType {
		
		
		
		if(leaveRepository.findByUser_userNameAndStartFromAndEndTo(leave.getUser().getUserName(),
				leave.getStartFrom(), leave.getEndTo())==null) {
		if(leave.getEndTo().equals(leave.getStartFrom()) || leave.getEndTo().after(leave.getStartFrom())) {
		User user =userRepository.findByUserName(leave.getUser().getUserName());
			leave.setUser(user);
			leave.setLeaveStatus("Applied");
			leaveRepository.save(leave);
			
		}else{
			throw new CustomErrorType("Check start from and to dates");
			
		}}else {
			
			throw new CustomErrorType("Already request exists with same details");
		}
			
		
	}
	
	
	public void updateLeave(Leave leave) throws CustomErrorType {
		if(leaveRepository.findByUser_userNameAndStartFromAndEndTo(leave.getUser().getUserName(),
				leave.getStartFrom(), leave.getEndTo())==null) {
		if(leave.getEndTo().equals(leave.getStartFrom()) || leave.getEndTo().after(leave.getStartFrom())) {
		
			leaveRepository.save(leave);
			
		}else{
			throw new CustomErrorType("Check start from and to dates");
			
		}}else {
			
			throw new CustomErrorType("Already request exists with same details");
		}
	} 
	
}
