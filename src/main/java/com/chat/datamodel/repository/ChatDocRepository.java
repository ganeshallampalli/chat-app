package com.chat.datamodel.repository;

import com.chat.datamodel.ChatDoc;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author ganeshallampalli
 */
public interface ChatDocRepository extends CrudRepository<ChatDoc,Integer> {

    ChatDoc findByChatId(String chatId);
}
