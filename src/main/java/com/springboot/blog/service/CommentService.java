package com.springboot.blog.service;

import java.util.List;

import com.springboot.blog.payload.CommentDto;

public interface CommentService {

	public CommentDto createComment(Long postId, CommentDto commentDto);

	List<CommentDto> getCommentsByPostId(Long postId);

	public CommentDto getCommentById(Long postId, Long commentId);

	public CommentDto updateComment(Long postId, Long commnetId, CommentDto commentDto);
	
	public void deleteComment(Long postId, Long commentId);

}
