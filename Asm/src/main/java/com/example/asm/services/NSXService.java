package com.example.asm.services;




import com.example.asm.entity.ChucVu;
import com.example.asm.entity.NSX;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface NSXService {

    public List<NSX> getAll();
    public NSX getById(UUID id);
    public NSX detail(UUID id);
    public void add(NSX nsx);
    public void update(NSX nsx);
    public boolean deleteById(UUID id);
    public Page<NSX> findAllPage(int page);
    public String maNSXCount();


}
