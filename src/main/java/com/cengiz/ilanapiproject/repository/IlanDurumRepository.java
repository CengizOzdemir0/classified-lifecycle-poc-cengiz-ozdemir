package com.cengiz.ilanapiproject.repository;

import com.cengiz.ilanapiproject.data.entity.IlanDurum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IlanDurumRepository extends JpaRepository<IlanDurum, Long> {

    List<IlanDurum> findAllByFkIlanId(Long id);
}
