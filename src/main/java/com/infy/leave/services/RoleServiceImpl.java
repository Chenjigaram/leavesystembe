package com.infy.leave.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.leave.DAO.RoleRepository;
import com.infy.leave.entities.Role;

/** 
 * @author Chenjigaram Naveen
 *
 */
@Service
public class RoleServiceImpl {

	@Autowired
	private RoleRepository roleRepository;
	
	public List<Role> getAllRoles(){
		
		return (List<Role>) roleRepository.findAll();
	}
	
}
