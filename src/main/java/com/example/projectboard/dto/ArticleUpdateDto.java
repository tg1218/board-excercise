package com.example.projectboard.dto;

import com.example.projectboard.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ArticleUpdateDto {

    private String title;
    private String content;
    private String hashtag;

    public static ArticleUpdateDto from(Article entity) {
        return new ArticleUpdateDto(
                entity.getTitle(),
                entity.getContent(),
                entity.getHashtag()
        );
    }

    public static ArticleUpdateDto of(String title, String content, String hashtag) {
        return new ArticleUpdateDto(title, content, hashtag);
    }
}
