package com.army.movieEro.stCommunity.With_board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.army.movieEro.stCommunity.With_board.service.WithBoardService;
import com.army.movieEro.stCommunity.With_board.vo.WithBoard;

@Controller
public class WithController {
	

		@Autowired
		private WithBoardService bService;

		@RequestMapping("with_blist.do")
		public ModelAndView boardList(ModelAndView mv, @RequestParam(value = "page", required = false) Integer page) {
			System.out.println("with_blist_controller 도착");
			// 페이지 값 처리용
			int currentPage = 1;
			// 한 페이지당 출력할 목록 갯수
			int limit = 10;

			// 전달된 페이지값 추출
			if (page != null)
				currentPage = page;

			// 전체 목록 갯수와 해당 페이지별 목록을 리턴
			int listCount = bService.getListCount();

			System.out.println("1");
			ArrayList<WithBoard> list = bService.selectList(currentPage, limit);
			System.out.println(list);
			System.out.println("2");
			// 총 페이지수 계산 : 목록이 최소 1개일 때 1page로 처리하기
			// 위해 0.9 더함
			int maxPage = (int) ((double) listCount / limit + 0.9);
			// 현재 페이지에 보여줄 시작 페이지수
			// (1, 11, 21, .......)
			// 현재 페이지가 13page 이면 시작페이지는 11page 가 되어야 함
			int startPage = (((int) ((double) currentPage / limit + 0.9)) - 1) * limit + 1;
			// 만약, 목록 아래에 보여질 페이지 갯수가 10개이면
			// 끝페이지수는 20페이지가 되어야 함
			int endPage = startPage + limit - 1;
			if (maxPage < endPage)
				endPage = maxPage;

			if (list != null && list.size() > 0) {

				mv.addObject("list", list)
				.addObject("currentPage", currentPage)
				.addObject("maxPage", maxPage)
				.addObject("startPage", startPage)
				.addObject("endPage", endPage)
				.addObject("listCount", listCount)
				.setViewName("stCommunityBoard/boardList");
			} else {
				mv.addObject("error", "게시글 전체 조회 실패");
				mv.setViewName("board/boardError");
			}
			return mv;
		}

		
		
		

		@RequestMapping("with_binsertForm.do")
		public String boardInsertView(Model model) {
			return "stCommunityBoard/boardInsertForm";
		}
		
		

		@RequestMapping("with_insertForm.do")
		public ModelAndView boardInsertMethod(HttpServletRequest request,
				@RequestParam(value = "file", required = false) MultipartFile file, ModelAndView mv, WithBoard board)
				throws IOException {

			if (bService.insertBoard(board) > 0) {
				mv.setViewName("redirect:boardList.do");
			} else {
				mv.addObject("error", "게시 원글 등록 서비스 실패!");
				mv.setViewName("board/boardError");
			}
			return mv;
		}

		


		@RequestMapping("community.do")
		public String WithBoardListView(Model model) {
			return "stCommunityBoard/boardList";
		}
		
		
		
		


}