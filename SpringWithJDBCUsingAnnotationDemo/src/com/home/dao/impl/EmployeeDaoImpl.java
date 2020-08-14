package com.home.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.home.dao.EmployeeDao;
import com.home.model.Employee;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void setjdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public void createEmployee(Employee employee) throws SQLException {
		String sql="insert into employee_table(employee_name,email,gender,salary)values(?,?,?,?)";
		int update = jdbcTemplate.update(sql, new Object[] {employee.getEmployeeName(),employee.getEmail(),employee.getGender(),employee.getSalary()});
		if(update>0)
			System.out.println("Employee created successfully!!!");
	}

	@Override
	public Employee getEmployeeById(int employeeId) {
		String sql="select * from employee_table where employee_id=?";
		Employee employee = jdbcTemplate.queryForObject(sql, new EmployeeRowMapper(), employeeId);
		return employee;
	}

	@Override
	public void deleteEmployeeById(int employeeId) {
		String sql="delete from employee_table where employee_id=?";
		int update = jdbcTemplate.update(sql,employeeId);
		if(update>0)
			System.out.println("Employeee deleted successfully!!!");
	}

	@Override
	public void updateEmployeeById(int employeeId, String newEmail) {
		String sql="update employee_table set email=? where employee_id=?";
		int update = jdbcTemplate.update(sql, newEmail,employeeId);
		if(update>0)
			System.out.println("Employeee update successfully!!!");
	}

	@Override
	public List<Employee> getAllEmployees() {
		String sql="select * from employee_table";
		return jdbcTemplate.query(sql, new EmployeeRowMapper());
		 
	}

}
