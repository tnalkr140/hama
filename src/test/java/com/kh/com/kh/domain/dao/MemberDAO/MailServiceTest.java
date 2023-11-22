package com.kh.com.kh.domain.dao.MemberDAO;

import groovy.util.logging.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MailServiceTest {

  @Autowired
  private MailService mailService;

  @Test
  void test(){
    mailService.sendMail("naruplace19@gmail.com","test","test");
  }
}
