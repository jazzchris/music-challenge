package com.jazzchris.musicchallenge.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jazzchris.musicchallenge.entity.Composer;

public interface ComposerRepository extends JpaRepository<Composer, Integer> {

}
