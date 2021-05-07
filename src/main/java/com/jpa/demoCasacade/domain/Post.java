package com.jpa.demoCasacade.domain;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.DenyAll;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @CreateBy: Bloo
 * @Date: 2021/05/07
 */

@Getter
@ToString (exclude = "comments")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long Id;
    private String title;
    private String content;

    @OneToMany (
        mappedBy = "post",
        fetch = FetchType.LAZY )
    private final List<Comment> comments = new ArrayList<> ();

    public void addComment ( Comment comment ) {
        comments.add ( comment );
    }
}




