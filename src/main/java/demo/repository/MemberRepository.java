package demo.repository;

import demo.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    @Query("select distinct m from Member m join fetch m.project p join fetch p.memberList order by m.id")
    List<Member> findJoin();
}
