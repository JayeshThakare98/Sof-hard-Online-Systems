package com.Dao;

import com.Exception.EmplException;
import com.Model.Employee;

public interface EmploDao {
	public String registerEmployee(int deptid, String name, String username, String password) throws EmplException;

	public Employee loginEmployee(String username, String password) throws EmplException;




}
