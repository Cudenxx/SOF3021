package com.example.asm.repositories;

import com.example.asm.entity.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang, UUID> {
        @Query("select kh from KhachHang kh where kh.email =:email and kh.matKhau =:password")
        public KhachHang login(String email, String password);
}
