package com.example.projectboard.repository;

import com.example.projectboard.config.JpaConfig;
import com.example.projectboard.domain.Article;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("JPA 연결 테스트")
@Import(JpaConfig.class)
@DataJpaTest
class JpaRepositoryTest {

    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;

    public JpaRepositoryTest(
            @Autowired ArticleRepository articleRepository,
            @Autowired ArticleCommentRepository articleCommentRepository) {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
    }
    @DisplayName("select 테스트")
    @Test
    void givenTestData_whenSelection_thenWorksFine(){

       articleRepository.save(Article.of("new article", "new content", "new hashtag"));

//       Article article = articleRepository.findById(1L);
       List<Article> articles = articleRepository.findAll();
       Article article = articles.get(0);

       String updatedHashtag = "#springboot";

       article.setHashtag(updatedHashtag);

       Article savedArticle = articleRepository.saveAndFlush(article);


//        List<Article> articles = articleRepository.findAll();

        assertThat(savedArticle).hasFieldOrPropertyWithValue("hashtag", updatedHashtag);

//        assertThat(articles)
//                .isNotNull()
//                .hasSize(1);

    }


}