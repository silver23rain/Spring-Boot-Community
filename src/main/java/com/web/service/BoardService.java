package com.web.service;

import com.web.domain.Board;
import com.web.repository.BoardRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
	
	private final BoardRepository boardRepository;
	
	public BoardService(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}
	
	//Pageable : 페이징 기능 제공
	public Page<Board> findBoardList(Pageable pageable) {
		pageable = PageRequest.of(pageable.getPageNumber() <= 0 ?
				0 : pageable.getPageNumber() - 1, pageable.getPageSize()); //pageNumber 가 0이하면 0으로 초기화. 기본 페이지 크기인 10으로 새로운 PageRequest 객체를 만들어 페이징 처리된 게시글 리스트 반환
		return boardRepository.findAll(pageable);
	}
	
	public Board findBoardIdx(Long idx) {
		return boardRepository.findById(idx).orElse(new Board()); //idx로 Board 객체를 반환, 없으면 새 객체를 생성해서 반환
	}
	
}
