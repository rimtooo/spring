package com.yedam.app.user.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.user.service.UserVO;
@CrossOrigin
@Controller
public class UserController {
   @GetMapping("getAjax")
   @ResponseBody // AJAX => 리턴결과가 순수 데이터
   public Map<String, Object> getAjax(UserVO userVO){
                     // 어노테이션 없이 객체 : 커맨드 객체
                     // QueryString을 처리
      Map<String, Object> map = new HashMap<>();
      map.put("type", "getAjax");
      map.put("data", userVO);
      return map;
   }
   
   @PostMapping("postAjax")
   @ResponseBody // AJAX => 리턴결과가 순수 데이터
   public Map<String, Object> postAjax(UserVO userVO){
                     // 어노테이션 없이 객체 : 커맨드 객체
                     // QueryString을 처리
      Map<String, Object> map = new HashMap<>();
      map.put("type", "postAjax");
      map.put("data", userVO);
      return map;
   }
   
   @PostMapping("jsonAjax")
   @ResponseBody // AJAX => 리턴결과가 순수 데이터
   public Map<String, Object> jsonAjax(@RequestBody UserVO userVO){
                     // JSON : @RequestBody + 객체, 배열
      Map<String, Object> map = new HashMap<>();
      map.put("type", "jsonAjax");
      map.put("data", userVO);
      return map;
   }
   
   @PostMapping("jsonListAjax")
   @ResponseBody // AJAX => 리턴결과가 순수 데이터
   public Map<String, Object> jsonListAjax(@RequestBody List<UserVO> userVO){
                     // JSON : @RequestBody + 객체, 배열
      Map<String, Object> map = new HashMap<>();
      map.put("type", "jsonListAjax");
      map.put("data", userVO);
      return map;
   }
}
