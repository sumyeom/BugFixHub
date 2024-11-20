package com.example.bugfixhub.service.friend;

import com.example.bugfixhub.entity.friend.Friend;
import com.example.bugfixhub.entity.user.User;
import com.example.bugfixhub.repository.friend.FriendRepository;
import com.example.bugfixhub.repository.user.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Getter
@Service
@RequiredArgsConstructor
public class FriendService {

    private final FriendRepository friendRepository;
    private final UserRepository userRepository;

    /**
     * 친구 추가 :
     * 사용자 존재 여부 확인 후
     * unChecked 상태로 데이터 전송
     */
    public Friend createFriendRequest(Long follower, Long following) {

        User followingId = userRepository.findByIdOrElseThrow(following);
        User followerId = userRepository.findByIdOrElseThrow(follower);

        Friend friendRequestStatus = new Friend();
        friendRequestStatus.setFollowing(followingId);
        friendRequestStatus.setFollower(followerId);
        friendRequestStatus.setStatus("unChecked");

        return friendRepository.save(friendRequestStatus);
    }



    //. 2.친구 요청 확인(수락, 거절하기)
    // id에 해당 하는 요청이 없는 경우 예외를 발생 시킨다.
    public Friend checkFriendRequest(Long id, String status) {
        Friend friend = friendRepository.findById(id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "탈퇴한 사용자입니다."));

        // accepted(수락), rejected(거절) 중 하나인지 확인, 만약 둘다 아니라면 오류 전송
        if (!status.equals("accepted") && !status.equals("rejected")) {

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이미 전송 또는 추가된 사용자입니다.");

        }

        friend.setStatus(status);
        return friendRepository.save(friend);
    }


    // 3. 친구 요청 삭제 및 받은 요청 삭제
    public void deleteFriendRequest(Long id) {
        // 친구 요청이 존재하는지 확인
        if (!friendRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 친구 요청입니다.");

        }

        // 친구 요청 삭제
        friendRepository.deleteById(id);
    }


    //4. 친구 요청 리스트 전체 받기
    public List<Friend> getFriendRequests(User receiveUser, User requestUser, String status) {
        return friendRepository.findByFollowerOrFollowingAndStatus(receiveUser, requestUser, status);
    }

}
