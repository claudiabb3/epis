package com.epigestion.epi.Repositories;

import com.epigestion.epi.Models.Epi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEpiRepository extends JpaRepository<Epi, Integer> {
}
