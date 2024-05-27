package jpabook.jpashop;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.*;
import jpabook.jpashop.domain.item.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class initDb {

    private final InitService initService;


    @PostConstruct
    public void init() {
        initService.dbInit1();
        initService.dbInit2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void dbInit1() {
            Member member = createMember("userA","seoul","street1","111-111");
            em.persist(member);

            Book book1 = createBook("paper1",20000,100);
            em.persist(book1);

            Book book2 = createBook("paper2",40000,150);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1,10000, 3);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2,20000, 4);

            Delivery delivery = createDelivery(member);
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);
        }

        public void dbInit2() {
            Member member = createMember("userB","incheon","street2","222-222");
            em.persist(member);

            Book book1 = createBook("book1",20000,100);
            em.persist(book1);

            Book book2 = createBook("book2",40000,150);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1,10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2,20000, 2);

            Delivery delivery = createDelivery(member);
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);
        }

        private static Book createBook(String name, int price, int quantity) {
            Book book1 = new Book();
            book1.setName(name);
            book1.setPrice(price);
            book1.setStockQuantity(quantity);
            return book1;
        }

        private static Member createMember(String name, String city, String street,String zipCode) {
            Member member = new Member();
            member.setName(name);
            member.setAddress(new Address(city,street,zipCode));
            return member;
        }

        private Delivery createDelivery(Member member) {
            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            return delivery;
        }
    }

}
