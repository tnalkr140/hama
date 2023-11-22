package com.kh.com.kh.web.Controller;

import com.kh.com.kh.domain.dao.MemberDAO.MailService;

import com.kh.com.kh.domain.svc.MemberSVC.MemberSVC;

import com.kh.com.kh.web.form.memberForm.FindPasswdForm;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;
import java.util.UUID;

@Controller@Slf4j@RequiredArgsConstructor

@RequestMapping("/memberChange")

public class FindSignController {

  private final MemberSVC memberSVC;
  private final MailService mailService;
  @GetMapping
  public ModelAndView changePasswd(
      FindPasswdForm findPasswdForm
   ){
    ModelAndView mv = new ModelAndView();
    mv.addObject("findPasswdForm",findPasswdForm);
    mv.setViewName("webPage/Login/findPasswd");
      return mv;
    }

  //비밀번호 찾기

  @PostMapping
  public ModelAndView findPasswd(
      @Valid
      @ModelAttribute
      FindPasswdForm findPasswdForm,
      BindingResult bindingResult,
      RedirectAttributes redirectAttributes
  ){
    ModelAndView mv = new ModelAndView();
    boolean existUser = memberSVC.existUser(findPasswdForm.getEmail(),findPasswdForm.getTel());
    log.info("파인드패스워드= {}");
    if (!existUser){
      bindingResult.reject("invalidMember",null);
      mv.setViewName("webPage/Login/findPasswd");
      return mv;
    }else{
      String randomPasswd = UUID.randomUUID().toString().substring(0,5);
      memberSVC.changePasswd(findPasswdForm.getEmail(),randomPasswd);
      StringBuffer str = new StringBuffer();

      str.append("<html>");
      str.append("<p><b>");
      str.append(randomPasswd);
      str.append("</b></p>");
      str.append("<p>위 임시번호로 로그인후 비밀번호 변경 바랍니다.</p>");
      str.append("<a href='http://localhost:9080/login'>로그인</a>");
      str.append("</html>");
      mailService.sendMail(findPasswdForm.getEmail(),"임시비밀번호",str.toString());
      mv.addObject("success","회원님의 이메일로 임시비밀번호를 보냈습니다");
      mv.setViewName("webPage/Login/findPasswd");
    return mv;
    }
  }

  @GetMapping("/findEmail")
  public ModelAndView findEmailForm(
      FindPasswdForm findPasswdForm
  ){
    ModelAndView mv = new ModelAndView();
    mv.addObject("findPasswdForm",findPasswdForm);
    mv.setViewName("webPage/Login/findEmail");
    return mv;
  }

  @PostMapping("/findEmail")
  public ModelAndView findEmail(
      @Valid
      @ModelAttribute
      FindPasswdForm findPasswdForm,
      BindingResult bindingResult
  ){
    ModelAndView mv = new ModelAndView();
    Optional<String> findedEmail = memberSVC.findEmailByTel(findPasswdForm.getTel());
    mv.setViewName("webPage/Login/findEmail");
    if(findedEmail.isEmpty()){
      bindingResult.reject("invalidMember",null);
      return mv;
    }
    String showEmail = findedEmail.get();
    mv.addObject("findEmail",showEmail);
    // 모델
    return mv;
  }
}