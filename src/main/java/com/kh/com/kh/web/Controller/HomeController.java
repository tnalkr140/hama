package com.kh.com.kh.web.Controller;

import com.kh.com.kh.domain.dao.entity.Community;
import com.kh.com.kh.domain.svc.CommunitySVC.CommunitySVC;
import com.kh.com.kh.web.form.memberForm.SessionForm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
@RequestMapping
@RequiredArgsConstructor
// ()의 경로와 컨트롤러를 이어주는 것 경로 = 키 컨트롤러 = 밸류
public class HomeController {
private final CommunitySVC communitySVC;
  @GetMapping("/")
  public ModelAndView webAddForm(
      HttpServletRequest request,
      HttpSession session
  ){
    ModelAndView mv = new ModelAndView();
    String view = null;
    HttpSession loginCheck = request.getSession(false);

    List<Community> communities = communitySVC.indexComu();


    log.info("session ={}",loginCheck);
    mv.addObject("comu",communities);
    mv.addObject("loginCheck",loginCheck);
    mv.setViewName("webPage/indexs/index");
    return mv;
  }

  @GetMapping("/cus_center")
  public ModelAndView cusCenter(){
    ModelAndView mv = new ModelAndView();
    mv.setViewName("webPage/cus_center/cus_center");
    return mv;
  }
  }
