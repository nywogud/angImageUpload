package com.jhl.ngImage.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jhl.ngImage.service.Sort3Service;
import com.jhl.ngImage.vo.DataVO;
import com.jhl.ngImage.vo.PagingVO;

@Controller
public class Sort3Controller {

	@Inject
	Sort3Service service;

	private static final String FILE_SERVER_PATH = "D:/img";

	@RequestMapping(value = "/favicon.ico", method = RequestMethod.GET)
	public void favicon(HttpServletRequest request, HttpServletResponse reponse) {
		try {
			reponse.sendRedirect("/resources/favicon.ico");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//GET. url "/"로 들어왔을 경우 redirecting되는 기본 경로. 
	@RequestMapping(value = "/sort3", method = RequestMethod.GET)
	public String homeGet(PagingVO pagingVO, Model model,
			@RequestParam(required = false, value = "nowPage") String nowPage,
			@RequestParam(required = false, value = "cntPerPage") String cntPerPage) throws Exception {
		
		// 기본 화면에서 sort3 이미지 페이징 처리
		//전체 이미지 갯수를 DB 조회를 통해 가져옴.
		int total = service.totalImag();

		// 현재 페이지와 페이지 당 이미지 갯수 지정, 기본 1페이지 이미지 갯수는 12개
		if (nowPage == null && cntPerPage == null) {
			nowPage = "1";
			cntPerPage = "12";
		} else if (nowPage == null) {
			nowPage = "1";
		} else if (cntPerPage == null) {
			cntPerPage = "12";
		}

		// PagingVO 생성자에 파람값(객체 데이터) 셋팅 후 객체 생성
		pagingVO = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		// 모델 객체에 담아 뷰에 전달
		model.addAttribute("paging", pagingVO);
		// 이미지 총 갯수, 현재 페이지, 페이지 당 이미지 수를 파람으로 디비 조회. 페이징 이미지 리스트 호출, 변수에 저장.
		List<DataVO> imgList = service.selectAllWithPaging(pagingVO);

		for (int i = 0; i < imgList.size(); i++) {
			System.out.println(imgList.get(i).getImgName());
		}

		model.addAttribute("imgList", imgList);

		return "main";
	}
	
	//버튼 이벤트, 아작스 통신으로 view 교체
	@RequestMapping(value = "/sort3", method = RequestMethod.POST)
	public String homePost(PagingVO pagingVO, Model model,
			@RequestParam(required = false, value = "nowPage") String nowPage,
			@RequestParam(required = false, value = "cntPerPage") String cntPerPage) throws Exception {
		// 기본 화면에서 sort3 이미지 페이징 처리 나열
		int total = service.totalImag();

		// 현재 페이지와 페이지 당 이미지 갯수 지정, 기본 1페이지 이미지 갯수는 12개
		if (nowPage == null && cntPerPage == null) {
			nowPage = "1";
			cntPerPage = "12";
		} else if (nowPage == null) {
			nowPage = "1";
		} else if (cntPerPage == null) {
			cntPerPage = "12";
		}

		// PagingVO 생성자에 파람값(객체 데이터) 셋팅 후 객체 생성
		pagingVO = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		// 모델 객체에 담아 뷰에 전달
		model.addAttribute("paging", pagingVO);
		// 이미지 총 갯수, 현재 페이지, 페이지 당 이미지 수를 파람으로 디비 조회. 페이징 이미지 리스트 호출, 변수에 저장.
		List<DataVO> imgList = service.selectAllWithPaging(pagingVO);

		model.addAttribute("imgList", imgList);

		return "convertSort3";
	}

	@RequestMapping(value = "/sort3Upload", method = RequestMethod.POST, headers = ("content-type=multipart/*"))
	public String sort3Upload(@RequestParam("file") MultipartFile file, DataVO dataVO, Model model, PagingVO pagingVO,
			@RequestParam(required = false, value = "nowPage") String nowPage,
			@RequestParam(required = false, value = "cntPerPage") String cntPerPage) throws Exception {

		// 로컬에 파일 저장
		file.transferTo(new File(FILE_SERVER_PATH, file.getOriginalFilename()));

		// data 객체에 이미지 이름 세팅
		dataVO.setImgName(file.getOriginalFilename());

		// 이미지 정보 디비 저장
		service.insertImgInf(dataVO);
		// 커밋
		service.doCommit();

		// 기본 화면에서 sort3 이미지 페이징 처리 나열
		int total = service.totalImag();

		// 현재 페이지와 페이지 당 이미지 갯수 지정, 기본 1페이지 이미지 갯수는 12개
		if (nowPage == null && cntPerPage == null) {
			nowPage = "1";
			cntPerPage = "12";
		} else if (nowPage == null) {
			nowPage = "1";
		} else if (cntPerPage == null) {
			cntPerPage = "12";
		}

		// PagingVO 생성자에 파람값(객체 데이터) 셋팅 후 객체 생성
		pagingVO = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		// 모델 객체에 담아 뷰에 전달
		model.addAttribute("paging", pagingVO);
		// 이미지 총 갯수, 현재 페이지, 페이지 당 이미지 수를 파람으로 디비 조회. 페이징 이미지 리스트 호출, 변수에 저장.
		List<DataVO> imgList = service.selectAllWithPaging(pagingVO);

		for (int i = 0; i < imgList.size(); i++) {
			System.out.println(imgList.get(i).getImgName());
		}

		model.addAttribute("imgList", imgList);

		return "sort3";
	}
}
