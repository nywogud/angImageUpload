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
import org.springframework.web.bind.annotation.ResponseBody;
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

	//get���. url "/"�� ������ ��� redirecting�Ǵ� ���. 
	@RequestMapping(value = "/sort3", method = RequestMethod.GET)
	public String homeGet(PagingVO pagingVO, Model model,
			@RequestParam(required = false, value = "nowPage") String nowPage,
			@RequestParam(required = false, value = "cntPerPage") String cntPerPage) throws Exception {
		// �⺻ ȭ�鿡�� sort3 �̹��� ����¡ ó�� ����
		int total = service.totalImag();

		// ���� �������� ������ �� �̹��� ���� ����, �⺻ 1������ �̹��� ������ 12��
		if (nowPage == null && cntPerPage == null) {
			nowPage = "1";
			cntPerPage = "12";
		} else if (nowPage == null) {
			nowPage = "1";
		} else if (cntPerPage == null) {
			cntPerPage = "12";
		}

		// PagingVO �����ڿ� �Ķ���(��ü ������) ���� �� ��ü ����
		pagingVO = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		// �� ��ü�� ��� �信 ����
		model.addAttribute("paging", pagingVO);
		// �̹��� �� ����, ���� ������, ������ �� �̹��� ���� �Ķ����� ��� ��ȸ. ����¡ �̹��� ����Ʈ ȣ��, ������ ����.
		List<DataVO> imgList = service.selectAllWithPaging(pagingVO);

		for (int i = 0; i < imgList.size(); i++) {
			System.out.println(imgList.get(i).getImgName());
		}

		model.addAttribute("imgList", imgList);

		return "main";
	}
	
	//��ư �̺�Ʈ, ���۽� ������� view ��ü
	@RequestMapping(value = "/sort3", method = RequestMethod.POST)
	public String homePost(PagingVO pagingVO, Model model,
			@RequestParam(required = false, value = "nowPage") String nowPage,
			@RequestParam(required = false, value = "cntPerPage") String cntPerPage) throws Exception {
		// �⺻ ȭ�鿡�� sort3 �̹��� ����¡ ó�� ����
		int total = service.totalImag();

		// ���� �������� ������ �� �̹��� ���� ����, �⺻ 1������ �̹��� ������ 12��
		if (nowPage == null && cntPerPage == null) {
			nowPage = "1";
			cntPerPage = "12";
		} else if (nowPage == null) {
			nowPage = "1";
		} else if (cntPerPage == null) {
			cntPerPage = "12";
		}

		// PagingVO �����ڿ� �Ķ���(��ü ������) ���� �� ��ü ����
		pagingVO = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		// �� ��ü�� ��� �信 ����
		model.addAttribute("paging", pagingVO);
		// �̹��� �� ����, ���� ������, ������ �� �̹��� ���� �Ķ����� ��� ��ȸ. ����¡ �̹��� ����Ʈ ȣ��, ������ ����.
		List<DataVO> imgList = service.selectAllWithPaging(pagingVO);

		for (int i = 0; i < imgList.size(); i++) {
			System.out.println(imgList.get(i).getImgName());
		}

		model.addAttribute("imgList", imgList);

		return "convertSort3";
	}

	@RequestMapping(value = "/sort3Upload", method = RequestMethod.POST, headers = ("content-type=multipart/*"))
	public String sort3Upload(@RequestParam("file") MultipartFile file, DataVO dataVO, Model model, PagingVO pagingVO,
			@RequestParam(required = false, value = "nowPage") String nowPage,
			@RequestParam(required = false, value = "cntPerPage") String cntPerPage) throws Exception {

		// ���ÿ� ���� ����
		file.transferTo(new File(FILE_SERVER_PATH, file.getOriginalFilename()));

		// data ��ü�� �̹��� �̸� ����
		dataVO.setImgName(file.getOriginalFilename());

		// �̹��� ���� ��� ����
		service.insertImgInf(dataVO);
		// Ŀ��
		service.doCommit();

		// �⺻ ȭ�鿡�� sort3 �̹��� ����¡ ó�� ����
		int total = service.totalImag();

		// ���� �������� ������ �� �̹��� ���� ����, �⺻ 1������ �̹��� ������ 12��
		if (nowPage == null && cntPerPage == null) {
			nowPage = "1";
			cntPerPage = "12";
		} else if (nowPage == null) {
			nowPage = "1";
		} else if (cntPerPage == null) {
			cntPerPage = "12";
		}

		// PagingVO �����ڿ� �Ķ���(��ü ������) ���� �� ��ü ����
		pagingVO = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		// �� ��ü�� ��� �信 ����
		model.addAttribute("paging", pagingVO);
		// �̹��� �� ����, ���� ������, ������ �� �̹��� ���� �Ķ����� ��� ��ȸ. ����¡ �̹��� ����Ʈ ȣ��, ������ ����.
		List<DataVO> imgList = service.selectAllWithPaging(pagingVO);

		for (int i = 0; i < imgList.size(); i++) {
			System.out.println(imgList.get(i).getImgName());
		}

		model.addAttribute("imgList", imgList);

		return "sort3";
	}
}