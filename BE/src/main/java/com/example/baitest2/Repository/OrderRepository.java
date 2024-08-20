package com.example.baitest2.Repository;

import com.example.baitest2.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("select o from User u inner join Order o on u.id = o.user.id where u.id = :user_id")
    List<Order> findByCatalog(@Param("user_id") Long userId);

}
