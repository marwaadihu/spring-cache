package anil.agrawal.spring.cache.service;

import java.util.List;

import org.springframework.stereotype.Service;

import anil.agrawal.spring.cache.entity.Employee;

/**
 * @author anil.agrawal
 *
 */
@Service
public interface EmployeeService {

	/**
	 * This method used to add employee
	 * 
	 * @param employee
	 * @return
	 */
	public Employee addEmployee(Employee employee);

	/**
	 * This method used to get employee
	 * 
	 * @param employeeId
	 * @return
	 */
	public Employee getEmployee(Long employeeId);

	/**
	 * This method used to update employee
	 * 
	 * @param requestBody
	 * @param employee
	 * @return
	 */
	public Employee updateEmployee(Employee requestBody, Employee employee);

	/**
	 * This method used to list employees
	 * 
	 * @return
	 */
	public List<Employee> listEmployee();

	/**
	 * This method used to delete employee
	 * 
	 * @param employee
	 */
	public void deleteEmployee(Employee employee);

}
