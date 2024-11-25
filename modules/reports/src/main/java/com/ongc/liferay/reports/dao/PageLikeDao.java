package com.ongc.liferay.reports.dao;

import com.ongc.liferay.reports.model.PageLike;

public interface PageLikeDao {

	public boolean savePageLike(PageLike dto) ;
	public boolean getPageLikeByCpfNo(String cpf, String pageid);
	public String getPageLikeforSparrow(String cpf, String pageid);
	public int getPageLikeCount(String pageid);
	public String getPageLikeEmpName(String pageid);
	public boolean check_cpf(String cpf);
	public boolean saveUser(String cpf, String chk);
	public boolean updateUser(String cpf, String chk);
	public int getLikesCount(String cpf);
	public boolean saveDislikeUser(String cpf, String chk);
	public boolean updateDislike(String cpf, String chk);
	public int getDisLikesCount(String cpf);
	public String getPostDate(String pageid);
}
