package com.example.member.controller;

import com.example.member.dto.Member;
import com.example.member.repo.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class MemberController {

    @Autowired
    private MemberRepository repository;

    @RequestMapping(value = "member/{id}" ,method = RequestMethod.GET)
    public Member getMemberByID(@PathVariable String id){
        final Optional<Member> byId = repository.findById(id);
        return byId.orElse(null);
    }
    //TODO

}
