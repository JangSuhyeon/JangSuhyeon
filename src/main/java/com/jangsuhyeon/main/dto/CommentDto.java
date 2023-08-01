package com.jangsuhyeon.main.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class CommentDto {

    private String cmtFrmName;
    private String cmtFrmPassword;
    private String cmtFrmComment;

}
