package com.infy.leave.entities;

import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Description of User.
 * 
 * @author Chenjigaram Naveen
 */
@Entity
@Table(name = "app_users")
public class User {
	@Id
	@Column(name = "emp_id")
	private Long empId;
	/**
	 * Description of the property employee email.
	 */
	@Column(unique = true, name = "username")
	private String userName;
	/**
	 * Description of the property First Name.
	 */
	@Column(name = "first_name")
	private String firstName;
	/**
	 * Description of the property Last Name.
	 */
	@Column(name = "last_name")
	private String lastName;

	/**
	 * Description of the property password.
	 */
	
	private String password;
	/**
	 * Description of the property role , to grant authority to the user .
	 */
	@ManyToOne
	@JoinColumn(name="role_id")
	private Role role;

	/**
	 * Description of the property Team names.
	 */
	@ManyToMany(fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinTable(name = "user_teams", joinColumns = @JoinColumn(name = "emp_id", referencedColumnName = "emp_id"), inverseJoinColumns = @JoinColumn(name = "team_id", referencedColumnName = "team_id"))
	private List<Team> teams;

	/**
	 * Description of the property Account Status.
	 */
	@Column(name = "account_status")
	private String accountStatus;

	@Override
	public String toString() {
		return "User [id=" + empId + ", username=" + userName + ", "+ ", role=" + role.getRoleName()
				+ "teamame=" + teams.size() + " ]";
	}

	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@JsonIgnore
	@JsonProperty(value = "user_password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

}
