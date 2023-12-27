package com.example.projectboard.dto.request;

import com.example.projectboard.domain.Article;
import com.example.projectboard.domain.ArticleComment;
import com.example.projectboard.domain.UserAccount;
import com.example.projectboard.dto.ArticleCommentDto;
import com.example.projectboard.dto.UserAccountDto;

import java.time.LocalDateTime;

public record ArticleCommentRequest(Long articleId, String content) {

    public static ArticleCommentRequest of(Long articleId, String content) {
        return new ArticleCommentRequest(articleId, content);
    }

    public ArticleCommentDto toDto(UserAccountDto userAccountDto) {
        return ArticleCommentDto.of(
                articleId,
                userAccountDto,
                content
        );
    }

}
