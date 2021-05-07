package com.jpa.demoCasacade.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.print.Book;
import java.util.Optional;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

/**
 * @CreateBy: Bloo
 * @Date: 2021/05/07
 */

@SpringBootTest
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    private Post post;

    private Comment comment;


    @BeforeEach
    void setUp () {
        post = Post.builder ()
            .title ( "Test 입니다." )
            .content ( "Test Post" )
            .build ();

        comment = Comment.builder ()
            .title ( "댓글 제목" )
            .content ( "댓글 테스트" )
            .build ();
    }

    @DisplayName ("PERSIST 를 테스트 한다")
    @Test
    void savePost () {
        /**
         * [ 메모 ]
         *  - Comment 와 Post 를 서로 연관관계를 설정 후 PERSIST 옵셥은 적용 후 Post 를 Save 해본다.
         */

        // when
        comment.createRelationWithPost ( post );
        postRepository.save ( post );

        // then
        assertThat ( post.getComments ().get ( 0 ).getTitle ()).isEqualTo ( comment.getTitle () );

    }


    @DisplayName ("연관관계 설정을 하고 REMOVE 테스트를 실행한다.")
    @Test
    void removePostAndComment () {
       // postRepository.deleteById ( 1L );

        Optional<Post> post = postRepository.findById ( 1L );

        post.ifPresent ( selectedPost -> {
            postRepository.delete ( selectedPost );
        } );

        Optional<Post> deletePost = postRepository.findById ( 1L );
        Assertions.assertFalse ( deletePost.isPresent () );
    }

    @DisplayName ("Comment 에서 Casacade.PERSIST 를 걸어 저장한다.")
    @Test
    void Comment_쪽에서_Casacade_적용하기  () {
        comment.createRelationWithPost ( post );
        commentRepository.save ( comment );

        Optional<Comment> comment = commentRepository.findById ( 1L );
        assertTrue ( comment.isPresent () );
    }


    @DisplayName ("기존에 있는 Post에 새롭게 댓글을 추가한다.")
    @Test
    void Comment_쪽에서_Casacade_적용  () {
        // postRepository.save ( post );
        Post savedPost = postRepository.findPostUseFetchJoin ( 1L );
        assertThat ( savedPost.getTitle () ).isEqualTo ( post.getTitle () );

    }

    @DisplayName ("comment 에서 삭제 요청 하면 POST도 지워진다.")
    @Test
    void comment_측에서_삭제요청 () {
        // given
//        comment.createRelationWithPost ( post );
//        commentRepository.save ( comment );

        // when
         commentRepository.deleteById ( 2L );
        // then
         Optional<Post> post = postRepository.findById ( 1L );

         assertFalse ( post.isPresent () );
    }
}