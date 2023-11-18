package com.example.asm.repositories;

import com.example.asm.entity.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, UUID> {

    @Query("SELECT hd.Id FROM HoaDon hd WHERE hd.khachHang.Id = :idKH order by hd.ma desc")
    List<UUID> findIdByKhachHangIdOrderByMaDesc( UUID idKH);
}

