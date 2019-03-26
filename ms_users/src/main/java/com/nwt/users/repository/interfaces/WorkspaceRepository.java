package com.nwt.users.repository.interfaces;

import com.nwt.users.entities.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkspaceRepository extends JpaRepository<Workspace, Long> {

    @Query("SELECT w FROM Workspace w WHERE w.owner.id = ?1 and w.isDeleted = 0")
    List<Workspace> getByUserOwnerId(Long userId);
}
