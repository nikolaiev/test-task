package com.example.member.controller;

import com.example.member.dto.Member;
import com.example.member.repo.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("members")
@AllArgsConstructor
public class MemberController {

    private MemberRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Member> getAllMembers() {
        return repository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Member getMemberByID(@PathVariable String id) {
        final Optional<Member> byId = repository.findById(id);
        return byId.orElse(null);
    }

    @RequestMapping(value = "member/{id}", method = RequestMethod.DELETE)
    public void deleteMemberById(@PathVariable String id) {
        repository.deleteById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Member saveMember(@RequestBody Member member) {
        return repository.save(member);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Member updateMember(@RequestBody Member member) {
        return repository.save(member);
    }

}
