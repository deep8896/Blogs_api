package com.blog_app_apis.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class CategoryDto {
	
	private Long categoryId;
	@NotBlank
	@Size(min=4,message = "Min size of category title is 4")
	private String categoryTitle;
	
	@NotBlank
	@Size(max=10, message = "min size of category desc is 10")
	private String categoryDescription;

}
