package com.infy.leave.entities;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Created by Chenjigaram Naveen
 */
@Entity
@Table(name="leave_table")
public class Leave {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="leave_id")
    private Long leaveId;
	
	@Column(name="start_from")
	@Temporal(TemporalType.DATE)
	private Calendar startFrom;
	
	@Column(name="end_to")
	@Temporal(TemporalType.DATE)
	private Calendar endTo;
	
	private String reason;
	
	 @Column(name="leave_status")
	    private String  leaveStatus;
	
	public String getLeaveStatus() {
		return leaveStatus;
	}

	public void setLeaveStatus(String leaveStatus) {
		this.leaveStatus = leaveStatus;
	}

	@ManyToOne
	@JoinColumn(name="emp_id")
	private User user;

	public Long getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(Long leaveId) {
		this.leaveId = leaveId;
	}

	public Calendar getStartFrom() {
		return startFrom;
	}

	public void setStartFrom(Calendar startFrom) {
		this.startFrom = startFrom;
	}

	public Calendar getEndTo() {
		return endTo;
	}

	public void setEndTo(Calendar endTo) {
		this.endTo = endTo;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

}
