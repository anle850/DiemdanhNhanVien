package com.example.loginmll;

import java.io.Serializable;

public class NguoiDung implements Serializable {
    private String Id, Name, Email;

    public NguoiDung(String id, String name, String email) {
        this.Id = id;
        this.Name = name;
        this.Email = email;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
