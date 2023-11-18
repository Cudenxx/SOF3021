package com.example.asm.repositories;


import com.example.asm.entity.ChiTietSp;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ChiTietSPRepository extends JpaRepository<ChiTietSp, UUID> {
    @Autowired
    EntityManager entityManager = null;

    @Query("select c from ChiTietSp c where c.Id = ?1")
    List<ChiTietSp> findBySanPhamId(UUID id);

    @Query("select c from ChiTietSp c where c.dongSp.ten = ?1")
    List<ChiTietSp> findByTenDongSP(String tenDongSP);

    @Query("select c.dongSp.ten from ChiTietSp c where c.Id = ?1")
    String findTenDongSP(UUID id);

    @Query("select c.soLuongTon from ChiTietSp c where c.Id = ?1")
    Integer getSoLuong(UUID idChiTietSp);


    @Modifying
    @Query("update ChiTietSp c set c.soLuongTon=?1 where c.Id=?2")
    void updateSoLuong(Integer soLuong, UUID idChiTietSp);

}

