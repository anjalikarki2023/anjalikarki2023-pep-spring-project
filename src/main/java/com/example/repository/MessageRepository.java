package com.example.repository;
import com.example.entity.*;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findByPostedBy(int accountId);
    int deleteByMessageId (Integer messageId);

    @Modifying
    @Query ("UPDATE Message m SET m.messageText =?2 WHERE m.id =?1")
    int  UpdatedMessage(int messageId, String newMessageText);
}
