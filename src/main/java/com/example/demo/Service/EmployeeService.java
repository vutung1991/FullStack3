package com.example.demo.Service;

import java.util.List;
import java.util.*;
import java.util.Optional;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.Entity.Employee;
import com.example.demo.Repo.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;
	
	public List<Employee> getAllEmployee()
	{
		return employeeRepository.findAll();
	}
	
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value ="id")Long employeeId)
	throws Exception
	{
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(()->new Exception("Employee not found for this id:"+employeeId));
		return ResponseEntity.ok().body(employee);
	}
	
	public Employee createEmployee(@Validated @RequestBody Employee employee)
	{
		return employeeRepository.save(employee);
	}
	
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value="id")Long employeeId,
			@Validated @RequestBody Employee employeeDetails) throws Exception
	{
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(()-> new Exception("Employee not found for this id :"+employeeId));
		
		employee.setEmail(employeeDetails.getEmail());
		employee.setLastName(employeeDetails.getLastName());
		employee.setFirstName(employeeDetails.getFirstName());
	
		
		final Employee updateEmployee = employeeRepository.save(employee);
		return ResponseEntity.ok(updateEmployee);
	}
	
	public Map<String,Boolean> deleteEmployee(@PathVariable(value="id")Long employeeId) throws Exception
	{
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(()->new Exception("Employee Not found for this id:"+employeeId));
		
		employeeRepository.delete(employee);
		Map<String,Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
		
	}
}
