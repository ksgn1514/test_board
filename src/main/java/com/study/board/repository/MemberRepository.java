package com.study.board.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.study.board.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    // 이메일로 회원 정보 조회 (select * from member_table where member_email=?)
    Optional<Member> findByMemberEmail(String memberEmail);

}
