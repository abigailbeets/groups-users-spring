package com.allstate.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String first;
    private String last;
    private String birthday;
    private String nickname;

    private static Map<String, String> nickNames = new HashMap<>();

    public User() {

        nickNames.put("2","Roaring");
        nickNames.put("3","Dapper");
        nickNames.put("4","Swell");
        nickNames.put("5","Cool");
        nickNames.put("6","Neat");
        nickNames.put("7","Chill");
        nickNames.put("8","Rad");
        nickNames.put("9","Sweet");
        nickNames.put("0","Dope");
        nickNames.put("1","Lit");
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getNickname() {
        String nickname = "";

        String[] birthdayYear = this.birthday.split("-");
        String fullYear = birthdayYear[2];
        String[] decade = fullYear.split("");
        String lookupDigit = decade[2];

        return this.nickNames.get(lookupDigit) + " " + this.first;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;

    }
}
