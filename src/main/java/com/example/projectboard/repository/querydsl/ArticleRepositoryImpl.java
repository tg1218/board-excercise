package com.example.projectboard.repository.querydsl;

import com.example.projectboard.domain.QArticle;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import java.util.List;

import static com.example.projectboard.domain.QArticle.*;

public class ArticleRepositoryImpl implements ArticleRepositoryCustom {

    private final JPAQueryFactory query;

    public ArticleRepositoryImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public List<String> findAllDistinctHashtags() {
        return query.select(
                    article.hashtag
                )
                .distinct()
                .from(article)
                .where(
                        article.hashtag.isNotNull()
                )
                .fetch();
    }
}
