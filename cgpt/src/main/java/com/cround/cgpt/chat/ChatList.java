package com.cround.cgpt.chat;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;

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
	private Integer chat_num;
	
    @Column(length = 1000)
    private String contents;
    
    private LocalDateTime localDateTime;
    
//    @ManyToOne
//	@JoinColumn(name = "username")
//	@Column(length = 45)
//	private UserList whiter;
    // private String username;
    
    
	@Column(length = 45)
	@ManyToOne
	@JoinColumn(table = "USER_LIST")
	private UserList whiter;
	
}
