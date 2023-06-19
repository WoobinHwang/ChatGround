package com.cround.cgpt.chat;

import java.time.LocalDateTime;

import com.cround.cgpt.user.UserList;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ChatList {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer chatNum;

	@ManyToOne
	@JoinColumn(name = "writer")
	private UserList userList;
	
    @Column(length = 1000)
	private String contents;
	
	private LocalDateTime writeTime;
	
}
