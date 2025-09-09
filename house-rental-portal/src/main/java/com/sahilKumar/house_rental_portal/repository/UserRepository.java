package com.sahilKumar.house_rental_portal.repository;

import com.sahilKumar.house_rental_portal.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a user by their email address. Spring Data JPA automatically
     * creates the query for us based on the method name "findByEmail".
     *
     * @param email the email to search for
     * @return an Optional containing the User if found, or an empty Optional otherwise.
     */
    Optional<User> findByEmail(String email);
}