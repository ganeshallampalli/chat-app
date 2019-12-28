package com.chat.datamodel;

import lombok.Data;

import javax.persistence.*;

/**
 * @author ganeshallampalli
 */

@Data
@Entity
@Table(name = "user_role", schema = "public")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String role;

    private String permissions;

}
