package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.entity.SuperType;
import com.service.TestService;

@RestController
public class TestController {
	@Autowired
	private TestService service;
  @RequestMapping("test")
  public ModelAndView test() {
	  List<SuperType> list=service.selectSuperTypes();
	  ModelAndView m=new ModelAndView();
	  m.addObject("message", list);
	  m.setView(new MappingJackson2JsonView());
	  return m;
  }
}
