package com.example.asm.services;


import com.example.asm.entity.ChucVu;
import com.example.asm.entity.DongSp;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface DongSPService {

    public List<DongSp> getAll();

    public DongSp getById(UUID id);

    public DongSp detail(UUID id);

    public void add(DongSp dongSp);

    public void update(DongSp dongSp);

    public void deleteById(UUID id);

    public Page<DongSp> findAllPage(int page);
    public String maDSPCount();


}
