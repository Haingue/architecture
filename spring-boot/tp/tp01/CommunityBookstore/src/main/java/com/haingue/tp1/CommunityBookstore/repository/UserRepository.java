package com.haingue.tp1.CommunityBookstore.repository;

import com.haingue.tp1.CommunityBookstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
}
