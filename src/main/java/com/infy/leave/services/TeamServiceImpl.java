package com.infy.leave.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.leave.DAO.TeamRepository;
import com.infy.leave.entities.Team;
/** 
 * @author Chenjigaram Naveen
 *
 */
@Service
public class TeamServiceImpl {

	
	@Autowired
	private TeamRepository teamRepository;
	
	public List<Team> getAllTeams(){
		
		return (List<Team>) teamRepository.findAll();
	}
}
