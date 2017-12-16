package tr.edu.boun.secretary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.edu.boun.secretary.domain.Account;
import tr.edu.boun.secretary.domain.Follower;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Repository
public interface FollowerRepository extends JpaRepository<Follower, Integer> {

    List<Follower> findByUserId(Long userId);

    List<Follower> findByUserIdAndFollowerIdIn(Long userId, Collection<Long> followerIds);
}
