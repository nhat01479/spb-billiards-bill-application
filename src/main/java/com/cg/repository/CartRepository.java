package com.cg.repository;


import com.cg.model.Cart;
import com.cg.model.Desk;
import com.cg.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {


    Optional<Cart> findByUser(User user);
    @Query("select new com.cg.model.Cart (" +
            "c.id, c.totalAmount, c.desk, c.user, c.createdAt )" +
            "from Cart as c " +
            "left join Desk as d on c.desk.id = d.id " +
            "where d.id = :deskId and d.status = :boo")
    Optional<Cart> findByDesk(Long deskId, Boolean boo);

}
