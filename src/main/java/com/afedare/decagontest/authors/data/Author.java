package com.afedare.decagontest.authors.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Author {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("username")
    private String username;
    
    @JsonProperty("about")
    private String about;

    @JsonProperty("submitted")
    private Long submitted;

    @JsonProperty("updated_at")
    private String updatedAt;

    @JsonProperty("submission_count")
    private Long submissionCount;

    @JsonProperty("comment_count")
    private Long commentCount;

    @JsonProperty("created_at")
    private Long createdAt;
}