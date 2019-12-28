package com.chat.datamodel;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author ganeshallampalli
 */

@Data
@Entity
@Table(schema = "public")
public class MessageHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String chatId;

    private Date sentTime;

    private String initiatedFrom;

    private String initiatedTo;

    private String message;
}
