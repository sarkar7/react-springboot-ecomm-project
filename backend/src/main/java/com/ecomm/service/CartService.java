package com.ecomm.service;

import com.ecomm.exception.ProductException;
import com.ecomm.model.Cart;
import com.ecomm.model.CartItem;
import com.ecomm.model.User;
import com.ecomm.request.AddItemRequest;

public interface CartService {
	
	public Cart createCart(User user);
	
	public String addCartItem(Long userId,AddItemRequest req) throws ProductException;
	
	public Cart findUserCart(Long userId);

}
