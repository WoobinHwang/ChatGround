package com.cround.cgpt.user;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DynamicInsert
@DynamicUpdate
public class UserList {

	@Id
	@Column(length = 45)
    private String username;

	@Column(length = 45)
	private String password;
	
	@Column(unique = true, length = 45)
	private String nickname;
	
	@Column(unique = true, length = 45)
	private String email;
	
	@ColumnDefault("0")
	private Integer reportedCount;
	
}
