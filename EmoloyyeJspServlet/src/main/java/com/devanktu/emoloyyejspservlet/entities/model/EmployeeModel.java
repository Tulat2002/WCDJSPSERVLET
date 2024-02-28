package com.devanktu.emoloyyejspservlet.entities.model;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public class EmployeeModel {

    private String id;

    @NotBlank(message = "Tên người dùng không được để trống")
    private String name;

    private Date birthday;

    @NotBlank(message = "Tên người dùng không được để trống")
    private String phone;

    @NotBlank(message = "Tên người dùng không được để trống")
    private String email;

    public EmployeeModel() {
    }

    public EmployeeModel(String id, String name, Date birthday, String phone, String email) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.phone = phone;
        this.email = email;
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
