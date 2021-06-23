package com.jhl.ngImage.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import com.jhl.ngImage.vo.DataVO;
import com.jhl.ngImage.vo.PagingVO;

@Repository
public class Sort3DAOImp implements Sort3DAO {
	
	@Inject
	private SqlSession sqlSession;
	
	private static final String Namespace = "com.jhl.mappers.sort3Mapper";

	@Override
	public void insertImgInf(DataVO dataVO) throws Exception{
		
		sqlSession.insert(Namespace + ".insertImgInf", dataVO);
	}
	
	@Override
	public void doCommit() throws Exception{
		sqlSession.update(Namespace + ".doCommit");
	}
	
	@Override
	public List<DataVO> selectAll() throws Exception{
		return sqlSession.selectList(Namespace + ".selectAll");
	}
	
	@Override
	public int totalImag() throws Exception{
		return sqlSession.selectOne(Namespace+".totalImag");
	}
	
	@Override
	public List<DataVO> selectAllWithPaging(PagingVO pagingVO) throws Exception{
		return sqlSession.selectList(Namespace+".selectAllWithPaging", pagingVO);
	}
	
}
