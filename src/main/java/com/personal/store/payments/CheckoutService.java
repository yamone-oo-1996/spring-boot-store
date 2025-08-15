package com.personal.store.payments;

import com.personal.store.carts.CartEmptyException;
import com.personal.store.carts.CartNotFoundException;
import com.personal.store.carts.CartRepository;
import com.personal.store.carts.CartService;
import com.personal.store.orders.Order;
import com.personal.store.orders.OrderRepository;
import com.personal.store.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CheckoutService {
    private final CartRepository cartRepository;
    private final AuthService authService;
    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final PaymentGateway paymentGateway;

    @Transactional
    public CheckoutResponse checkout(CheckoutRequest request) {
        var cart = cartRepository.getCartWithItems(request.getCartId()).orElse(null);
        if (cart == null) {
            throw new CartNotFoundException();
        }

        if (cart.isEmpty()) {
            throw new CartEmptyException();
        }

        var order = Order.fromCart(cart, authService.getCurrentUser());

        orderRepository.save(order);

        try {
            var session = paymentGateway.createCheckoutSession(order);

            cartService.clearCart(cart.getId());

            return new CheckoutResponse(order.getId(), session.getCheckoutUrl());
        } catch (PaymentException e) {
            orderRepository.delete(order);
            throw e;
        }
    }

    public void handleWebhookEvent(WebhookRequest request) {
        paymentGateway.parseWebhookRequest(request)
                .ifPresent(paymentRequest -> {
                    var order = orderRepository.findById(paymentRequest.getOrderId()).orElseThrow();
                    order.setStatus(paymentRequest.getPaymentStatus());
                    orderRepository.save(order);
                });
    }
}
