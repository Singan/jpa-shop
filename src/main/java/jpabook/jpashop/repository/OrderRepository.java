package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Orders;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
    private final EntityManager em;

    public Long save(Orders orders){
        em.persist(orders);
        return orders.getId();
    }

    public Orders find(Long id){
        return em.find(Orders.class,id);
    }
    public List<Orders> searchOrders(OrdersSearch ordersSearch){

        return em.createQuery(
                "select o from Orders o join fetch o.member m " +
                        "where o.orderstatus = :status and m.username like :name",Orders.class)
                .setParameter("status",ordersSearch.getOrderstatus())
                .setParameter("name",ordersSearch.getUserName())
                .setMaxResults(1000).getResultList();
    }
    //JPA Criteria

    public List<Orders> findByCriteria(OrdersSearch ordersSearch){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Orders> cq = cb.createQuery(Orders.class);
        Root<Orders> o = cq.from(Orders.class);
        Join<Object,Object> m = o.join("member", JoinType.INNER);
        List<Predicate> criteria = new ArrayList<>();

        if(ordersSearch.getOrderstatus() != null){
            Predicate status = cb.equal(o.get("status"),ordersSearch.getOrderstatus());
            criteria.add(status);
        }

        if(ordersSearch.getUserName() != null){
            Predicate status = cb.like(m.get("name"),"%"+ordersSearch.getUserName()+"%");
            criteria.add(status);
        }

        cq.where(cb.and(
                criteria.toArray(new Predicate[criteria.size()])
        ));
        TypedQuery<Orders> query = em.createQuery(cq).setMaxResults(1000);
        return query.getResultList();
    }
}
