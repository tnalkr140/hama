package com.kh.com.kh.domain.dao.MemberDAO;

import com.kh.com.kh.domain.dao.entity.Member;

import java.util.Optional;

public interface MemberDAO {

  Optional<Member> findByInfo (String email);

  Boolean nickExist (String nickname);

  Member insert(Member member);

  //전화번호로 이메일 유무 확인

  Optional<String> findEmailByTel(String tel);

  //이메일, 전화번호로 가입자 확인

  boolean existUser(String email, String tel);

  //비밀번호 변경

  int changePasswd(String email, String passwd);

  Optional<String> findTelByTel(String tel);

}
