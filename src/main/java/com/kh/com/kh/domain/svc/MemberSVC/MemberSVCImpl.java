package com.kh.com.kh.domain.svc.MemberSVC;

import com.kh.com.kh.domain.dao.MemberDAO.MemberDAO;

import com.kh.com.kh.domain.dao.entity.Member;

import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j

@Service

@RequiredArgsConstructor

public class MemberSVCImpl implements MemberSVC {

  private final MemberDAO memberDAO;

  @Override

  public Optional<Member> findByInfo(String email) {

    return memberDAO.findByInfo(email);

  }

  @Override

  public Member insert(Member member) {

    return memberDAO.insert(member);

  }

  @Override

  public Boolean nickExist(String nickname) {

    return memberDAO.nickExist(nickname);

  }

  @Override

  public Optional<String> findEmailByTel(String tel) {

    return memberDAO.findEmailByTel(tel);

  }

  @Override

  public boolean existUser(String email, String tel) {

    return memberDAO.existUser(email,tel);

  }

  @Override

  public int changePasswd(String email, String passwd) {

    return memberDAO.changePasswd(email,passwd);

  }

  @Override
  public Optional<String> findTelByTel(String tel) {
    return memberDAO.findTelByTel(tel);
  }
}