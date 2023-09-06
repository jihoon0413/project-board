package com.example.projectboard.service;

import com.example.projectboard.domain.Article;
import com.example.projectboard.dto.ArticleCommentDto;
import com.example.projectboard.repository.ArticleCommentRepository;
import com.example.projectboard.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DisplayName("비즈니스 로직 - 댓글")
@ExtendWith(MockitoExtension.class)
class ArticleCommentServiceTest {
    @InjectMocks private ArticleCommentService sut;
    @Mock private ArticleCommentRepository articleCommentRepository;
    @Mock private ArticleRepository articleRepository;

//    @DisplayName("댓글 정보를 입력하면 댓글을 저장한다.")
//    @Test
//    void given_when_then() {
//
//        Long articleId = 1L;
//
//        BDDMockito.given(articleRepository.findById(articleId)).willReturn(Optional.of(Article.of("title", "content", "#java")));
//
//        List<ArticleCommentDto> articleComments = sut.searchArticleComment(articleId);
//
//        assertThat(articleComments).isNotNull();
//
//        BDDMockito.then(articleRepository).should().findById(articleId);
//    }


}