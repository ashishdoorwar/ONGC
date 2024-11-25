package com.ongc.liferay.reports.service.Impl;

import com.ongc.liferay.reports.dao.PageLikeDao;
import com.ongc.liferay.reports.dao.Impl.PageLikeDaoImpl;
import com.ongc.liferay.reports.model.PageLike;
import com.ongc.liferay.reports.service.PageLikeService;

public class PageLikeServiceImpl implements PageLikeService{

	private PageLikeDao pageLikeDao = new PageLikeDaoImpl();
	
	@Override
	public boolean savePageLike(PageLike dto) {
		
		return pageLikeDao.savePageLike(dto);
	}

	@Override
	public boolean getPageLikeByCpfNo(String cpf, String pageid) {
		// TODO Auto-generated method stub
		return pageLikeDao.getPageLikeByCpfNo(cpf, pageid);
	}

	@Override
	public String getPageLikeforSparrow(String cpf, String pageid) {
		// TODO Auto-generated method stub
		return pageLikeDao.getPageLikeforSparrow(cpf, pageid);
	}

	@Override
	public int getPageLikeCount(String pageid) {
		// TODO Auto-generated method stub
		return pageLikeDao.getPageLikeCount(pageid);
	}

	@Override
	public String getPageLikeEmpName(String pageid) {
		// TODO Auto-generated method stub
		return pageLikeDao.getPageLikeEmpName(pageid);
	}

	@Override
	public boolean check_cpf(String cpf) {
		// TODO Auto-generated method stub
		return pageLikeDao.check_cpf(cpf);
	}

	@Override
	public boolean saveUser(String cpf, String chk) {
		// TODO Auto-generated method stub
		return pageLikeDao.saveUser(cpf, chk);
	}

	@Override
	public boolean updateUser(String cpf, String chk) {
		// TODO Auto-generated method stub
		return pageLikeDao.updateUser(cpf, chk);
	}

	@Override
	public int getLikesCount(String cpf) {
		// TODO Auto-generated method stub
		return pageLikeDao.getLikesCount(cpf);
	}

	@Override
	public boolean saveDislikeUser(String cpf, String chk) {
		// TODO Auto-generated method stub
		return pageLikeDao.saveDislikeUser(cpf, chk);
	}

	@Override
	public boolean updateDislike(String cpf, String chk) {
		// TODO Auto-generated method stub
		return pageLikeDao.updateDislike(cpf, chk);
	}

	@Override
	public int getDisLikesCount(String cpf) {
		// TODO Auto-generated method stub
		return pageLikeDao.getDisLikesCount(cpf);
	}

	@Override
	public String getPostDate(String pageid) {
		// TODO Auto-generated method stub
		return pageLikeDao.getPostDate(pageid);
	}

}
