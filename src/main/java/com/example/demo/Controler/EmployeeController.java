package com.example.demo.Controler;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Employee;
import com.example.demo.Service.EmployeeService;

@RestController @CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/api")
public class EmployeeController
{

	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployees()
	{
		return employeeService.getAllEmployee();
	}
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value="id")Long employeeId)
	throws Exception
	{
		return employeeService.getEmployeeById(employeeId);
	}
	
	@PostMapping("/employees")
	public Employee createEmployee(@Validated @RequestBody Employee employee)
	{
		return employeeService.createEmployee(employee);
	}
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value="id")Long employeeId,
			@Validated @RequestBody Employee employeeDetails) throws Exception
	{
		return employeeService.updateEmployee(employeeId, employeeDetails);
	}
	
	@DeleteMapping("/employees/{id}")
	public Map<String,Boolean> deleteEmployee(@PathVariable(value="id") Long employeeId)
	throws Exception
	{
		return employeeService.deleteEmployee(employeeId);
	}
}
