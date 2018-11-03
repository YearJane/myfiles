package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.SuperType;
import com.mapper.SuperTypeMapper;

@Service
public class TestService {
	@Autowired
  private SuperTypeMapper mapper;
  public List<SuperType> selectSuperTypes(){
	  return mapper.selectSuperTypes();
  }
}
