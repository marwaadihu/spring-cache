package anil.agrawal.spring.cache.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import anil.agrawal.spring.cache.entity.Employee;

/**
 * @author anil.agrawal
 *
 */
@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

}
