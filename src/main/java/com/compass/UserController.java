package com.compass;

import java.util.List;

import org.picketlink.idm.credential.encoder.BCryptPasswordEncoder;
import org.picketlink.idm.credential.util.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
	/* public static boolean checkpw(String plaintext, String hashed) {
		      return (hashed.compareTo(hashpw(plaintext, hashed)) == 0);
		 }*/
	 /*public static boolean checkpw(String plaintext, String hashed) {
	        return (hashed.compareTo((plaintext, hashed)) == 0);
	    }*/

	

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;
	
	/*@Autowired
	Encoder encoder;*/
	

	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(){
		
		
		return "login";
		
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String authenticateLoginPage(@RequestParam String username, @RequestParam String password){
		BCryptPasswordEncoder b=new BCryptPasswordEncoder(1);
		User user = userRepository.findByUsername(username);
		String pass=user.getPassword();
		//String pw_hash = BCrypt.hashpw(password	, BCrypt.gensalt()); 
		String usname=user.getUsername();
		//verify(password, pass));
		//if(BCrypt.checkpw(password.toString(), pw_hash));
		System.out.println(pass);
		System.out.println(usname);
		System.out.println(password);
		System.out.println(username);
		//System.out.println(pw_hash);
		
		if(username.equalsIgnoreCase(usname) && password.equalsIgnoreCase(pass)){
			return "redirect:/dashboard";
		}
		
		else 
		{
			return "redirect:/error";
		}
	}

	@RequestMapping(value = "/dashboard")
	public String dashBoardMethod() {

		return "dashboard";

	}

	@RequestMapping(value = "/showroles", method = RequestMethod.GET)
	public String showRoleMethod(Model model) {
		model.addAttribute("roles", roleRepository.findAll());

		return "showroles";

	}

	@RequestMapping(value = "/showusers", method = RequestMethod.GET)
	public String showUserMethod(Model model) {
		model.addAttribute("users", userRepository.findAll());
		model.addAttribute("roles", roleRepository.findAll());
		return "showusers";

	}

	@RequestMapping(value = "/addrole", method = RequestMethod.GET)
	public String addRoleMethod() {

		return "addrole";

	}

	@RequestMapping(value = "/addrole", method = RequestMethod.POST)
	public String addRoleMethod(@RequestParam String rolename) {
		Role role = new Role();
		role.setRolename(rolename);
		roleRepository.save(role);

		return "redirect:/showroles";
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String developersList(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "users";

	}

	@RequestMapping(value = "/user/{id}")
	public String developer(@PathVariable Long id, Model model) 
	{
		model.addAttribute("user", userRepository.findOne(id));
		model.addAttribute("roles", roleRepository.findAll());
		return "user";
	}

	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public String developersAdd(@RequestParam String email, @RequestParam String firstName,
			@RequestParam String lastName,@RequestParam String username, @RequestParam String password,  Model model) {
		User newUser = new User();
		//String pw_hash = BCrypt.hashpw(password	, BCrypt.gensalt()); 
		
		newUser.setEmail(email);
		newUser.setFirstName(firstName);
		newUser.setLastName(lastName);
		newUser.setUsername(username);
		newUser.setPassword(password);
		userRepository.save(newUser);

		model.addAttribute("user", newUser);
		model.addAttribute("roles", roleRepository.findAll());
		return "redirect:/user/" + newUser.getId();
	}

	@RequestMapping(value = "/user/{id}/roles", method = RequestMethod.POST)
	public String developersAddSkill(@PathVariable Long id, @RequestParam Long roleId, Model model) {
		Role role = roleRepository.findOne(roleId);
		User user = userRepository.findOne(id);

		if (user != null) {
			if (!user.hasRole(role)) {
				user.getRoles().add(role);
			}
			userRepository.save(user);
			model.addAttribute("user", userRepository.findOne(id));
			model.addAttribute("roles", roleRepository.findAll());
			model.addAttribute("users", userRepository.findAll());
			return "redirect:/showusers";
		}

		model.addAttribute("users", userRepository.findAll());
		return "redirect:/users";
	}
	
	@RequestMapping(value="/showusers/{id}/delete", method = RequestMethod.GET)
	public String deleteUser(@PathVariable Long id, Model model)
	{
		User user = userRepository.findOne(id);
		userRepository.delete(user); 
		model.addAttribute("users", userRepository.findAll());
		model.addAttribute("roles", roleRepository.findAll());
		return "redirect:/showusers";
	}
	@RequestMapping(value="/showrole/{id}/delete", method = RequestMethod.GET)
	public String deleteRole(@PathVariable Long id, Model model)
	{
		Role role = roleRepository.findOne(id);
		roleRepository.delete(role);
		return "redirect:/showroles";
	}
	
	@RequestMapping(value = "/showusers/{username}", method = RequestMethod.GET)
	public String showUserMethoda(Model model, @PathVariable String username) {
		System.out.println(username);
		Role role = roleRepository.findByUsername(username);
		System.out.println(role.getRolename());
		model.addAttribute("users", userRepository.findAll());
		model.addAttribute("roles", roleRepository.findAll());
		return "showusers";

	}
	

}
