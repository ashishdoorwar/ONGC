package com.ongc.liferay.serviceImpl;

import com.ongc.liferay.DaoImpl.OrderAndCircularDaoImpl;
import com.ongc.liferay.dao.OrderAndCircularDao;
import com.ongc.liferay.model.OrderCircular;
import com.ongc.liferay.model.OrderCircularCategory;
import com.ongc.liferay.service.OrderAndCircularService;

import java.io.File;
import java.util.List;

public class OrderAndCircularServiceImpl implements OrderAndCircularService{
	
	private OrderAndCircularDao orderAndCircularDao = new OrderAndCircularDaoImpl();
	
	@Override
	public List<OrderCircularCategory> getOrderAndCircularCategory() {
		// TODO Auto-generated method stub
		return orderAndCircularDao.getOrderAndCircularCategory();
	}

	@Override
	public int insertOrderAndCircular(OrderCircular orderCircular, String fileUploadFileName, File fileUpload) {
		// TODO Auto-generated method stub
		return orderAndCircularDao.insertOrderAndCircular(orderCircular, fileUploadFileName, fileUpload);
	}

	@Override
	public List<OrderCircular> selectTopOrderAndCircular() {
		// TODO Auto-generated method stub
		return orderAndCircularDao.selectTopOrderAndCircular();
	}

	@Override
	public List<OrderCircular> selectOrderAndCircular(String startDate, String endDate, String subject,
			String category) {
		// TODO Auto-generated method stub
		return orderAndCircularDao.selectOrderAndCircular(startDate, endDate, subject, category);
	}

	@Override
	public byte[] getAttachmentFile(String id) {
		// TODO Auto-generated method stub
		return orderAndCircularDao.getAttachmentFile(id);
	}

	@Override
	public OrderCircular selectOrderCircularById(int id) {
		// TODO Auto-generated method stub
		return orderAndCircularDao.selectOrderCircularById(id);
	}

	@Override
	public int updateOrderAndCircular(OrderCircular orderCircular, String fileUploadFileName, File fileUpload) {
		// TODO Auto-generated method stub
		return orderAndCircularDao.updateOrderAndCircular(orderCircular, fileUploadFileName, fileUpload);
	}

	@Override
	public int deleteOrderCircularById(int id) {
		// TODO Auto-generated method stub
		return orderAndCircularDao.deleteOrderCircularById(id);
	}

}
