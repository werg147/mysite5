package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;
	
	//리스트
	public List<BoardVo> getList() {
		System.out.println("[service] getList()");
		
		return boardDao.selectList();
	}
	
	//리스트2 (리스트+검색)
	public List<BoardVo> getList2(String keyword){
		System.out.println("[service] getList2()");
		System.out.println("keyword= " + keyword);
		
		List<BoardVo> boardList = boardDao.selectList2(keyword);
		
		return boardList;
	}
	
	//리스트3 (검색+페이징)
	public Map<String, Object> getList3(String keyword, int crtPage){
		System.out.println("[service] getList3()");
		
		/////////////////////////////
		//리스트 구하기
		/////////////////////////////
		
		//페이지당 글갯수
		int listCnt = 10;
		
		//현재 페이지
		crtPage = (crtPage > 0) ? crtPage : (crtPage = 1);
		/*
		if(crtPage > 0) {
			crtPage = crtPage;
		} else {
			crtPage = 1;
		}
		*/
		
		//시작 글 번호 startRnum: 1page->1  2page->11  3page->21
		int startRnum = (crtPage-1) * listCnt + 1;
		
		//끝 글 번호 endRnum: 1,10  11,20   21,30
		int endRnum = (startRnum + listCnt) - 1;

		List<BoardVo> boardList = boardDao.selectList3(keyword, startRnum, endRnum);
		
		
		/////////////////////////////
		//페이징 계산
		/////////////////////////////
		
		//페이지당 버튼 갯수
		int pageBtnCount = 5;
		
		//전체 글 갯수 구하기
		int totalCount = boardDao.selectTotalCnt(keyword);
		
		//마지막 버튼 번호
		int endPageBtnNo = (int)Math.ceil(crtPage/(double)pageBtnCount) * pageBtnCount;
										// 1/5.0 -> 0.2 -> 1.0 -> 1 * 5 => 5
		//시작 버튼 번호
		int startPageBtnNo = endPageBtnNo - (pageBtnCount - 1);
		
		//다음 버튼 boolean
		boolean next;
		if(endPageBtnNo * listCnt < totalCount) { // 5*10 < 51
			next = true;                          // 5*19 < 51
		} else {
			next = false;
			endPageBtnNo = (int)Math.ceil(totalCount/(double)listCnt);
		}
		
		//이전 버튼 boolean
		boolean prev;
		if(startPageBtnNo != 1) {
			prev = true;
		} else {
			prev = false;
		}
		
		//boardList, prev, startPageBtnNo, endPageBtnNo, next  ->  jsp 전달
		Map<String, Object> pMap = new HashMap<String, Object>();
		pMap.put("boardList", boardList);
		pMap.put("prev", prev);
		pMap.put("startPageBtnNo", startPageBtnNo);
		pMap.put("endPageBtnNo", endPageBtnNo);
		pMap.put("next", next);
		
		return pMap;
	}
	
	
	//게시글 삭제
	public int remove(int no) {
		System.out.println("[service] remove()");
		
		return boardDao.delete(no);
	}
	
	//게시글 읽기
	public BoardVo getRead(int no) {
		System.out.println("[service] getRead()");
		
		int count = boardDao.hitCount(no);
		System.out.println(count);
		
		return boardDao.selectOne(no);	
	}
	
	//게시글 작성
	public int write(BoardVo boardVo) {
		System.out.println("[service] write()");
		
		for(int i=1; i<=1234; i++) {
			boardVo.setTitle(i + "번째 글 제목입니다.");
			boardVo.setContent(i + "번째 글 내용입니다.");
			boardDao.insert(boardVo);
		}
		
		return 1;
		
	}
	
	//게시글 수정폼
	public BoardVo modifyForm(int no) {
		System.out.println("[service] modifyForm()");
		
		return boardDao.selectOne(no);	
	}
	
	//게시글 수정
	public int modify(BoardVo boardVo) {
		System.out.println("[service] modify()");
		
		return boardDao.update(boardVo);
	}
	
}
