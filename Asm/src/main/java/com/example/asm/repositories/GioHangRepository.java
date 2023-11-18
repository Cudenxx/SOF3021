package com.example.asm.repositories;


import com.example.asm.entity.GioHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface GioHangRepository extends JpaRepository<GioHang, UUID> {
    @Query("select gh from GioHang gh where gh.khachHang.Id=:idKhachHang and gh.trangThai=0")
     public GioHang findByKhachHangIdAndTrangThai(UUID idKhachHang);
}
