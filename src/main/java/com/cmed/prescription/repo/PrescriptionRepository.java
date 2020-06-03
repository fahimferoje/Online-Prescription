package com.cmed.prescription.repo;

import com.cmed.prescription.model.Patient;
import com.cmed.prescription.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {

    List<Prescription> findAll();

    @Query("SELECT ps FROM Prescription ps WHERE ps.createdAt BETWEEN :startDate AND :endDate")
    List<Prescription> findPrescriptionsByDate(@Param("startDate") Date startDate
            , @Param("endDate") Date endDate);
}
