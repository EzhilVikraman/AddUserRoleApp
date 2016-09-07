package com.compass;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class AdminrolesApplication implements CommandLineRunner {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	org.apache.log4j.Logger loggerrr=org.apache.log4j.Logger.getLogger(this.getClass());

	public static void main(String[] args) {
		SpringApplication.run(AdminrolesApplication.class, args);
	}

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;
	


	@Override
	public void run(String... args) throws Exception {
		Role admin = new Role("Admin");
		Role hr = new Role("HR");
		Role developer = new Role("Developer");
		Role tester = new Role("Tester");
		Role android = new Role("Android");

		roleRepository.save(admin);
		roleRepository.save(hr);
		roleRepository.save(developer);
		roleRepository.save(tester);
		roleRepository.save(android);
		

		List<User> users = new LinkedList<User>();
		users.add(
				new User("Vikraman", "Ramakrishnan", "ram@gmail.com","vikraman", "password", Arrays.asList(new Role[] { admin, developer })));
		users.add(new User("Ezhil", "Vikraman", "ezhil@gmail.com", "ezhil","password",Arrays.asList(new Role[] { developer })));
		users.add(new User("Aruna", "Selvam", "aruna@gmail.com","aruna","password", Arrays.asList(new Role[] { hr })));
		users.add(new User("Arpitha", "Rao", "appi@gmail.com", "arpitha","password",Arrays.asList(new Role[] { developer })));
		userRepository.save(users);
		/*logger.info("Info message");
		loggerrr.info("hello");*/
	}
}
