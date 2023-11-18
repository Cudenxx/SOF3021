package com.example.asm.controller.user;

import com.example.asm.entity.ChiTietSp;
import com.example.asm.services.ChiTietSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class SanPhamUserController {
    @Autowired
    private ChiTietSanPhamService chiTietSanPhamService;

    @GetMapping("san-pham")
    public String sanPham(Model model,@RequestParam(defaultValue = "0" ,name = "page") int page) {
        Page<ChiTietSp> chiTietSPPage = chiTietSanPhamService.findAllPage(page);
        model.addAttribute("sanPham", chiTietSPPage);
        model.addAttribute("view", "/WEB-INF/views/user/san-pham/sanpham.jsp");
        model.addAttribute("banner", "/WEB-INF/views/user/banner.jsp");
        return "user/layout";
    }

    @GetMapping("chi-tiet-san-pham/{id}")
    public String chiTietSanPham(@PathVariable("id") UUID id, Model model) {
        String tenDongSp = chiTietSanPhamService.findTenDongSP(id);
        List<ChiTietSp> list = chiTietSanPhamService.findBySanPhamId(id);
        List<ChiTietSp> listByCategoty = chiTietSanPhamService.findByTenDongSP(tenDongSp);
        model.addAttribute("list", list);
        model.addAttribute("listByCategoty", listByCategoty);
        model.addAttribute("view", "/WEB-INF/views/user/san-pham/chi-tiet-san-pham.jsp");
        return "user/layout";
    }
}
