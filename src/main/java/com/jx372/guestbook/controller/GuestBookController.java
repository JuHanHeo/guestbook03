package com.jx372.guestbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jx372.guestbook.dao.GuestBookDao;
import com.jx372.guestbook.vo.GuestBookVo;

@Controller
public class GuestBookController {
	@Autowired
	GuestBookDao guestBookDao;
	
	@RequestMapping(value = "/list" , method=RequestMethod.GET)
	public String gbList(Model model){
		List<GuestBookVo> list = guestBookDao.getList();
		model.addAttribute("list", list);
		return "/WEB-INF/views/gbindex.jsp";
	}
	
	@RequestMapping(value = "/list" , method=RequestMethod.POST)
	public String gbList(GuestBookVo vo){
		guestBookDao.insert(vo);
		return "redirect:/list";
	}
	
	@RequestMapping("/deleteform/{no}")
	public String deleteFrom(Model model,@PathVariable("no") int no){
		model.addAttribute("no", no);
		
		return "/WEB-INF/views/deleteform.jsp";
	}
	
	@RequestMapping("/delete")
	public String delete(GuestBookVo vo){
		System.out.println("들어오나 안오나");
		
		guestBookDao.delete(String.valueOf(vo.getNo()));
		
		return "redirect:/list";
	}
	
	@RequestMapping("/delete2")
	public String delete2(@RequestParam("no") int no){
		System.out.println("들어오나 안오나2");
		
		guestBookDao.delete(String.valueOf(no));
		
		return "redirect:/list";
	}
	
	
	
}
