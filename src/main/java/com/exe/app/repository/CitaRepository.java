package com.exe.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exe.app.entity.Cita;

@Repository("CitaRepository")
public interface CitaRepository extends JpaRepository<Cita, Long>{

}
