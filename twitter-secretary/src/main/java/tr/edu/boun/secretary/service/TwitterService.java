package tr.edu.boun.secretary.service;

import org.springframework.social.twitter.api.CursoredList;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Service;
import tr.edu.boun.secretary.domain.Follower;
import tr.edu.boun.secretary.repository.FollowerRepository;

import javax.annotation.Resource;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TwitterService {

    private final Twitter twitter;

    @Resource
    private FollowerRepository followerRepository;

    @Inject
    public TwitterService (Twitter twitter) {
        this.twitter = twitter;
    }

    public List<TwitterProfile> getUnfollowers() {
        Long profileId = twitter.userOperations().getProfileId();
        CursoredList<Long> cursoredList = twitter.friendOperations().getFollowerIds();
        List<Follower> followers = followerRepository.findByUserId(profileId);
        List<Long> unfollowers = followers.stream()
                .filter(follower -> !cursoredList.contains(follower.getFollowerId()))
                .map(Follower::getFollowerId)
                .collect(Collectors.toList());
        saveNewFollowers(profileId, cursoredList, followers);
        deleteUnfollowers(profileId, unfollowers);
        return getProfiles(new ArrayList<>(unfollowers));
    }

    private void saveNewFollowers(Long profileId, CursoredList<Long> currentFollowers, List<Follower> followers) {
        List<Long> followerIds = followers.stream().map(Follower::getFollowerId).collect(Collectors.toList());
        currentFollowers.stream().filter(id -> !followerIds.contains(id))
                .forEach(currentFollower -> {
                    Follower follower = Follower.builder().userId(profileId)
                            .followerId(currentFollower)
                            .build();
                    followerRepository.save(follower);
                } );
    }

    private void deleteUnfollowers(Long profileId, List<Long> unfollowers) {
        List<Follower> followers = followerRepository.findByUserIdAndFollowerIdIn(profileId, unfollowers);
        followerRepository.delete(followers);
    }

    public List<TwitterProfile> getAllFriends() {
        CursoredList<Long> cursoredList;
        cursoredList = twitter.friendOperations().getFriendIds();
        return getProfiles(new ArrayList<>(cursoredList));
    }

    public List<TwitterProfile> getAllFollowers() {
        CursoredList<Long> cursoredList;
        cursoredList = twitter.friendOperations().getFollowerIds();
        return getProfiles(new ArrayList<>(cursoredList));
    }

    private List<TwitterProfile> getProfiles(ArrayList<Long> cursoredList) {
        List<TwitterProfile> followers = new ArrayList<>();
        for(int i = 0; i< cursoredList.size(); i+=100){
            followers.addAll(getProfiles(cursoredList, i));
        }
        return followers;
    }

    private List<TwitterProfile> getProfiles(ArrayList<Long> cursoredList, int start){
        int end = Math.min(start+100, cursoredList.size());
        Long[] ids = cursoredList.subList(start, end).toArray(new Long[0]);
        long[] idArray = Arrays.stream(ids).mapToLong(Long::longValue).toArray();
        return twitter.userOperations().getUsers(idArray);
    }
}
