package com.cengiz.ilanapiproject.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "kullanici")
@Getter
@Setter
public class Kullanici {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "ad")
    private String ad;


    @Column(name = "soyad")
    private String soyad;


    @Column(name = "email")
    private String email;

    // auth servisi olusuturulmadigindan dolayi kullanici_password gibi iliskilendirmedim.
    @Column(name = "password")
    private String password;

    @Column(name = "uuid")
    private UUID uuid;
}
