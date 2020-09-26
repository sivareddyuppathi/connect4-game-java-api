package com.connect4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.connect4.entity.GameDetails;

@Repository
public interface GameDetailsRepository extends JpaRepository<GameDetails, Long> {

	GameDetails findByUniqueGameId(String uniqueGameId);

}
