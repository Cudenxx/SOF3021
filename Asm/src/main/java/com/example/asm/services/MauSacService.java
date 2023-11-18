package com.example.asm.services;

import com.example.asm.entity.ChucVu;
import com.example.asm.entity.MauSac;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface MauSacService {

    public List<MauSac> findAll();
    public MauSac getById(UUID id);
    public MauSac detail(UUID id);
    public void add(MauSac mauSac);
    public void update(MauSac mauSac);
    public void deleteById(UUID id);
    public Page<MauSac> findAllPage(int page);
    public String maMSCount();


}
