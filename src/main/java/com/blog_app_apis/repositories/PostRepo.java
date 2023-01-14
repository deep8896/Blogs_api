package com.blog_app_apis.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.blog_app_apis.entities.Category;
import com.blog_app_apis.entities.Post;
import com.blog_app_apis.entities.User;

public interface PostRepo extends JpaRepository<Post,Integer> {
       
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	//seaching ke liye
	//List <Post> findByTitleContaining(String title);
	
	@Query("select p from Post p where p.title like:key")
	List<Post>searchByTitle(@Param("key")String title);
	
	
}
