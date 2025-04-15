package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.repository.MessageRepository;
import com.example.repository.AccountRepository;
import com.example.entity.*;
import java.util.*;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private AccountRepository accountRepository;

    public Message CreateMessage(Message message) {
        if (message == null ||
        message.getMessageText() == null || message.getMessageText().trim().isEmpty() ||
        message.getMessageText().length() > 255) {
       
        return null; 
    }

return messageRepository.save(message);


    }
    public boolean isUserExists(int userId){
        return accountRepository.findById(userId).isPresent();
    }

    public List<Message> getAllMessage() {
        return messageRepository.findAll();
    }

    public Message getMessageById(int messageId) {
        return messageRepository.findById(messageId).orElse(null);
    }

    public Message DeleteMessage(int messageId) {
        Message message = getMessageById(messageId);
        if(message != null){
             messageRepository.delete(message);
            return message;

        }
        return null;
    }

    @Transactional
    public Message UpdatedMessage(int messageId, String newMessageText) {
        if (newMessageText == null || newMessageText.trim().isEmpty() || newMessageText.length() > 255) {
            return null; 
        }
        Message existMessage = getMessageById(messageId);
        if (existMessage == null){
            return null;
        }
        existMessage.setMessageText(newMessageText);
        return messageRepository.save( existMessage);
    }

    public List<Message> getMessageByUser(int accountId) {
        return messageRepository.findByPostedBy(accountId);
    }
}
