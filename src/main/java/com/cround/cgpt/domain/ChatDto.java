package com.cround.cgpt.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChatDto {

	// ENTER : 방에 입장할 때 나오는 환영 메세지
	// TALK : 방에 있는 사람이 채팅을 입력하면 나오는 메시지
	public enum MessageType{
		ENTER, TALK
	}
	
	private MessageType type; // 메시지 타입
	private String receiver;// 수신자
    private String sender;//채팅을 보낸 사람
    private String message;// 메세지
    private String time; // 채팅 발송 시간
	
}
