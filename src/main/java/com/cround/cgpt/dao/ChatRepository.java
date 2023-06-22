package com.cround.cgpt.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cround.cgpt.entity.ChatList;

public interface ChatRepository extends JpaRepository<ChatList, Integer> {

}
