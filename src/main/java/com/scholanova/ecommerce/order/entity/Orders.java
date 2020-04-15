package com.scholanova.ecommerce.order.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.scholanova.ecommerce.cart.entity.Cart;
import com.sun.xml.bind.v2.TODO;

import javax.persistence.*;
import java.sql.Date;
import java.time.Instant;

@Entity(name="orders")
public class Orders {

    @Id
    @GeneratedValue
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Column
    private String number;

    @Column
    private Date issueDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.CREATED;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="cart_id", referencedColumnName = "id")
    private Cart cart;

    public Orders() {
    }

    public void createOrder(Cart cart){
        //TODO

        this.setCart(cart);
        this.setStatus(OrderStatus.CREATED);
    }

    public void checkout(){
        this.issueDate = new Date(Instant.now().toEpochMilli());
        this.status = OrderStatus.PENDING;
    }

    public void getDiscount(){
        //TODO
        return cart.getTotalPrice();

    }

    public void getOrderPrice(){
        //TODO
        double totalPrice = this.getCart().getTotalPrice().doubleValue();
        double discount = this.getCart().getTotalPrice() * getDiscount();
        return totalPrice - discount;
    }

    public void close(){
        //TODO
        setStatus(OrderStatus.CLOSED);

    }


    public Long getId() {return id;}

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {return number;}

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getIssueDate() {return issueDate;}

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public OrderStatus getStatus() {return status;}

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Cart getCart() {return cart;}

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
