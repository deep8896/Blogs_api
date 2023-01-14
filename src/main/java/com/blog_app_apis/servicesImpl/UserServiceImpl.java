package com.blog_app_apis.servicesImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog_app_apis.entities.User;
import com.blog_app_apis.exceptions.ResourceNotFoundException;
import com.blog_app_apis.payloads.UserDto;
import com.blog_app_apis.repositories.UserRepo;
import com.blog_app_apis.services.UserService;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
	private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.DtoToUser(userDto);
		User savedUser = this.userRepo.save(user);
		
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user1 = this.userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		       user1.setName(userDto.getName());
		       user1.setEmail(userDto.getEmail());
		       user1.setPassword(userDto.getPassword());
		       user1.setAbout(userDto.getAbout());
		       User updateUser = this.userRepo.save(user1);
		       UserDto userDto1 = this.userToDto(updateUser);
		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {
	      User user =  ((Optional<User>) this.userRepo.findById(userId)).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = this.userRepo.findAll();
		List<UserDto> userDtos = users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		 return userDtos; 
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		this.userRepo.delete(user);

	}
       private User DtoToUser(UserDto userDto) {
    	   User user1 = this.modelMapper.map(userDto,User.class);
//    	   User user1  = new User();
//    	   user1.setId(userDto.getId());
//    	   user1.setName(userDto.getName());
//    	   user1.setEmail(userDto.getEmail());
//    	   user1.setPassword(userDto.getPassword());
//    	   user1.setAbout(userDto.getAbout());
    	   
		return user1;
    	   
       }
       public UserDto userToDto(User user) {
    	   UserDto userDto  = this.modelMapper.map(user, UserDto.class);
//    	   UserDto userDto = new UserDto();
//    	   userDto.setId(user.getId());
//    	   userDto.setName(user.getName());
//    	   userDto.setEmail(user.getEmail());
//    	   userDto.setPassword(user.getPassword());
//    	   userDto.setAbout(user.getAbout());
    	   return userDto;
       }

	
}
