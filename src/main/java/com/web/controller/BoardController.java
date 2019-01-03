package com.web.controller;

import com.web.service.BoardService;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/board")
public class BoardController {
	private final BoardService boardService;
	
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	@GetMapping({"", "/"})
	public String board(@RequestParam(value = "idx", defaultValue = "0") Long idx, Model model) {
		model.addAttribute("board", boardService.findBoardByIdx(idx));
		return "/board/form";
	}
	
	@GetMapping("/list")
	public String list(@PageableDefault Pageable pageable, Model model) { //@PageableDefault : 파라미터 size, sort, direction 등를 사용하여 페이징처리에 대한 규역을 정의할 수 있음
		model.addAttribute("boardList", boardService.findBoardList(pageable));
		return "/board/list";
	}
	
}