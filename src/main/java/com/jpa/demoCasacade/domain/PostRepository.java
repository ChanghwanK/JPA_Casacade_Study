package com.jpa.demoCasacade.domain;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @CreateBy: Bloo
 * @Date: 2021/05/07
 */

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query ( "select p from Post p join fetch p.comments" )
    Post findPostUseFetchJoin (Long id);
}
