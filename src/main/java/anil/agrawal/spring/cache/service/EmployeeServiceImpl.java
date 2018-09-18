package anil.agrawal.spring.cache.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import anil.agrawal.spring.cache.entity.Employee;
import anil.agrawal.spring.cache.repository.EmployeeRepository;

/**
 * @author anil.agrawal
 *
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

	private static final String DATABASE_CALL = "--> database call <--";

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	private EmployeeRepository employeeRepository;

	/**
	 * Constructor
	 * 
	 * @param employeeRepository
	 */
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	@Transactional
	@CacheEvict(value = "employee", allEntries = true)
	@CachePut(value = "employeeId", key = "#employee.id")
	public Employee addEmployee(Employee employee) {
		return save(employee);
	}

	private Employee save(Employee employee) {
		LOGGER.info(DATABASE_CALL);
		return employeeRepository.save(employee);
	}

	@Override
	@Transactional
	@Cacheable(value = "employeeId", key = "#employeeId")
	public Employee getEmployee(Long employeeId) {
		LOGGER.info(DATABASE_CALL);
		Optional<Employee> findById = employeeRepository.findById(employeeId);
		return findById.isPresent() ? findById.get() : null;
	}

	@Override
	@Transactional
	@CacheEvict(value = "employee", allEntries = true)
	@CachePut(value = "employeeId", key = "#employee.id")
	public Employee updateEmployee(Employee requestBody, Employee employee) {
		requestBody.setId(employee.getId());
		return save(requestBody);
	}

	@Override
	@Transactional
	@Cacheable(value = "employee")
	public List<Employee> listEmployee() {
		List<Employee> listOfEmployee = new ArrayList<>();
		LOGGER.info(DATABASE_CALL);
		Iterator<Employee> iterator = employeeRepository.findAll().iterator();
		while (iterator.hasNext()) {
			listOfEmployee.add(iterator.next());
		}
		return listOfEmployee;
	}

	@Override
	@Transactional
	@Caching(evict = { @CacheEvict(value = "employee", allEntries = true),
			@CacheEvict(value = "employeeId", key = "#employee.id") })
	public void deleteEmployee(Employee employee) {
		LOGGER.info(DATABASE_CALL);
		employeeRepository.delete(employee);
	}

}