package com.hostel.maintenance.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "maintenance_requests")
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String studentName;

    @Column(nullable = false)
    private String rollNo;

    private String studentEmail;
    private String studentPhone;

    @Column(nullable = false)
    private String hostelBlock;

    @Column(nullable = false)
    private String roomNo;

    @Column(nullable = false)
    private String category;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(nullable = false)
    private String status = "Pending";

    @Column(nullable = false)
    private String priority = "Medium";

    // Staff / Warden resolution details
    private String assignedStaff;

    @Column(columnDefinition = "TEXT")
    private String resolutionRemarks;

    private LocalDateTime createdAt;
    private LocalDateTime resolvedAt;

    public Complaint() {
        this.createdAt = LocalDateTime.now();
        this.status = "Pending";
        this.priority = "Medium";
    }

    public Complaint(String studentName, String rollNo, String studentEmail, String studentPhone, 
                     String hostelBlock, String roomNo, String category, String description, String priority) {
        this.studentName = studentName;
        this.rollNo = rollNo;
        this.studentEmail = studentEmail;
        this.studentPhone = studentPhone;
        this.hostelBlock = hostelBlock;
        this.roomNo = roomNo;
        this.category = category;
        this.description = description;
        this.priority = priority != null ? priority : "Medium";
        this.status = "Pending";
        this.createdAt = LocalDateTime.now();
    }

    @PrePersist
    protected void onCreate() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
        if (this.status == null) {
            this.status = "Pending";
        }
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getStudentPhone() {
        return studentPhone;
    }

    public void setStudentPhone(String studentPhone) {
        this.studentPhone = studentPhone;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
        if ("Resolved".equalsIgnoreCase(status) && this.resolvedAt == null) {
            this.resolvedAt = LocalDateTime.now();
        } else if (!"Resolved".equalsIgnoreCase(status)) {
            this.resolvedAt = null;
        }
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getAssignedStaff() {
        return assignedStaff;
    }

    public void setAssignedStaff(String assignedStaff) {
        this.assignedStaff = assignedStaff;
    }

    public String getResolutionRemarks() {
        return resolutionRemarks;
    }

    public void setResolutionRemarks(String resolutionRemarks) {
        this.resolutionRemarks = resolutionRemarks;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getResolvedAt() {
        return resolvedAt;
    }

    public void setResolvedAt(LocalDateTime resolvedAt) {
        this.resolvedAt = resolvedAt;
    }
}
