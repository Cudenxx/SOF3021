package com.example.asm.controller.admin;

import com.example.asm.entity.ChiTietSp;
import com.example.asm.entity.DongSp;
import com.example.asm.entity.MauSac;
import com.example.asm.entity.NSX;
import com.example.asm.entity.SanPham;
import com.example.asm.repositories.ChiTietSPRepository;
import com.example.asm.services.ChiTietSanPhamService;
import com.example.asm.services.DongSPService;
import com.example.asm.services.MauSacService;
import com.example.asm.services.NSXService;
import com.example.asm.services.SanPhamService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin/ctsp")
public class CTSPController {
    @Autowired
    private ChiTietSanPhamService chiTietSPService;
    @Autowired
    private ChiTietSPRepository chiTietSPRepository;
    @Autowired
    private NSXService nsxService;
    @Autowired
    private DongSPService dongSPService;
    @Autowired
    private MauSacService mauSacService;
    @Autowired
    private SanPhamService sanPhamService;
    @Autowired
    public ChiTietSp chiTietSp;

    @GetMapping("/index")
    public String index(Model model, @RequestParam(defaultValue = "0", name = "page") int page) {
        Page<ChiTietSp> list = chiTietSPService.findAllPage(page);
        model.addAttribute("list", list);
        model.addAttribute("view", "/WEB-INF/views/admin/chi-tiet-sp/index.jsp");
        return "admin/layout";
    }

    @GetMapping("/create")
    public String create(Model model) {
        List<MauSac> mauSacList = mauSacService.findAll();
        List<NSX> nsxList = nsxService.getAll();
        List<DongSp> dongSPList = dongSPService.getAll();
        List<SanPham> sanPhamList = sanPhamService.findAll();
        model.addAttribute("action", "/admin/ctsp/store");
        model.addAttribute("mauSacList", mauSacList);
        model.addAttribute("nsxList", nsxList);
        model.addAttribute("dongSPList", dongSPList);
        model.addAttribute("sanPhamList", sanPhamList);
        model.addAttribute("ctsp", chiTietSp);
        model.addAttribute("view", "/WEB-INF/views/admin/chi-tiet-sp/create.jsp");
        return "admin/layout";
    }

    @PostMapping("add")
    public String add(@Valid @ModelAttribute("ctsp") ChiTietSp ChiTietSp, BindingResult bindingResult, Model model) {
        if (bindingResult.hasFieldErrors()) {
            List<MauSac> mauSacList = mauSacService.findAll();
            List<NSX> nsxList = nsxService.getAll();
            List<DongSp> dongSPList = dongSPService.getAll();
            List<SanPham> sanPhamList = sanPhamService.findAll();
            model.addAttribute("mauSacList", mauSacList);
            model.addAttribute("nsxList", nsxList);
            model.addAttribute("dongSPList", dongSPList);
            model.addAttribute("sanPhamList", sanPhamList);
            model.addAttribute("view", "/WEB-INF/views/admin/chi-tiet-sp/create.jsp");
            return "admin/layout";
        }
        try {
            chiTietSPService.add(ChiTietSp);
            return "redirect:/admin/ctsp/index";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") UUID id, Model model) {
        List<MauSac> mauSacList = mauSacService.findAll();
        List<NSX> nsxList = nsxService.getAll();
        List<DongSp> dongSPList = dongSPService.getAll();
        List<SanPham> sanPhamList = sanPhamService.findAll();
        model.addAttribute("mauSacList", mauSacList);
        model.addAttribute("nsxList", nsxList);
        model.addAttribute("dongSPList", dongSPList);
        model.addAttribute("sanPhamList", sanPhamList);
        model.addAttribute("ctsp", chiTietSPService.detail(id));
        model.addAttribute("view", "/WEB-INF/views/admin/chi-tiet-sp/update.jsp");
        return "admin/layout";
    }

    @PostMapping("/update/{id}")
    public String update(
            @Valid @ModelAttribute("ctsp") ChiTietSp chiTietSp, BindingResult bindingResult, Model model
    ) {
        if (bindingResult.hasErrors()) {
            List<MauSac> mauSacList = mauSacService.findAll();
            List<NSX> nsxList = nsxService.getAll();
            List<DongSp> dongSPList = dongSPService.getAll();
            List<SanPham> sanPhamList = sanPhamService.findAll();
            model.addAttribute("mauSacList", mauSacList);
            model.addAttribute("nsxList", nsxList);
            model.addAttribute("dongSPList", dongSPList);
            model.addAttribute("sanPhamList", sanPhamList);
            model.addAttribute("view", "/WEB-INF/views/admin/chi-tiet-sp/update.jsp");
            return "admin/layout";
        }
        chiTietSPService.update(chiTietSp);
        return "redirect:/admin/ctsp/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") UUID id, RedirectAttributes redirectAttributes) {
        try {
            chiTietSPService.deleteById(id);
            redirectAttributes.addFlashAttribute("successMessage", "Xóa thành công.");
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Không thể xóa sản phẩm do có bản ghi liên quan trong bảng Giỏ Hàng.");
        }
        return "redirect:/admin/ctsp/index";
    }
}
