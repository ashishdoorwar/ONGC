package com.ongc.liferay.service;

import com.ongc.liferay.model.OrderCircular;
import com.ongc.liferay.model.OrderCircularCategory;

import java.io.File;
import java.util.List;

public interface OrderAndCircularService {
	
	public List<OrderCircularCategory> getOrderAndCircularCategory();
	public int insertOrderAndCircular(OrderCircular orderCircular,
			String fileUploadFileName, File fileUpload);
	public List<OrderCircular> selectTopOrderAndCircular();
	public List<OrderCircular> selectOrderAndCircular(String startDate,
			String endDate, String subject, String category);
	public byte[] getAttachmentFile(String id);
	public OrderCircular selectOrderCircularById(int id);
	public int updateOrderAndCircular(OrderCircular orderCircular,
			String fileUploadFileName, File fileUpload);
	public int deleteOrderCircularById(int id);
	
}
