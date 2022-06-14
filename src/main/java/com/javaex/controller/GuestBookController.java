package com.javaex.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.GuestBookDao;
import com.javaex.vo.GuestBookVo;

@Controller
public class GuestBookController {

	// 생성자
	// 메소드gs
	// 메소드일반

	// 리스트
	@RequestMapping(value = "/addList", method = { RequestMethod.GET, RequestMethod.POST })
	public String addList(Model model) {
		System.out.println("addList");

		GuestBookDao gDao = new GuestBookDao();
		List<GuestBookVo> guestList = gDao.getList();

		model.addAttribute("gList", guestList);

		return "/WEB-INF/views/addList.jsp";
	}

	// 등록
	@RequestMapping(value = "/add", method = { RequestMethod.GET, RequestMethod.POST })
	public String add(@ModelAttribute GuestBookVo guestBookVo) {
		System.out.println("add");

		GuestBookDao guestBookDao = new GuestBookDao();
		guestBookDao.insert(guestBookVo);

		return "redirect:/addList";
	}

	// 삭제품
	@RequestMapping(value = "/deleteForm/{no}", method = { RequestMethod.GET, RequestMethod.POST })
	public String deleteForm(Model model, @PathVariable("no") int no) {
		System.out.println("deleteForm");
		model.addAttribute("no", no);
		return "/WEB-INF/views/deleteForm.jsp";
	}

	// 삭제
	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@ModelAttribute GuestBookVo guestbookVo, @RequestParam("no") int no,
			@RequestParam("password") String password) {
		System.out.println("delete");

		GuestBookDao guestbookDao = new GuestBookDao();
		no = guestbookVo.getNo();
		password = guestbookVo.getPassword();
		guestbookDao.delete(no, password);

		return "redirect:/addList";
	}

}