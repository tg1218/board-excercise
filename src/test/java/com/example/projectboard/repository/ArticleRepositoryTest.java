package com.example.projectboard.repository;

import com.example.projectboard.config.JpaConfig;
import com.example.projectboard.domain.Article;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(JpaConfig.class)
class ArticleRepositoryTest {

    private ArticleRepository articleRepository;
    private ArticleCommentRepository articleCommentRepository;

    public ArticleRepositoryTest(@Autowired ArticleRepository articleRepository,
                                 @Autowired ArticleCommentRepository articleCommentRepository) {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
    }

    @Test
    @DisplayName("select 테스트")
    public void whenSelect_ThenEmpty(){
        //when
        List<Article> results = articleRepository.findAll();

        //then
        assertThat(results.toArray().length).isEqualTo(0);
    }

    @Test
    @DisplayName("insert 테스트")
    public void givenTestData_whenInsert_ThenWorksFine(){
        //given
        long previousCount = articleRepository.count();

        //when
        articleRepository.save(Article.of(null,"new article", "new content", "#new"));

        //then
        assertThat(previousCount + 1).isEqualTo(1);
    }

    @DisplayName("update 테스트")
    @Test
    void givenTestData_whenUpdating_thenWorksFine() {
        // Given
        articleRepository.save(Article.of(null,"new article", "new content", "#new"));
        Article article = articleRepository.findById(1L).orElseThrow();

        // When
        article.setHashtag("hashtagName");
        Article savedArticle = articleRepository.saveAndFlush(article);

        // Then
        assertThat(savedArticle.getHashtag()).isEqualTo("hashtagName");
    }

    @DisplayName("delete 테스트")
    @Test
    void givenTestData_whenDeleting_thenWorksFine() {
        // Given
        articleRepository.save(Article.of(null,"new article", "new content", "#new"));
        Article article = articleRepository.findById(1L).orElseThrow();
        long previousArticleCount = articleRepository.count();

        // When
        articleRepository.delete(article);

        // Then
        assertThat(articleRepository.count()).isEqualTo(previousArticleCount - 1);
    }
}