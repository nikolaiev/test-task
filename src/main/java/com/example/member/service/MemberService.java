package com.example.member.service;

import com.example.member.dto.Member;
import com.example.member.repo.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MemberService implements IMemberService {

    private MemberRepository repository;

    @Override
    public List<Member> getAllMembers() {
        return repository.findAll();
    }

    @Override
    public Optional<Member> getMemberByID(String id) {
        return repository.findById(id);
    }

    @Override
    public void deleteMemberById(String id) {
        repository.deleteById(id);
    }

    @Override
    public Member saveMember(Member member) {
        return repository.save(member);
    }

    @Override
    public Member updateMember(Member member) {
        return repository.save(member);
    }

}
