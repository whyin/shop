package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // 스프링 빈으로 해줌
@RequiredArgsConstructor
public class MemberRepository {

    // @PersistenceContext //JPA가 제공하는 표준 어노테이션
    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return  em.find(Member.class, id);
    }

    /**
     * sql은 테이블을 대상으로 쿼리 JPA는 from의 대상이 엔티티
     */
    public List<Member> findAll() {
        return em.createQuery("select m from Member m ", Member.class)
                .getResultList();
    }

    // 이름을 이용한 회원 조회
    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
