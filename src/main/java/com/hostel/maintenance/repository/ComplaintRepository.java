package com.hostel.maintenance.repository;

import com.hostel.maintenance.model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
    List<Complaint> findByStatus(String status);
    List<Complaint> findByRollNo(String rollNo);
    List<Complaint> findByStudentEmail(String studentEmail);
    List<Complaint> findByCategory(String category);
    List<Complaint> findByHostelBlock(String hostelBlock);
}
