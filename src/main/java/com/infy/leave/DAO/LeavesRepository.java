package com.infy.leave.DAO;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.infy.leave.entities.Leave;


@Repository
public interface LeavesRepository extends CrudRepository<Leave, Long> {

	@Query("select distinct l from Leave l join fetch l.user u join u.role r join u.teams t where r.roleLevel < :rolelevel "
	   		+ "and t.teamName in (:teamNames) and l.leaveStatus = :leavestatus")
	   List<Leave> findByUser_teams_teamNameAndUser_roles_roleNameAndLeaveStatus(@Param("teamNames") String[] teams 
			   ,@Param("rolelevel") Integer rolelevel,@Param("leavestatus") String leavestatus );
	
	
	@Query("select distinct l from Leave l join fetch l.user u  where u.userName = :username")
	   List<Leave> findByUser_userName(@Param("username") String username );
	
	@Query("select distinct l from Leave l join l.user u  join u.teams t where "
			+ "t.teamName in (:teamNames) and u.userName <> :username")
	   List<Leave> findByUser_teams_teamNameAndUser_roles_roleName(@Param("teamNames") String[] teams 
			   ,@Param("username") String username);
	
	@Query("select distinct l from Leave l join fetch l.user u  where u.userName = :username and l.startFrom = :startfrom"
			+ " and l.endTo = :endto")
	   Leave findByUser_userNameAndStartFromAndEndTo(@Param("username") String username,
			   @Param("startfrom") Calendar startfrom,@Param("endto") Calendar endto);
	
}
