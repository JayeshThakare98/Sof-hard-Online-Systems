package com.Dao;

import com.Exception.ComplaintException;
import com.Exception.EmplException;
import com.Model.Employee;
import com.Model.EngineerComplaintsDTO;

public interface EmploDao {
	public String registerEmployee(int deptid, String name, String username, String password) throws EmplException;

	public Employee loginEmployee(String username, String password) throws EmplException;

	public int raiseComplaint(int empId, String compType) throws ComplaintException;

	public EngineerComplaintsDTO checkComplaintStatus(int complaintId) throws ComplaintException;
	
	
	
}
