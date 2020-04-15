package com.scholanova.ecommerce.order.entity;

import com.scholanova.ecommerce.cart.entity.Cart;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OrdersTest {

    @Test
    public void checkout_ShouldSetTheDateAndTimeOfTodayInTheOrder(){

        Orders order = new Orders();
        Date dat = new Date(Instant.now().toEpochMilli());

        //when
        order.checkout();

        //then
        assertThat(order.getIssueDate()).isEqualTo(dat);
    }

    @Test
    public void checkout_ShouldSetOrderStatusToPending(){
        Orders order = new Orders();

        //when
        order.checkout();

        //then
        assertThat(order.getStatus()).isEqualTo(OrderStatus.PENDING);
    }

    @Test
    public void checkout_ShouldThrowNotAllowedExceptionIfStatusIsClosed(){
        Orders order = new Orders();

        //when
        order.setStatus(OrderStatus.CLOSED);

        //then
        assertThrows(NotAllowedException.class, () -> {
            order.checkout();
        });
    }

    @Test
    public void checkout_ShouldThrowIllegalArgExceptionIfCartTotalItemsQuantityIsZERO(){
        Orders order = new Orders();

        Cart cart = new Cart();

        order.setCart(cart);

        //then
        assertThrows(IllegalArgumentException.class, () -> {
            order.checkout();
        });
    }

    @Test
    public void setCart_ShouldThrowNotAllowedExceptionIfStatusIsClosed(){
        Orders order = new Orders();

        Cart cart = new Cart();

        order.setStatus(OrderStatus.CLOSED);

        //then
        assertThrows(NotAllowedException.class, () -> {
            order.setCart(cart);
        });
    }

    @Test
    public void createOrder_ShouldSetTheCartInTheOrder(){
        Orders order = new Orders();
        Cart cart = new Cart();

        //when
        order.createOrder(cart);

        //then
        assertThat(order.getCart()).isEqualTo(cart);
    }

    @Test
    public void createOrder_ShouldSetStatusToCreated(){
        Orders order = new Orders();
        Cart cart = new Cart();

        //when
        order.createOrder(cart);

        //then
        assertThat(order.getStatus()).isEqualTo(OrderStatus.CREATED);
    }

    @Test
    public void getDiscount_shouldReturnZEROIFCartTotalPriceIsLessThan100(){

    }

    @Test
    public void getDiscount_shouldReturn5percentIfCartTotalPriceIsMoreOrEqual100(){

    }

    @Test
    public void getOrderPrice_shouldReturnTotalPriceWithDiscount(){

    }

    @Test
    public void close_ShouldSetStatusToClose(){
        Orders order = new Orders();

        //when
        order.close();

        //then
        assertThat(order.getStatus()).isEqualTo(OrderStatus.CLOSED);
    }

}