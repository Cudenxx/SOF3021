package com.example.asm.services.impl;

import com.example.asm.entity.NSX;
import com.example.asm.repositories.NSXRepository;
import com.example.asm.services.NSXService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class NSXServiceImpl implements NSXService {
    @Autowired
    private NSXRepository nsxRepository;


    @Override
    public List<NSX> getAll() {
        return nsxRepository.findAll();
    }

    @Override
    public NSX getById(UUID id) {
        if (nsxRepository.findById(id).isPresent()) {
            return nsxRepository.findById(id).get();
        } else {
            return null;
        }
    }

    @Override
    public void add(NSX nsx) {
        if (nsx.getId() == null) {
            nsxRepository.save(nsx);
        }
    }

    @Override
    public void update(NSX nsx) {
        NSX nsxUpdate = nsxRepository.findById(nsx.getId()).get();
        nsxUpdate.setTen(nsx.getTen());
        nsxRepository.save(nsxUpdate);
    }

    @Override
    public NSX detail(UUID id) {
        return nsxRepository.findById(id).get();
    }

    @Override
    public boolean deleteById(UUID id) {
        if (nsxRepository.findById(id).isPresent()) {
            nsxRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Page<NSX> findAllPage(int page) {
        Pageable pageable = PageRequest.of(page, 5);
        return nsxRepository.findAll(pageable);
    }

    @Override
    public String maNSXCount() {
        String code = "";
        List<NSX> list = nsxRepository.findAll();
        if (list.isEmpty()) {
            code = "NSX01";
        } else {
            int max = 0;
            for (NSX nsx : list) {
                String ma = nsx.getMa();
                if (ma.length() >= 4) {
                    int so = Integer.parseInt(ma.substring(3));
                    if (so > max) {
                        max = so;
                    }
                }
            }
            max++;
            if (max < 1000) {
                code = "NSX0" + max;
            } else {
                code = "NSX" + max;
            }
        }
        return code;
    }


}
