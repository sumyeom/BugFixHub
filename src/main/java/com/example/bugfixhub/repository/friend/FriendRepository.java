package com.example.bugfixhub.repository.friend;

import com.example.bugfixhub.entity.friend.Friend;
import com.example.bugfixhub.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friend, Long> {

//    // 사용자가 보낸 요청 중 지정된 status만 필터링 한다.
//    List<Friend> findByFollowerAndStatus(User follower, String status);
//
//    // 사용자가 받은 요청 중 지정된 status만 필터링 한다.
//    List<Friend> findByFollowingAndStatus(User following, String status);

    // 사용자가 요청한 또는 요청 받은 친구 요청 중 지정된 status만 조회한다.
    List<Friend> findByFollowerOrFollowingAndStatus(User receiveUser, User requestUser, String status);

}
