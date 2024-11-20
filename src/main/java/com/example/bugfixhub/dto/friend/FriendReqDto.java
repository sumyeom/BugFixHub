package com.example.bugfixhub.dto.friend;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;


@Getter
public class FriendReqDto {

    @NotNull(message = "followingId는 필수 값 입니다.")
    private final Long followingId;      //친구 요청을 보낸 사용자 ID


    @JsonCreator
    public FriendReqDto(Long followingId) {
        this.followingId = followingId;
    }


//    @NotNull(message = "followerId는 필수 값 입니다.")
//    private Long followerId;       //친구 요청을 받은 사용자 ID

//    @NotNull(message = "followerId는 필수 값 입니다.")
//    private String status;        //상태: unChecked(미확인), accepted(수락), rejected(거절)

}
