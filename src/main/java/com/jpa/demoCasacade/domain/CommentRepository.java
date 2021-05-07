package com.jpa.demoCasacade.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @CreateBy: Bloo
 * @Date: 2021/05/07
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
