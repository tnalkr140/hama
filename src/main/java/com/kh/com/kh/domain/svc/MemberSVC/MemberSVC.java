package com.kh.com.kh.domain.svc.MemberSVC;

import com.kh.com.kh.domain.dao.entity.Member;

import java.util.Optional;

public interface MemberSVC {

  Optional <Member> findByInfo(String email);

  Boolean nickExist (String nickname);

  Member insert(Member member);

  //전화번호로 이메일 확인 후 반환

  Optional<String> findEmailByTel(String tel);

  //전화번호, 이메일로 가입자 확인 후 불린값 반환

  boolean existUser(String email, String tel);

  //이메일로 비밀번호 변경 후 변경 확인

  int changePasswd(String email, String passwd);

  Optional<String> findTelByTel(String tel);

}