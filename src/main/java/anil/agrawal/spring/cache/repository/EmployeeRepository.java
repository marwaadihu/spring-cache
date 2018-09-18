package anil.agrawal.spring.cache.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import anil.agrawal.spring.cache.entity.Employee;

/**
 * @author anil.agrawal
 *
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

	@Override
	<S extends Employee> S save(S entity);

	@Override
	Optional<Employee> findById(Long id);

	@Override
	Iterable<Employee> findAll();

	@Override
	void delete(Employee entity);

}
