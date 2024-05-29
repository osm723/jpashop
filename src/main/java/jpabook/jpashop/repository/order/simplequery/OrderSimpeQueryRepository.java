package jpabook.jpashop.repository.order.simplequery;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderSimpeQueryRepository {

    private final EntityManager em;

    public OrderSimpeQueryRepository(EntityManager em) {
        this.em = em;
    }


    public List<OrderSimpleQueryDto> findOrderDtos() {
        return em.createQuery("select new jpabook.jpashop.repository.order.simplequery.OrderSimpleQueryDto(o.id, m.name, o.orderDate, o.status, d.address)" +
                " from Order o " +
                " join o.member m " +
                " join o.delivery d", OrderSimpleQueryDto.class
        ).getResultList();
    }
}
