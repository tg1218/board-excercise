package com.example.projectboard.controller;

import com.example.projectboard.dto.UserAccountDto;
import com.example.projectboard.dto.request.ArticleCommentRequest;
import com.example.projectboard.dto.security.BoardPrincipal;
import com.example.projectboard.service.ArticleCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comments")
public class ArticleCommentController {

    private final ArticleCommentService articleCommentService;

    @PostMapping("/new")
    public String postNewArticleComment(ArticleCommentRequest request) {
        articleCommentService.saveArticleComment(request.toDto(UserAccountDto.of(
                "tg", "asdf1234",  "tg@mail.com", "tg", "memo",
                null, null, null, null)));

        return "redirect:/articles/" + request.articleId();
    }

    @PostMapping("/{commentId}/delete")
    public String deleteArticleComment(
            @PathVariable(name = "commentId") Long commentId,
            @RequestParam(name = "articleId") Long articleId,
            @AuthenticationPrincipal BoardPrincipal principal) {
        articleCommentService.deleteArticleComment(commentId, principal.username());

        return "redirect:/articles/" + articleId;
    }
}
