package com.cround.cgpt.service;

import org.springframework.stereotype.Service;

import com.cround.cgpt.dao.ChatRepository;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Service
@Data
@Slf4j
public class ChatServiceImpl implements ChatService{
	
	private final ChatRepository chatRepository;

}
