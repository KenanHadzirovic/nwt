package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.List;

@Repository
public interface IPostRepository extends JpaRepository<Post, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE Post p SET p.isDeleted = 1 WHERE p.workspace.id = ?1")
    void removeByWorkspaceId(Long workspaceId);
}
