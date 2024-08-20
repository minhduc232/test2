package com.example.baitest2.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Setter
@Getter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "Total_qty")
    private int totalQty;

    @Column(name = "Total_amount")
    private Long totalAmount;

    @Column(name = "status")
    private boolean status;


}
