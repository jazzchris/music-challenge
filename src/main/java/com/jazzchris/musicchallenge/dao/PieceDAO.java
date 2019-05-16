package com.jazzchris.musicchallenge.dao;

import java.util.List;

import com.jazzchris.musicchallenge.entity.Piece;

public interface PieceDAO {

	public List<Piece> getPieces();
	
	public Piece getPiece(int id);
	
	public void savePiece(Piece piece);
	
	public void deletePiece(int id);

	public List<Piece> getPiecesByComposer(int theId);
}
