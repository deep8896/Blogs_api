package com.blog_app_apis.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@AllArgsConstructor
public class ApiResponse {
	
	private String message;
	private boolean success;

}
