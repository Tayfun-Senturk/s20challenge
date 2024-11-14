package com.workintech.s20challenge.repository;

import com.workintech.s20challenge.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    @Query("SELECT m From Member m WHERE m.email=:email")
    Optional<Member> findByEmail(String email);
}
