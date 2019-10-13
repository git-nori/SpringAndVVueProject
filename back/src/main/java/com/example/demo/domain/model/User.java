package com.example.demo.domain.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Entity
@Table(name="users")
@EntityListeners(AuditingEntityListener.class)
@Data
public class User {
    /* JPA監査アノテーション
     * エンティティを作成or更新するたびに下記のアノテーションをつけたフィールドに値が自動的に入力される
     * @CreatedDate
     * @CreatedBy
     * @UpdatedDate
     * @UpdatedBy
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Idの自動採番
    private long id;

    @Column(name="first_name", nullable=false)
    private String firstName;

    @Column(name="last_name", nullable=false)
    private String lastName;

    @Column(name="email_address", nullable=false)
    private String emailId;

    @Column(name="created_at", nullable=false)
    @CreatedDate
    private Date createdAt;

    @Column(name="created_by", nullable=false)
    @CreatedBy
    private String creeatedBy;

    @Column(name="updated_at", nullable=false)
    @LastModifiedDate
    private Date updatedAt;

    @Column(name="updated_by", nullable=false)
    @LastModifiedBy
    private String updatedBy;
}
