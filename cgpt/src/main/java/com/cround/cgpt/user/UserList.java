package com.cround.cgpt.user;

import java.util.List;

import org.hibernate.annotations.ColumnDefault;

import com.cround.cgpt.chat.ChatList;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;


//@DynamicInsert
//@DynamicUpdate
@Getter
@Setter
@Entity
public class UserList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userNum;
    
    @Column(unique = true, length = 45)
    private String username;
    
    @Column(length = 45)
    private String password;

    @Column(unique = true, length = 45)
    private String nickname;
    
    @Column(unique = true, length = 45)
    private String email;
    
    @ColumnDefault("0")
    private Integer reportedCount;
	
    @OneToMany(mappedBy = "UserList", cascade = CascadeType.REMOVE)
    private List<ChatList> answerList;
    
}
