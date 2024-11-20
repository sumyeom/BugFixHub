package com.example.bugfixhub.controller.friend;

import com.example.bugfixhub.dto.friend.FriendReqDto;
import com.example.bugfixhub.dto.friend.FriendReqStatusDto;
import com.example.bugfixhub.dto.user.UserResDto;
import com.example.bugfixhub.entity.friend.Friend;
import com.example.bugfixhub.service.friend.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/friends")
public class FriendController {

    private final FriendService friendService;

    //1. 친구 추가(createFriendRequest)
    @PostMapping
    public ResponseEntity<Friend> createFriendRequest(@RequestBody FriendReqDto friendReqDto, @SessionAttribute UserResDto userResDto ) {

        Friend friend = friendService.createFriendRequest(
                friendReqDto.getFollowingId(),
                userResDto.getId()  //두개 아이디 넘어감

        );

        return ResponseEntity.status(HttpStatus.CREATED).body(friend);

    }


//     2. 친구 요청 상태 변경
//    @PatchMapping("/{id}")
//    public ResponseEntity<Friend> checkFriendRequest(
//            @PathVariable Long id,
//            @RequestBody FriendReqStatusDto friendReqStatusDto) {
//
//        Friend friend = friendService.checkFriendRequest(id, friendReqStatusDto.getStatus());
//
//        return ResponseEntity.ok(friend);
//
//    }

}