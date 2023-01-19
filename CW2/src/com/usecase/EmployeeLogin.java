package com.usecase;

import java.util.Scanner;

import com.Dao.EmploDao;
import com.Dao.EmploDaoImpl;
import com.Exception.EmplException;
import com.Model.Employee;

public class EmployeeLogin {
	public int loginEmployee() {
		int empId = 0;
		Scanner sc  = new Scanner(System.in);
		System.out.println("Enter Username");
		String username = sc.next();
		System.out.println("Enter Password");
		String password = sc.next();
		System.out.println("==================================");
		
		EmploDao dao = new EmploDaoImpl();
		
		try {
			Employee emp = dao.loginEmployee(username, password);
			System.out.println("Welcome "+ emp.getName());
			System.out.println("======================================");
			empId = emp.getEmpId();
			
		} catch (EmplException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			System.out.println("===========================================");
			loginEmployee();
		}
		
		return empId;
	}
}
