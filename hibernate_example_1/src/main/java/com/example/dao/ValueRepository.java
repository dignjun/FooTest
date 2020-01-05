package com.example.dao;

import com.example.entity.Value;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by DINGJUN on 2020.01.05.
 */
public interface ValueRepository extends JpaRepository<Value, String> {
}
