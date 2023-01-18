package com.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

}
