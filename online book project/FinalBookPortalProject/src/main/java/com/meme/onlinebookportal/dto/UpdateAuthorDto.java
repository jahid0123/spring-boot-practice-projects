package com.meme.onlinebookportal.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UpdateAuthorDto {
	private Long authorId;
	private String authorName;
	private Long authorNid;
	private String authorBio;
	private String address;
}