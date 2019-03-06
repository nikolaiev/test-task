package com.example.member.rest;

import com.example.member.model.Member;
import com.example.member.service.IMemberService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("members")
@AllArgsConstructor
public class MemberController {

    private IMemberService memberService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Member getMemberByID(@PathVariable String id) {
        final Optional<Member> byId = memberService.getMemberById(id);
        return byId.orElse(null);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteMemberById(@PathVariable String id) {
        memberService.deleteMemberById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Member saveMember(@Valid @RequestBody Member member) {
        return memberService.saveMember(member);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Member updateMember(@Valid @RequestBody Member member) {
        return memberService.updateMember(member);
    }
}
