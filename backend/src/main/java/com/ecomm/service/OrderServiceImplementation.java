package com.ecomm.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomm.exception.OrderException;
import com.ecomm.model.Address;
import com.ecomm.model.Cart;
import com.ecomm.model.CartItem;
import com.ecomm.model.Order;
import com.ecomm.model.OrderItem;
import com.ecomm.model.User;
import com.ecomm.repository.AddressRepository;
import com.ecomm.repository.OrderItemRepository;
import com.ecomm.repository.OrderRepository;
import com.ecomm.repository.UserRepository;
import com.ecomm.user.domain.OrderStatus;
import com.ecomm.user.domain.PaymentStatus;

@Service
public class OrderServiceImplementation implements OrderService {


	private OrderRepository orderRepository;
	private CartService cartService;
	private AddressRepository addressRepository;
	private UserRepository userRepository;
	private OrderItemService orderItemService;
	private OrderItemRepository orderItemRepository;
	

	public OrderServiceImplementation(OrderRepository orderRepository,CartService cartService,
			AddressRepository addressRepository,UserRepository userRepository,
			OrderItemService orderItemService,OrderItemRepository orderItemRepository) {
		this.orderRepository=orderRepository;
		this.cartService=cartService;
		this.addressRepository=addressRepository;
		this.userRepository=userRepository;
		this.orderItemService=orderItemService;
		this.orderItemRepository=orderItemRepository;
	}


	@Override
	public Order createOrder(User user, Address shippAddress) {
		
		shippAddress.setUser(user);
		Address address= addressRepository.save(shippAddress);
		user.getAddresses().add(address);
		userRepository.save(user);
		
		Cart cart=cartService.findUserCart(user.getId());
		List<OrderItem> orderItems=new ArrayList<>();
		
		for(CartItem item: cart.getCartItems()) {
			OrderItem orderItem=new OrderItem();
			
			orderItem.setPrice(item.getPrice());
			orderItem.setProduct(item.getProduct());
			orderItem.setQuantity(item.getQuantity());
			orderItem.setSize(item.getSize());
			orderItem.setUserId(item.getUserId());
			orderItem.setDiscountedPrice(item.getDiscountedPrice());
			
			
			OrderItem createdOrderItem=orderItemRepository.save(orderItem);
			
			orderItems.add(createdOrderItem);
		}
		
		
		Order createdOrder=new Order();
		createdOrder.setUser(user);
		createdOrder.setOrderItems(orderItems);
		createdOrder.setTotalPrice(cart.getTotalPrice());
		createdOrder.setTotalDiscountedPrice(cart.getTotalDiscountedPrice());
		createdOrder.setDiscounte(cart.getDiscounte());
		createdOrder.setTotalItem(cart.getTotalItem());
		
		createdOrder.setShippingAddress(address);
		createdOrder.setOrderDate(LocalDateTime.now());
		createdOrder.setOrderStatus(OrderStatus.PENDING);
		createdOrder.getPaymentDetails().setStatus(PaymentStatus.PENDING);
		createdOrder.setCreatedAt(LocalDateTime.now());
		
		Order savedOrder=orderRepository.save(createdOrder);
		
		for(OrderItem item:orderItems) {
			item.setOrder(savedOrder);
			orderItemRepository.save(item);
		}
		
		return savedOrder;
		
	}

	@Override
	public Order placedOrder(Long orderId) throws OrderException {
		Order order=findOrderById(orderId);
		order.setOrderStatus(OrderStatus.PLACED);
		order.getPaymentDetails().setStatus(PaymentStatus.COMPLETED);
		return order;
	}

	@Override
	public Order confirmedOrder(Long orderId) throws OrderException {
		Order order=findOrderById(orderId);
		order.setOrderStatus(OrderStatus.CONFIRMED);
		
		
		return orderRepository.save(order);
	}

	@Override
	public Order shippedOrder(Long orderId) throws OrderException {
		Order order=findOrderById(orderId);
		order.setOrderStatus(OrderStatus.SHIPPED);
		return orderRepository.save(order);
	}

	@Override
	public Order deliveredOrder(Long orderId) throws OrderException {
		Order order=findOrderById(orderId);
		order.setOrderStatus(OrderStatus.DELIVERED);
		return orderRepository.save(order);
	}

	@Override
	public Order cancledOrder(Long orderId) throws OrderException {
		Order order=findOrderById(orderId);
		order.setOrderStatus(OrderStatus.CANCELLED);
		return orderRepository.save(order);
	}

	@Override
	public Order findOrderById(Long orderId) throws OrderException {
		Optional<Order> opt=orderRepository.findById(orderId);
		
		if(opt.isPresent()) {
			return opt.get();
		}
		throw new OrderException("order not exist with id "+orderId);
	}

	@Override
	public List<Order> usersOrderHistory(Long userId) {
		List<Order> orders=orderRepository.getUsersOrders(userId);
		return orders;
	}

	@Override
	public List<Order> getAllOrders() {
		
		return orderRepository.findAll();
	}

	@Override
	public void deleteOrder(Long orderId) throws OrderException {
		Order order =findOrderById(orderId);
		
		orderRepository.deleteById(orderId);
		
	}

}
