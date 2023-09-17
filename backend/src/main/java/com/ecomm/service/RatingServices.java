package com.ecomm.service;

import java.util.List;

import com.ecomm.exception.ProductException;
import com.ecomm.model.Rating;
import com.ecomm.model.User;
import com.ecomm.request.RatingRequest;

public interface RatingServices {
	
	public Rating createRating(RatingRequest req,User user) throws ProductException;
	
	public List<Rating> getProductsRating(Long productId);

}
