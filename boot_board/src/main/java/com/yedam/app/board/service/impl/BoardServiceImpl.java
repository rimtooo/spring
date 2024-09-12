package com.yedam.app.board.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.board.mapper.BoardMapper;
import com.yedam.app.board.service.BoardService;
import com.yedam.app.board.service.BoardVO;

@Service // AOP => @Transactional
public class BoardServiceImpl implements BoardService {
	private BoardMapper boardMapper;
	
	@Autowired // 생성자 주입
	public BoardServiceImpl(BoardMapper boardMapper) {
		this.boardMapper = boardMapper;
	}
	
	@Override // 전체조회
	public List<BoardVO> boardList() {
		return boardMapper.selectBoardAll();
	}

	@Override // 단건조회
	public BoardVO boardInfo(BoardVO boardVO) {
		return boardMapper.selectBoardInfo(boardVO);
	}

	@Override // 등록 : 값을 돌려받기 위해서는 selectKey 필요.(getBno 쓰려면)
	public int insertBoard(BoardVO boardVO) {
		int result = boardMapper.insertBoardInfo(boardVO);
		return result == 1 ? boardVO.getBno() : -1;
	}

	@Override // 수정
	public Map<String, Object> updateBoard(BoardVO boardVO) {
		Map<String, Object> map = new HashMap<>();
		boolean isSuccessed = false;
		
		int result = boardMapper.updateBoardInfo(boardVO);
		if(result == 1) {
			isSuccessed = true;
		}
		
		String updateDate = getUpdateDate();
		
		map.put("date", updateDate);
		map.put("result", isSuccessed);
		map.put("target", boardVO);
		
		return map; 
	}
	
	private String getUpdateDate() {
		LocalDate today = LocalDate.now(); 
		DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		String updateDt = today.format(dtFormat);
		return updateDt;
	}
	
	

	@Override // 삭제
	public int deleteBoard(int boardNo) {
		return boardMapper.deleteBoardInfo(boardNo);
	}

}
