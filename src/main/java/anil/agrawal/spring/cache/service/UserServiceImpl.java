package anil.agrawal.spring.cache.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import anil.agrawal.spring.cache.entity.User;
import anil.agrawal.spring.cache.repository.UserRepository;

/**
 * @author anil.agrawal
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	/**
	 * This method used to get User based on username
	 * 
	 * @param username
	 * @return
	 */
	private User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) {
		User user = getUserByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User with username " + username + " not found");
		}

		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getAuthorities());
		grantedAuthorities.add(grantedAuthority);

		return new org.springframework.security.core.userdetails.User(username, user.getPassword(), user.isEnabled(),
				!user.isUserAccountExpired(), !user.isUserPasswordExpired(), !user.isUserAccountLocked(),
				grantedAuthorities);
	}

}