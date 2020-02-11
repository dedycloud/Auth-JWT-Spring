package com.enigma.repository;

import com.enigma.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByUsernameAndPassword(String username, String password);

    Page<User> findAllByRoleLikeOrderByFullName(String role, Pageable pageable);

    User findByUsername(String username);
}
