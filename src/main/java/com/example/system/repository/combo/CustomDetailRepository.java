package com.example.system.repository.combo;

import com.example.system.model.combo.CustomDetail;
import com.example.system.model.requestcontract.RequestContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomDetailRepository extends JpaRepository<CustomDetail, Long> {
    List<CustomDetail> findAllByRequestContract(RequestContract requestContract);
}
