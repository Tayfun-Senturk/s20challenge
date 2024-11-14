package com.workintech.s20challenge.service;

import com.workintech.s20challenge.entity.Member;
import com.workintech.s20challenge.entity.Role;
import com.workintech.s20challenge.repository.MemberRepository;
import com.workintech.s20challenge.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class AuthenticationService {

    private static final String ROLE_USER = "USER";
    private static final String ROLE_ADMIN = "ADMIN";
    private final MemberRepository memberRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public Member register(String email, String password, int auth) {
        if (memberRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("User with given email already exists.");
        }

        List<Role> roleList = new ArrayList<>();

        if (auth==780) {
            addRoleAdmin(roleList);
        } else {
            addRoleUser(roleList);
        }

        Member member = new Member();
        member.setEmail(email);
        member.setPassword(passwordEncoder.encode(password));
        member.setRoles(roleList);

        return memberRepository.save(member);
    }

    private void addRoleUser(List<Role> roleList) {
        Role roleUserEntity = roleRepository.findByAuthority(ROLE_USER).orElseThrow(() -> new RuntimeException("User role not found"));
        roleList.add(roleUserEntity);
    }

    private void addRoleAdmin(List<Role> roleList) {
        Role roleAdminEntity = roleRepository.findByAuthority(ROLE_ADMIN).orElseThrow(() -> new RuntimeException("Admin role not found"));
        roleList.add(roleAdminEntity);
    }
}