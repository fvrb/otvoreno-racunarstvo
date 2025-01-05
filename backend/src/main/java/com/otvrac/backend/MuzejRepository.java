package com.otvrac.backend;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MuzejRepository extends JpaRepository<Muzej, Integer> {

    List<Muzej> findByDrzava(String drzava);

    List<Muzej> findByTipMuzeja(String type);

    List<Muzej> findByGrad(String city);
}
