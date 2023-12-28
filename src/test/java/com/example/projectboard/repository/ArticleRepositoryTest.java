package com.example.projectboard.repository;

import com.example.projectboard.config.JpaConfig;
import com.example.projectboard.domain.Article;
import com.example.projectboard.domain.UserAccount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("JPA 연결 테스트")
@DataJpaTest
class ArticleRepositoryTest {

    private ArticleRepository articleRepository;
    private UserAccountRepository userAccountRepository;

    public ArticleRepositoryTest(@Autowired ArticleRepository articleRepository,
                                 @Autowired UserAccountRepository userAccountRepository) {
        this.articleRepository = articleRepository;
        this.userAccountRepository = userAccountRepository;
    }

    @Test
    @DisplayName("select 테스트")
    public void whenSelect_ThenEmpty(){
        //when
        List<Article> results = articleRepository.findAll();

        //then
        assertThat(results.toArray().length).isEqualTo(123);
    }

    @Test
    @DisplayName("insert 테스트")
    public void givenTestData_whenInsert_ThenWorksFine(){
        //given
        long previousCount = articleRepository.count();
        UserAccount user = userAccountRepository.getReferenceById("tg");
        //when
        articleRepository.save(Article.of(user,"new article", "new content", "#new"));

        //then
        assertThat(previousCount + 1).isEqualTo(articleRepository.count());
    }

    @DisplayName("update 테스트")
    @Test
    void givenTestData_whenUpdating_thenWorksFine() {
        // Given
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
        Article article = articleRepository.findById(1L).orElseThrow();
        long previousArticleCount = articleRepository.count();

        // When
        articleRepository.delete(article);

        // Then
        assertThat(articleRepository.count()).isEqualTo(previousArticleCount - 1);
    }

    @EnableJpaAuditing
    @TestConfiguration
    public static class TestJpaConfig {
        @Bean
        public AuditorAware<String> auditorAware() {
            return () -> Optional.of("tg");
        }
    }
}