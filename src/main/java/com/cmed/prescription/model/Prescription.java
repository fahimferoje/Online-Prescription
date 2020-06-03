package com.cmed.prescription.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "PRESCRIPTION")
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotEmpty(message = "Please provide diagnosis")
    private String diagnosis;

    @Column
    @NotEmpty(message = "Please provide medicines")
    private String medicines;

    @Temporal(TemporalType.DATE)
    @Column(name = "NEXT_VISIT_DATE")
    private Date nextVisitDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "CREATED_AT")
    private Date createdAt;

    @ManyToOne
    private User user;

    @ManyToOne
    //@Column(name = "PATIENT_ID")
    private Patient patient;

    @Transient
    private Long patientId;

    @Transient
    private String nextVisitDateString;

    @Transient
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getMedicines() {
        return medicines;
    }

    public void setMedicines(String medicine) {
        this.medicines = medicine;
    }

    public Date getNextVisitDate() {
        return nextVisitDate;
    }

    public void setNextVisitDate(Date nextVisitDate) {
        this.nextVisitDate = nextVisitDate;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNextVisitDateString() {
        return nextVisitDateString;
    }

    public void setNextVisitDateString(String nextVisitDateString) {
        this.nextVisitDateString = nextVisitDateString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prescription that = (Prescription) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Prescription{" +
                "id=" + id +
                ", diagnosis='" + diagnosis + '\'' +
                ", medicine='" + medicines + '\'' +
                ", nextVisitDate=" + nextVisitDate +
                ", createdAt=" + createdAt +
                ", createdBy=" + user +
                ", patient=" + patient +
                '}';
    }
}
