package com.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.Exception.ComplaintException;
import com.Exception.EmplException;
import com.Model.Employee;
import com.Util.DBUtil;

public class EmploDaoImpl implements EmploDao{

	@Override
	public String registerEmployee(int deptid, String name, String username, String password) throws EmplException {
		String Msg = "Somthing wentwrong";
		
try(Connection conn = DBUtil.provideConnection()) {
			
			PreparedStatement ps = conn.prepareStatement("insert into employee (deptid,name,username,password)"
					+ "values(?,?,?,?)");
			ps.setInt(1, deptid);
			ps.setString(2, name);
			ps.setString(3, username);
			ps.setString(4, password);
			
			int x = ps.executeUpdate();
			if(x>0) {Msg = "Registration Successfull.";
			}else {
				throw new EmplException("Registration failed. please check the inputs .");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new EmplException(e.getMessage());
		}		
		
		return Msg ; 
	}

	@Override
	public Employee loginEmployee(String username, String password) throws EmplException {
Employee emp = new Employee();
		
		try(Connection conn = DBUtil.provideConnection()) {
			
			PreparedStatement ps = conn.prepareStatement("select * from employee where username=?"
					+ "AND password = ?");
			
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				emp.setEmpId(rs.getInt("empId"));
				emp.setDeptid(rs.getInt("deptId"));
				emp.setName(rs.getString("name"));
				emp.setUserName(rs.getString("username"));
				emp.setPassword(rs.getString("password"));
			}else {
				throw new EmplException("Invalid username or password");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new EmplException(e.getMessage());
		}
		
		return emp;
	}

	@Override
	public int raiseComplaint(int empId, String compType) throws ComplaintException {
int complaintId = 0;
		
		try(Connection conn = DBUtil.provideConnection()) {
			
			PreparedStatement ps = conn.prepareStatement("insert into complaints (complaintId,"
					+ "empId, complaintType,status, dateRaised) values(?,?,?,?,?)");
			
			complaintId = (int) (Math.random()*10000);
			LocalDate dateRaised = LocalDate.now();
			
			ps.setInt(1, complaintId);
			ps.setInt(2, empId);
			ps.setString(3, compType);
			ps.setString(4, "Raised");
			ps.setDate(5, java.sql.Date.valueOf(dateRaised));
			
			int x = ps.executeUpdate();
			
			if(x>0) {
				System.out.println("Complaint raised successfully");
			}else {
				throw new ComplaintException("Complaint could not be raised. Please try again.");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ComplaintException(e.getMessage());
		}
		
		return complaintId;
	}

	
}
