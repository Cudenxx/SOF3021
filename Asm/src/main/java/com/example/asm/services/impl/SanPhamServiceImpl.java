package com.example.asm.services.impl;

import com.example.asm.entity.SanPham;
import com.example.asm.model.SanPhamModel;
import com.example.asm.repositories.SanPhamRepository;
import com.example.asm.services.SanPhamService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

@Service
public class SanPhamServiceImpl implements SanPhamService {

    @Autowired
    private SanPhamRepository sanPhamRepository;
    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<SanPham> findAll() {
        return sanPhamRepository.findAll();
    }

    @Override
    public SanPham getById(UUID id) {
        if (sanPhamRepository.findById(id).isPresent()) {
            return sanPhamRepository.findById(id).get();
        } else {
            return null;
        }

    }

    private SanPham toEntity(SanPhamModel sanPhamModel) {
        return modelMapper.map(sanPhamModel, SanPham.class);
    }

    @Override
    public void add(SanPhamModel sanPhamModel, Path path) {
        SanPham sanPham = toEntity(sanPhamModel);
        sanPham.setAnh(path.toString());
        sanPhamRepository.save(sanPham);
    }

    @Override
    public void update(SanPhamModel sanPhamModel, Path path) {
        SanPham sanPhamUpdate = sanPhamRepository.findById(sanPhamModel.getId()).get();
        sanPhamUpdate.setTen(sanPhamModel.getTen());
        sanPhamUpdate.setAnh(path.toString());
        sanPhamRepository.save(sanPhamUpdate);
    }

    @Override
    public SanPham detail(UUID id) {
        return sanPhamRepository.findById(id).get();
    }

    @Override
    public void deleteById(UUID id) {
        SanPham sanPham = sanPhamRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy sản phẩm với ID: " + id));

        try {
            sanPhamRepository.delete(sanPham);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Không thể xóa sản phẩm với ID: " + id
                    + " do có bản ghi liên quan trong bảng ChiTietSP.");
        }
    }

    @Override
    public Page<SanPham> findAllPage(int page) {
        Pageable pageable = PageRequest.of(page, 5);
        return sanPhamRepository.findAll(pageable);
    }

    @Override
    public String maSPcount() {

        String code = "";
        List<SanPham> list = sanPhamRepository.findAll();
        if (list.isEmpty()) {
            code = "SP01";
        } else {
            int max = 0;
            for (SanPham sanPham : list) {
                String ma = sanPham.getMa();
                if (ma.length() >= 4) {
                    int so = Integer.parseInt(ma.substring(3));
                    if (so > max) {
                        max = so;
                    }
                }
            }
            max++;
            if (max < 1000) {
                code = "SP0" + max;
            } else {
                code = "SP" + max;
            }
        }
        return code;
    }

}
