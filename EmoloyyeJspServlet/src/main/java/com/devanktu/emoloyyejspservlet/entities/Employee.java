package com.devanktu.emoloyyejspservlet.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @Column(nullable = false,length = 100,unique = true)
    private String id;

    @Column(nullable = false,length = 64)
    private String name;

    @Column(nullable = false)
    private Date birthday;

    @Column(nullable = false, length=11)
    private String phone;

    @Column(nullable = false)
    private String email;

    public Employee(String id, String name, Date birthday, String phone, String email) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.phone = phone;
        this.email = email;
    }

    public Employee() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
