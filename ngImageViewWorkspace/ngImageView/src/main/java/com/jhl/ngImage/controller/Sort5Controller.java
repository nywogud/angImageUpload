package com.jhl.ngImage.controller;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jhl.ngImage.service.Sort5Service;
import com.jhl.ngImage.vo.DataVO;
import com.jhl.ngImage.vo.PagingVO;

@Controller
public class Sort5Controller {

	@Inject
	Sort5Service service;

	private static final String FILE_SERVER_PATH = "D:/img";

	//sort5로 GET 방식으로 넘어올 경우
	@RequestMapping(value = "/sort5", method = RequestMethod.GET)
	public String homeGET(PagingVO pagingVO, Model model,
			@RequestParam(required = false, value = "nowPage") String nowPage,
			@RequestParam(required = false, value = "cntPerPage") String cntPerPage) throws Exception {
		// 기본 화면에서 sort4 이미지 페이징 처리
		int total = service.totalImag();

		// 현재 페이지와 페이지 당 이미지 갯수 지정, 기본 1페이지 이미지 갯수는 20개
		if (nowPage == null && cntPerPage == null) {
			nowPage = "1";
			cntPerPage = "20";
		} else if (nowPage == null) {
			nowPage = "1";
		} else if (cntPerPage == null) {
			cntPerPage = "20";
		}

		// PagingVO 생성자에 파람값(객체 데이터) 셋팅 후 객체 생성
		pagingVO = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		// 모델 객체에 담아 뷰에 전달
		model.addAttribute("paging", pagingVO);
		// 이미지 총 갯수, 현재 페이지, 페이지 당 이미지 수를 파람으로 디비 조회. 페이징 이미지 리스트 호출, 변수에 저장.
		List<DataVO> imgList = service.selectAllWithPaging(pagingVO);

		model.addAttribute("imgList", imgList);

		return "main5";
	}

	// 버튼 이벤트, 아작스 통신으로 view 교체
	@RequestMapping(value = "/sort5", method = RequestMethod.POST)
	public String homePost(PagingVO pagingVO, Model model,
			@RequestParam(required = false, value = "nowPage") String nowPage,
			@RequestParam(required = false, value = "cntPerPage") String cntPerPage) throws Exception {
		// 기본 화면에서 sort4 이미지 페이징 처리
		int total = service.totalImag();

		// 현재 페이지와 페이지 당 이미지 갯수 지정, 기본 1페이지 이미지 갯수는 20개
		if (nowPage == null && cntPerPage == null) {
			nowPage = "1";
			cntPerPage = "20";
		} else if (nowPage == null) {
			nowPage = "1";
		} else if (cntPerPage == null) {
			cntPerPage = "20";
		}

		// PagingVO 생성자에 파람값(객체 데이터) 셋팅 후 객체 생성
		pagingVO = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		// 모델 객체에 담아 뷰에 전달
		model.addAttribute("paging", pagingVO);
		// 이미지 총 갯수, 현재 페이지, 페이지 당 이미지 수를 파람으로 디비 조회. 페이징 이미지 리스트 호출, 변수에 저장.
		List<DataVO> imgList = service.selectAllWithPaging(pagingVO);

		model.addAttribute("imgList", imgList);

		return "convertSort5";
	}

	@RequestMapping(value = "/sort5Upload", method = RequestMethod.POST, headers = ("content-type=multipart/*"))
	public String sort5Upload(@RequestParam("file") MultipartFile file, DataVO dataVO, Model model, PagingVO pagingVO,
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

		// sort4 이미지 페이징 처리 나열
		int total = service.totalImag();

		// 현재 페이지와 페이지 당 이미지 갯수 지정, 기본 1페이지 이미지 갯수는 20개
		if (nowPage == null && cntPerPage == null) {
			nowPage = "1";
			cntPerPage = "20";
		} else if (nowPage == null) {
			nowPage = "1";
		} else if (cntPerPage == null) {
			cntPerPage = "20";
		}

		// PagingVO 생성자에 파람값(객체 데이터) 셋팅 후 객체 생성
		pagingVO = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		// 모델 객체에 담아 뷰에 전달
		model.addAttribute("paging", pagingVO);
		// 이미지 총 갯수, 현재 페이지, 페이지 당 이미지 수를 파람으로 디비 조회. 페이징 이미지 리스트 호출, 변수에 저장.
		List<DataVO> imgList = service.selectAllWithPaging(pagingVO);

		model.addAttribute("imgList", imgList);

		return "sort5";
	}
}
