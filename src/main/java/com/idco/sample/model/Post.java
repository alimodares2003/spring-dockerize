package com.idco.sample.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ReadOnlyProperty
    private long id;
    @CreationTimestamp
    private LocalDateTime cdt;
    @UpdateTimestamp
    private LocalDateTime udt;

    private String title;
    private String content;

    public Post(long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Post() {

    }
}
