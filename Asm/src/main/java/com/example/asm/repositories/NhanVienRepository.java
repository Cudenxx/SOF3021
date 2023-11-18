package com.example.asm.repositories;

import com.example.asm.entity.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface  NhanVienRepository extends JpaRepository<NhanVien, UUID> {
    @Query("select nv from NhanVien nv where nv.email =:email and nv.matKhau =:password")
    public NhanVien login(String email, String password);

    public boolean existsNhanVienByMa(String ma);
}
