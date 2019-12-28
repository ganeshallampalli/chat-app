package com.chat.datamodel;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author ganeshallampalli
 */

@Data
@Entity
@Table(name = "chat_doc", schema = "public")
public class ChatDoc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String chatId;

    private String initiatedFrom;

    private String initiatedTo;

    private Date createdOn;

    private Date updatedOn;

}
