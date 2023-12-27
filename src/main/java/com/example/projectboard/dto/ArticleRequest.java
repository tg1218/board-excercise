package com.example.projectboard.dto;

public record ArticleRequest (
    String title,
    String content,
    String hashtag
){

    public static ArticleRequest of(String title, String content, String hashtag) {
        return new ArticleRequest(title, content, hashtag);
    }


}
