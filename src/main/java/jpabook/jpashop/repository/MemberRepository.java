package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;
    private final OrderRepository orderRepository;
    private final MapperTest mapperTest;
    private final SqlSessionFactory sqlSessionFactory;
    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
//        return em.createQuery("select m from Member m", Member.class)
//                .getResultList();
        return mapperTest.selectAll();
    }

    @Transactional(readOnly = false)
    public void delete(Long id) {
        orderRepository.deleteOrderByMember(id);

        int deletedCount = em.createQuery("delete from Member m where m.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
