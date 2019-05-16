package com.jazzchris.musicchallenge.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jazzchris.musicchallenge.entity.Piece;

public interface PieceRepository extends JpaRepository<Piece, Integer> {

	List<Piece> findAllByComposerId(int theId);

}
