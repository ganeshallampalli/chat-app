package com.chat.datamodel;

import lombok.Data;

import javax.persistence.*;

/**
 * @author ganeshallampalli
 */

@Data
@Entity
@Table(name = "user", schema = "public")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String userId;

    private String name;

    private String address;

    private String phone;

}
