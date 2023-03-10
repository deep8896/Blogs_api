package com.blog_app_apis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog_app_apis.payloads.JwtAuthRequest;
import com.blog_app_apis.payloads.JwtAuthResponse;
import com.blog_app_apis.security.JwtTokenHelper;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	@Autowired
	private UserDetailsService userDEtailsService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception{
		System.out.println(request);
		System.out.println("controller me request aa rahi hai");
		this.authenticate(request.getUsername(),request.getPassword());

	/*	UserDetails userDetails = this.userDEtailsService.loadUserByUsername(request.getPassword());
		String token = this.jwtTokenHelper.generateToken(userDetails);
		JwtAuthResponse response = new JwtAuthResponse();
		response.setToken(token);
		return new ResponseEntity<JwtAuthResponse>(response, OK);
		*/

		UserDetails userDetails = this.userDEtailsService.loadUserByUsername(request.getUsername());
		String token = this.jwtTokenHelper.generateToken(userDetails);
		System.out.println(token);
		JwtAuthResponse response = new JwtAuthResponse();


		response.setToken(token);
		return  new ResponseEntity<JwtAuthResponse>(response,OK);
		
	}
	private void authenticate(String username, String password) throws Exception {

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

		try {

			this.authenticationManager.authenticate(authenticationToken);
		}catch (BadCredentialsException e){

			System.out.println("Invailed details  !!");
			
			throw new Exception ("Invalid username or password");

		}





	}
}
