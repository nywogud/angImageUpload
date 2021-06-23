package com.jhl.ngImage.service;

import java.util.List;

import com.jhl.ngImage.vo.DataVO;
import com.jhl.ngImage.vo.PagingVO;

public interface Sort3Service {
	
	public void insertImgInf(DataVO dataVO) throws Exception;

	public void doCommit() throws Exception;

	public List<DataVO> selectAll() throws Exception;

	public int totalImag() throws Exception;

	public List<DataVO> selectAllWithPaging(PagingVO pagingVO) throws Exception;

}
