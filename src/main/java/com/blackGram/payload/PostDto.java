package com.blackGram.payload;

import java.util.Set;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PostDto {
    private long id;

    @NotEmpty
    @Size(min = 2, message = "At least 2 characteres")
    private String title;

    @NotEmpty
    @Size(min = 2, message = "At least 2 characteres")
    private String description;

    @NotEmpty
    private String content;
    private Set<CommentDto> comments;
}
