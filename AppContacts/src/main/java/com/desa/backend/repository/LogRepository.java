package com.desa.backend.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desa.backend.entity.Log;

@Repository("logRepository")
public interface LogRepository extends JpaRepository<Log, Serializable> {

}
