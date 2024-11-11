package com.exe.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exe.app.entity.Rol;

@Repository("RolRepository")
public interface RolRepository extends JpaRepository<Rol, Long>{

}
