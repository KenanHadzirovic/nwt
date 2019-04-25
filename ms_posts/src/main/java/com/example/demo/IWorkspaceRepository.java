package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IWorkspaceRepository extends JpaRepository<Workspace, Long> {

    @Query("SELECT w FROM Workspace w WHERE w.ownerid = ?1 and w.isDeleted = 0")
    List<Workspace> getByUserOwnerId(Long userId);
    
    @Query("SELECT w FROM Workspace w")
    List<Workspace> getWorkspaces();
}
