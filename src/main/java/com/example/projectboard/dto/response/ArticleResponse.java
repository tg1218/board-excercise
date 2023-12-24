package com.example.projectboard.dto.response;

import com.example.projectboard.dto.ArticleDto;

import java.time.LocalDateTime;
import java.util.Set;

public record ArticleResponse(
        Long id,
        String title,
        String content,
        Set<String> hashtags,
        LocalDateTime createdAt,
        String email,
        String nickname
) {

    public static ArticleResponse of(Long id, String title, String content, Set<String> hashtags, LocalDateTime createdAt, String email, String nickname) {
        return new ArticleResponse(id, title, content, hashtags, createdAt, email, nickname);
    }

    public static ArticleResponse from(ArticleDto dto) {
        return new ArticleResponse(
                dto.getId(),
                dto.getTitle(),
                dto.getContent(),
                Set.of(dto.getHashtag()),
                dto.getCreatedAt(),
                "tg1218.kim@gmail.com",
                "testNickName"
        );
    }
}
