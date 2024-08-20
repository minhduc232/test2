package com.example.baitest2.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "username",length = 150)
    private String username;

    @Column(name = "Password",length = 50)
    private String password;

    @Column(name = "Email",length = 150)
    private String email;

    @Column(name = "Fullname",length = 150)
    private String fullname;

    @Column(name = "status")
    private boolean status;
}
