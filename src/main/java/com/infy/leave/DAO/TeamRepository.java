package com.infy.leave.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.infy.leave.entities.Team;
/**
 * Created by Chenjigaram Naveen
 */
@Repository
public interface TeamRepository extends CrudRepository<Team, Long> {

}
