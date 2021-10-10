package com.atm.emulator.services.implementations;



import com.atm.emulator.model.user.UserEntity;
import com.atm.emulator.repository.UserRepository;
import com.atm.emulator.services.interfaces.UserService;
import com.atm.emulator.utils.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {


	@Autowired
	UserRepository userRepo;

	@Autowired
	PasswordEncoder passwordEncoder;




	public List<UserEntity> getAll() {
		return userRepo.findAll();
	}

	@Override
	public boolean userExist(UserEntity user) {
		return userRepo.existsByUsername(user.getUsername());
	}

	@Override
	public Optional<UserEntity> findUserByUserName(String userName) {
		return userRepo.findByUsername(userName);
	}

	@Override
	public UserEntity findLoggedInUser(Principal principal) {
		// TODO Auto-generated method stub
		return findUserByUserName(principal.getName()).get();
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return this.findUserByUserName(username).get();
	}



	@Override
	public UserEntity saveUser(UserEntity user, String password) {
		PasswordGenerator passwordGenerator = new PasswordGenerator.PasswordGeneratorBuilder()
				.useDigits(true)
				.useLower(true)
				.useUpper(true)
				.build();
		return userRepo.save(user);
	}
}

	

        
        
        

	


