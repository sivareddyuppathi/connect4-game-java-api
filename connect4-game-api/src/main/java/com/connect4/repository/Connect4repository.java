package com.connect4.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.connect4.entity.Connect4;

@Repository
public interface Connect4repository extends JpaRepository<Connect4, Long> {

	List<Connect4> findByUniqueGameId(String uniqueGameId);

	

}
