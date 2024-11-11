package com.exe.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exe.app.entity.HistorialPsicosocial;

@Repository("HistorialPsicosocialRepository")
public interface HistorialPsicosocialRepository extends JpaRepository<HistorialPsicosocial, Long>{

}

