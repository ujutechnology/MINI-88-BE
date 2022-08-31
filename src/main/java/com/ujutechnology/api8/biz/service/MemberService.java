package com.ujutechnology.api8.biz.service;

import com.ujutechnology.api8.api.dto.LoginDto;
import com.ujutechnology.api8.api.dto.MemberDto;
import com.ujutechnology.api8.api.dto.RegistMemberDto;
import com.ujutechnology.api8.biz.domain.Member;
import com.ujutechnology.api8.biz.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author kei
 * @since 2022-08-24
 */
@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    public void register(RegistMemberDto registMemberDto) throws Exception  {
        Optional<Member> memberOpt = memberRepository.findByEmail(registMemberDto.getEmail());
        if(memberOpt.isEmpty()){
            Member member = Member.builder()
                    .email(registMemberDto.getEmail())
                    .password(registMemberDto.getPassword())
                    .name(registMemberDto.getName())
                    .role("USER")
                    .profilePhoto(registMemberDto.getProfilePhoto())
                    .age(registMemberDto.getAge())
                    .job(registMemberDto.getJob())
                    .build();
            memberRepository.save(member);
        } else {
            throw new Exception("회원가입 실패");
        }
    }

    public void login(LoginDto loginDto) throws Exception {
        memberRepository.findByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword())
                .orElseThrow(()->new Exception("로그인 실패"));
    }

    public void getMember(String email, MemberDto memberDto) {
        memberRepository.findByEmail(email).ifPresent(member -> {
            memberDto.setMember(member);
        });
    }
}
