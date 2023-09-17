package com.ecomm.service;

import com.ecomm.exception.CartItemException;
import com.ecomm.exception.UserException;
import com.ecomm.model.Cart;
import com.ecomm.model.CartItem;
import com.ecomm.model.Product;

public interface CartItemService {
	
	public CartItem createCartItem(CartItem cartItem);
	
	public CartItem updateCartItem(Long userId, Long id,CartItem cartItem) throws CartItemException, UserException;
	
	public CartItem isCartItemExist(Cart cart,Product product,String size, Long userId);
	
	public void removeCartItem(Long userId,Long cartItemId) throws CartItemException, UserException;
	
	public CartItem findCartItemById(Long cartItemId) throws CartItemException;
	
}
