package com.ecomm.service;

import java.util.List;

import com.ecomm.exception.ProductException;
import com.ecomm.model.Review;
import com.ecomm.model.User;
import com.ecomm.request.ReviewRequest;

public interface ReviewService {

	public Review createReview(ReviewRequest req,User user) throws ProductException;
	
	public List<Review> getAllReview(Long productId);
	
	
}
