package com.example.asm.services;




import com.example.asm.entity.ChiTietSp;
import com.example.asm.entity.ChucVu;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface ChucVuService {

    public List<ChucVu> findAll();
    public ChucVu getById(UUID id);
    public void deleteById(UUID id);
    public String maCVCount();
    public ChucVu detail(UUID id);
    public void add(ChucVu chucVu);
    public void update(ChucVu chucVu);

    public Page<ChucVu> findAllPage(int page);


}
