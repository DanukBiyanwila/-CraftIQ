package com.CraftIQ.CraftIQ.entity;

import jakarta.persistence.*;

@Entity
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;



    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "skill_post_id")
    private SkillPosts skillPost;
}
