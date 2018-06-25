package demo.repository;

import demo.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

    @Query("select distinct p from Project p left outer join fetch p.memberList order by p.id")
    List<Project> findJoin();

}
