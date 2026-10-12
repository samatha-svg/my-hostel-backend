package com.hostel.maintenance.controller;

import com.hostel.maintenance.model.Complaint;
import com.hostel.maintenance.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/complaints")
@CrossOrigin(origins = "*")
public class ComplaintController {

    private final ComplaintService complaintService;

    @Autowired
    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    // READ: Get all complaints (Warden view)
    @GetMapping
    public ResponseEntity<List<Complaint>> getAllComplaints() {
        return ResponseEntity.ok(complaintService.getAllComplaints());
    }

    // READ: Get single complaint by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getComplaintById(@PathVariable Long id) {
        return complaintService.getComplaintById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // READ: Get complaints by Roll No (Student personal tracking)
    @GetMapping("/student/{rollNo}")
    public ResponseEntity<List<Complaint>> getComplaintsByRollNo(@PathVariable String rollNo) {
        return ResponseEntity.ok(complaintService.getComplaintsByRollNo(rollNo));
    }

    // CREATE: Submit new complaint (Student)
    @PostMapping
    public ResponseEntity<Complaint> createComplaint(@RequestBody Complaint complaint) {
        Complaint created = complaintService.createComplaint(complaint);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // UPDATE: Update whole complaint details
    @PutMapping("/{id}")
    public ResponseEntity<?> updateComplaint(@PathVariable Long id, @RequestBody Complaint updatedComplaint) {
        try {
            Complaint result = complaintService.updateComplaint(id, updatedComplaint);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
        }
    }

    // UPDATE: Update status and optional remarks (Warden / Staff)
    @PatchMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(@PathVariable Long id, @RequestBody Map<String, String> statusBody) {
        String status = statusBody.get("status");
        String remarks = statusBody.get("remarks");
        if (status == null || status.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Status field is required"));
        }
        try {
            Complaint updated = complaintService.updateStatus(id, status, remarks);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
        }
    }

    // ASSIGN: Assign maintenance staff / technician (Warden)
    @PatchMapping("/{id}/assign")
    public ResponseEntity<?> assignStaff(@PathVariable Long id, @RequestBody Map<String, String> assignBody) {
        String staff = assignBody.get("assignedStaff");
        if (staff == null || staff.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Staff name is required"));
        }
        try {
            Complaint updated = complaintService.assignStaff(id, staff);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
        }
    }

    // DELETE: Delete complaint by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComplaint(@PathVariable Long id) {
        boolean deleted = complaintService.deleteComplaint(id);
        if (deleted) {
            return ResponseEntity.ok(Map.of("message", "Complaint deleted successfully with ID: " + id));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Complaint not found with ID: " + id));
        }
    }

    // STATS: Get summary counts
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getSummaryStats() {
        return ResponseEntity.ok(complaintService.getSummaryStats());
    }
}
