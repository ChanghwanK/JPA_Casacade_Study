package com.jpa.demoCasacade.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @CreateBy: Bloo
 * @Date: 2021/05/07
 */

@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Comment {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long Id;
    private String title;
    private String content;

    // TODO: 2021.05.07 -Blue
    // Comment 쪽에서 Casacade 를 걸면 어떻게 될까 ?
    @ManyToOne (cascade = CascadeType.REMOVE)
    private Post post;

    public void createRelationWithPost ( Post post ) {
        this.post = post;
        post.addComment ( this );
    }
}



