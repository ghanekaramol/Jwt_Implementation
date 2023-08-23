package jwt.implementaion.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jwt.implementaion.model.User;
import jwt.implementaion.service.UserService;

@RestController
@RequestMapping("/home")
public class HomrController {

	@Autowired
	private UserService userService;

	@GetMapping("/users")
	public List<User> protectedApi()
	{
		return userService.getUser();
	}
	
	@GetMapping("/currentUser")
	public String getLoggedInUser(Principal principal)
	{
		return principal.getName();
	}
	
}
