package com.springboot.blog.payload;

import java.util.Set;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "PostDto Model Information")
public class PostDto {
	private Long id;

	@Schema(description = "Blog Post Title")
//    title should not be null o empty
//    title should have at least 2 characters
	@NotEmpty
	@Size(min = 2, message = "Post Title should have at least 2 characters")
	private String title;

	@Schema(description = "Blog Post Description")
//  post description should be not null or empty
//  post description should at least 10 character
	@NotEmpty
	@Size(min = 10, message = "Post description should have at least 10 characters")
	private String description;

	@Schema(description = "Blog Post Content")
//	post content should not be null or empty
	@NotEmpty(message = "must not be empty")
	private String content;
	private Set<CommentDto> comments;

	@Schema(description = "Post Category")
	private Long categoryId;
}