package com.chat.datamodel.repository;

import com.chat.datamodel.MessageHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ganeshallampalli
 */

@Repository
public interface MessageHistoryRepository extends CrudRepository<MessageHistory, Integer> {

}
