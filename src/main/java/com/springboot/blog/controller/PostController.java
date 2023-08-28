package com.springboot.blog.controller;

import static com.springboot.blog.utils.AppConstants.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.service.PostService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/posts")
@Tag(name = "CRUD REST APIs for Post Resource")
public class PostController {

	@Autowired
	private PostService postServicie;

	@Operation(summary = "Create Post REST API", description = "Create Post REST API is used to save post into database")
	@ApiResponse(responseCode = "201", description = "Http Status 201 CREATED")
	@SecurityRequirement(name = "Bear Authentication")
	// create posts rest api
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto) {
		return new ResponseEntity<>(postServicie.createPost(postDto), HttpStatus.CREATED);
	}

	@Operation(summary = "Get All Posts REST API", description = "Get All Posts REST API is used to fetch all the posts from the database")
	@ApiResponse(responseCode = "200", description = "Http Status 200 SUCCESS")
	@GetMapping
	public PostResponse getAllPost(
			@RequestParam(value = "pageNo", defaultValue = DEFAULT_PAGE_NUMBER, required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE, required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = DEFAULT_SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = DEFAULT_SORT_DIR, required = false) String sortDir) {
		return postServicie.getAllPost(pageNo, pageSize, sortBy, sortDir);
	}

	@Operation(summary = "Get Post By Id REST API", description = "Get Post By Id REST API is used to get single post from the database")
	@ApiResponse(responseCode = "200", description = "Http Status 200 SUCCESS")
	// get post by id
	@GetMapping("/{id}")
	public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") Long id) {
		return ResponseEntity.ok(postServicie.getPostById(id));
	}

	@Operation(summary = "Update Post REST API", description = "Update Post REST API is used to update a particular post in the database")
	@ApiResponse(responseCode = "200", description = "Http Status 200 SUCCESS")
	@SecurityRequirement(name = "Bear Authentication")
	// update post by id rest api
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable(name = "id") Long id) {
		return new ResponseEntity<>(postServicie.updatePost(postDto, id), HttpStatus.GONE);
	}

	@Operation(summary = "Delete Post REST API", description = "Delete Post REST API is used to delete a particular post from the database")
	@ApiResponse(responseCode = "200", description = "Http Status 200 SUCCESS")
	@SecurityRequirement(name = "Bear Authentication")
	// delete post rest api
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePost(@PathVariable(name = "id") Long id) {
		postServicie.deletePost(id);
		return new ResponseEntity<>("Post entity deleted successfully.", HttpStatus.OK);
	}

	// Build Get Post by Category REST API
	@GetMapping("/category/{id}")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable("id") Long categoryId) {
		List<PostDto> posts = postServicie.getPostsByCategory(categoryId);
		return ResponseEntity.ok(posts);
	}

}
