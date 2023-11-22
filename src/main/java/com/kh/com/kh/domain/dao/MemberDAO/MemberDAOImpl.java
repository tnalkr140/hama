package com.kh.com.kh.domain.dao.MemberDAO;

import com.kh.com.kh.domain.dao.entity.Member;

import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;

import org.springframework.dao.EmptyResultDataAccessException;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import org.springframework.jdbc.core.RowMapper;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import org.springframework.stereotype.Repository;

import java.util.Map;

import java.util.Optional;

@Slf4j

@Repository

@RequiredArgsConstructor

public class MemberDAOImpl implements MemberDAO {

  private final NamedParameterJdbcTemplate template;

  // email을 대조하여 한행 다열(해당고객의 모든정보)를 rowMapper(여러요소를 담을때 사용)에 담아서

  // queryForObject의 jdbc로 컴파일후 템플릿에 저장한거 리턴

  @Override

  public Optional<Member> findByInfo(String email) {

    // sql 쿼리문 선언

    StringBuffer sql = new StringBuffer();

    sql.append("select member_id, email, passwd, tel, nickname, hobby, gubun, pic, cdate, udate ");

    sql.append("from member where email = :email ");

    // map에 email을 담음 키는 email

    Map<String, String> param = Map.of("email",email);

    // RowMapper에 member.class와 컬럼명이 동일할 경우 자동 매핑

    RowMapper rowMapper = new BeanPropertyRowMapper(Member.class);

    try{

      Member findedmember = (Member) template.queryForObject(sql.toString(), param, rowMapper);

      return Optional.of(findedmember);

    }catch (EmptyResultDataAccessException e){

      return Optional.empty();

    }

  }

  // Member객체의 요소를 꺼내와 insert쿼리 진행

  @Override

  public Member insert(Member member) {

    StringBuffer sql = new StringBuffer();

    sql.append("insert into member (member_id,email,passwd,nickname,tel) ");

    sql.append("             values(member_member_id_seq.nextval, :email, :passwd, :nickname, :tel) ");

    SqlParameterSource param = new BeanPropertySqlParameterSource(member);

    int update = template.update(sql.toString(), param);

    return member;

  }

  // 별명 중첩확인 찾을시 중첩

  @Override

  public Boolean nickExist(String nickname) {

    String sql = new String();

    sql = "select count(*) from member where nickname = :nickname ";

    Map<String, String> param = Map.of("nickname",nickname);

    Integer cnt = template.queryForObject(sql, param, Integer.class);

    return cnt == 1 ? true : false ;

  }

  //전화로 아이디 유무 확인 후 반환

  @Override

  public Optional<String> findEmailByTel(String tel) {

    String findEmail = "select email from member where tel= :tel ";

    Map<String, String> mapString = Map.of("tel",tel);

    try{

      String findedEmail = template.queryForObject(findEmail,mapString,String.class);

      return Optional.of(findedEmail);

    }catch(EmptyResultDataAccessException e){

      return Optional.empty();

    }

  }

  //전화번호, 이메일로 불린값 출력

  @Override

  public boolean existUser(String email, String tel) {

    StringBuffer stringBuffer = new StringBuffer();

    stringBuffer.append("select count(*) from member ");

    stringBuffer.append("where email= :email ");

    stringBuffer.append("and tel= :tel ");

    Map<String, String> mapString = Map.of("email",email,"tel",tel);

    Integer countNumber = template.queryForObject(stringBuffer.toString(),mapString,Integer.class);

    return countNumber == 1 ? true : false ;

  }

  //이메일, 패스워드로 비밀번호 변경

  @Override

  public int changePasswd(String email, String passwd) {

    String sqlString = "update member set passwd= :passwd where email= :email ";

    Map<String, String> mapString = Map.of("email",email,"passwd",passwd);

    int returnsValue = template.update(sqlString,mapString);

    return returnsValue;

  }

  @Override
  public Optional<String> findTelByTel(String tel) {
    String findEmail = "select tel from member where tel= :tel ";

    Map<String, String> mapString = Map.of("tel",tel);

    try{

      String findedTel = template.queryForObject(findEmail,mapString,String.class);

      return Optional.of(findedTel);

    }catch(EmptyResultDataAccessException e){

      return Optional.empty();

    }
  }
}