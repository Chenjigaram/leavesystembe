package com.infy.leave.DAO;


import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import com.infy.leave.entities.User;

/**
 * Created by Chenjigaram Naveen
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
   User findByUserName(String userName);
   User findByEmpId(Long empId);
   
   @Query("select distinct u from User u join u.role r join u.teams t where r.roleLevel < :rolelevel "
   		+ "and t.teamName in (:teamNames) and u.accountStatus = :accountstatus")
   List<User> findByTeams_teamNameAndRoles_roleNameAndAccountStatus(@Param("teamNames") String[] teams 
		   ,@Param("rolelevel") Integer rolelevel,@Param("accountstatus") String accountStatus );
   
   @Transactional
   @Modifying
   @Query("UPDATE User c SET c.accountStatus = :accountstatus WHERE c.empId = :empid")
   void setAccountStatusforEmpId(@Param("empid") Long empId,@Param("accountstatus") String accountStatus);
   
}
