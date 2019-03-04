package com.example.member.repo;

import com.example.member.dto.Member;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MemberRepository    extends MongoRepository<Member, String> {
}
