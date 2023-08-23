package jwt.implementaion.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jwt.implementaion.model.JwtRequest;
import jwt.implementaion.model.JwtResponse;
import jwt.implementaion.security.JwtHelper;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserDetailsService service;

	@Autowired
	private AuthenticationManager manager;

	@Autowired
	private JwtHelper helper;


	private Logger logger = LoggerFactory.getLogger(AuthController.class);

	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request)
	{
		doAuthenticate(request.getEmail(), request.getPassword());

		UserDetails userdetails = service.loadUserByUsername(request.getEmail());
		String token = helper.generateToken(userdetails);

		JwtResponse response = new JwtResponse();;
		response.setJwtToken(token);
		response.setUsername(userdetails.getUsername());

		return new ResponseEntity<>(response,HttpStatus.OK);
	}


	private void doAuthenticate(String email, String password) {

		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(email,password);
		try {
			manager.authenticate(auth);
		}
		catch (BadCredentialsException e) {
			throw new BadCredentialsException(" Invalid Username or Password  !!");
		}
	}

	@ExceptionHandler(BadCredentialsException.class)
	public String exceptionHandler() {
		return "Credentials Invalid !!";
	}

}
