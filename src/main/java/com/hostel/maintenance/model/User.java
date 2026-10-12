package com.hostel.maintenance.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String role; // "STUDENT" or "WARDEN"

    private String rollNo;
    private String hostelBlock;
    private String roomNo;
    private String phone;

    public User() {}

    public User(String email, String password, String name, String role, 
                String rollNo, String hostelBlock, String roomNo, String phone) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role != null ? role.toUpperCase() : "STUDENT";
        this.rollNo = rollNo;
        this.hostelBlock = hostelBlock;
        this.roomNo = roomNo;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getHostelBlock() {
        return hostelBlock;
    }

    public void setHostelBlock(String hostelBlock) {
        this.hostelBlock = hostelBlock;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
