package com.jhl.ngImage.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.jhl.ngImage.dao.Sort3DAO;
import com.jhl.ngImage.vo.DataVO;
import com.jhl.ngImage.vo.PagingVO;

@Service
public class Sort3ServiceImp implements Sort3Service {
	
	@Inject
	Sort3DAO dao;
	
	@Override
	public void insertImgInf(DataVO dataVO) throws Exception{
		dao.insertImgInf(dataVO);
	}
	
	@Override
	public void doCommit() throws Exception{
		dao.doCommit();
	}
	
	@Override
	public List<DataVO> selectAll() throws Exception{
		return dao.selectAll();
	}
	
	@Override
	public int totalImag() throws Exception{
		return dao.totalImag();
	}
	
	@Override
	public List<DataVO> selectAllWithPaging(PagingVO pagingVO) throws Exception{
		return dao.selectAllWithPaging(pagingVO);
	}
}
