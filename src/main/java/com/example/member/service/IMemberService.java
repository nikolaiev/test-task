package com.example.member.service;

import com.example.member.model.Member;

import java.util.List;
import java.util.Optional;

public interface IMemberService {
    List<Member> getAllMembers();

    Optional<Member> getMemberByID(String id);

    void deleteMemberById(String id);

    Member saveMember(Member member);

    Member updateMember(Member member);
}
