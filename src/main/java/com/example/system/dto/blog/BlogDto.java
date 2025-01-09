package com.example.system.dto.blog;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BlogDto {
    private String blogName;
    private String blogContent;
    private int blogType;
    private String userEmail;
    private String imgPath;
}
