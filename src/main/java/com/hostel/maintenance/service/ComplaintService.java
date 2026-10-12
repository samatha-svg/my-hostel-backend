package com.hostel.maintenance.service;

import com.hostel.maintenance.model.Complaint;
import com.hostel.maintenance.repository.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ComplaintService {

    private final ComplaintRepository complaintRepository;

    @Autowired
    public ComplaintService(ComplaintRepository complaintRepository) {
        this.complaintRepository = complaintRepository;
    }

    public List<Complaint> getAllComplaints() {
        return complaintRepository.findAll();
    }

    public Optional<Complaint> getComplaintById(Long id) {
        return complaintRepository.findById(id);
    }

    public List<Complaint> getComplaintsByRollNo(String rollNo) {
        return complaintRepository.findByRollNo(rollNo);
    }

    public Complaint createComplaint(Complaint complaint) {
        if (complaint.getStatus() == null || complaint.getStatus().isEmpty()) {
            complaint.setStatus("Pending");
        }
        if (complaint.getCreatedAt() == null) {
            complaint.setCreatedAt(LocalDateTime.now());
        }
        return complaintRepository.save(complaint);
    }

    public Complaint updateComplaint(Long id, Complaint updatedDetails) {
        Complaint complaint = complaintRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Complaint not found with id: " + id));

        complaint.setStudentName(updatedDetails.getStudentName());
        complaint.setRollNo(updatedDetails.getRollNo());
        complaint.setStudentPhone(updatedDetails.getStudentPhone());
        complaint.setHostelBlock(updatedDetails.getHostelBlock());
        complaint.setRoomNo(updatedDetails.getRoomNo());
        complaint.setCategory(updatedDetails.getCategory());
        complaint.setDescription(updatedDetails.getDescription());
        complaint.setPriority(updatedDetails.getPriority());

        if (updatedDetails.getAssignedStaff() != null) {
            complaint.setAssignedStaff(updatedDetails.getAssignedStaff());
        }
        if (updatedDetails.getResolutionRemarks() != null) {
            complaint.setResolutionRemarks(updatedDetails.getResolutionRemarks());
        }

        if (updatedDetails.getStatus() != null) {
            complaint.setStatus(updatedDetails.getStatus());
        }

        return complaintRepository.save(complaint);
    }

    public Complaint updateStatus(Long id, String newStatus, String remarks) {
        Complaint complaint = complaintRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Complaint not found with id: " + id));

        complaint.setStatus(newStatus);
        if (remarks != null && !remarks.trim().isEmpty()) {
            complaint.setResolutionRemarks(remarks);
        }
        return complaintRepository.save(complaint);
    }

    public Complaint assignStaff(Long id, String staffName) {
        Complaint complaint = complaintRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Complaint not found with id: " + id));

        complaint.setAssignedStaff(staffName);
        if ("Pending".equalsIgnoreCase(complaint.getStatus())) {
            complaint.setStatus("In Progress");
        }
        return complaintRepository.save(complaint);
    }

    public boolean deleteComplaint(Long id) {
        if (complaintRepository.existsById(id)) {
            complaintRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Map<String, Object> getSummaryStats() {
        List<Complaint> all = complaintRepository.findAll();
        long total = all.size();
        long pending = all.stream().filter(c -> "Pending".equalsIgnoreCase(c.getStatus())).count();
        long inProgress = all.stream().filter(c -> "In Progress".equalsIgnoreCase(c.getStatus())).count();
        long resolved = all.stream().filter(c -> "Resolved".equalsIgnoreCase(c.getStatus())).count();

        Map<String, Object> stats = new HashMap<>();
        stats.put("total", total);
        stats.put("pending", pending);
        stats.put("inProgress", inProgress);
        stats.put("resolved", resolved);
        return stats;
    }
}
