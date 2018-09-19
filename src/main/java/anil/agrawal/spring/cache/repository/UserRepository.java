package anil.agrawal.spring.cache.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import anil.agrawal.spring.cache.entity.User;

/**
 * @author anil.agrawal
 *
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface UserRepository extends CrudRepository<User, Long> {

	/**
	 * This method used to get User based on username
	 * 
	 * @param username
	 * @return
	 */
	public User findByUsername(String username);

}
