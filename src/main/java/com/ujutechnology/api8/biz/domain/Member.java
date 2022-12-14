package com.ujutechnology.api8.biz.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author kei
 * @since 2022-08-24
 */
@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
@DynamicUpdate
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String name;

    @Column(nullable = false)
    private String role;

    private String token;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime modifiedDate;

    private String profilePhoto;
    private String job;
    private int age;
    private int credit;
    private int point;

    @Builder
    public Member(Long id, String email, String password, String name, String role, String token, LocalDateTime createdDate, LocalDateTime modifiedDate, String profilePhoto, String job, int age, int credit, int point) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
        this.token = token;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.profilePhoto = profilePhoto;
        this.job = job;
        this.age = age;
        this.credit = credit;
        this.point = point;
    }

    public void setValidCredit(int credit) {
        this.credit = Math.min(1000, this.credit+Math.abs(credit));
    }
}
