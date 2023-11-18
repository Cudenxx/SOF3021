package com.example.asm.controller.admin;

import com.example.asm.entity.ChucVu;
import com.example.asm.entity.KhachHang;
import com.example.asm.services.KhachHangService;
import com.example.asm.services.impl.KhachHangServiceImpl;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
@RequestMapping("/admin/khach-hang")
public class KhachHangController {
    @Autowired
    private KhachHangService khachHangService = new KhachHangServiceImpl();
    @Autowired
    private KhachHang khachHang;
    @Autowired
    private HttpSession session;

    @GetMapping("index")
    public String index(Model model, @RequestParam(defaultValue = "0", name = "page") int page){
        session.setAttribute("thongBao", "");
        Page<KhachHang> list = khachHangService.findAllPage(page);
        model.addAttribute("list", list);
        model.addAttribute("view", "/WEB-INF/views/admin/khach-hang/index.jsp");
        return "admin/layout";
    }

    @GetMapping("create")
    public String create(Model model){
        model.addAttribute("khachHang", khachHang);
        model.addAttribute("view", "/WEB-INF/views/admin/khach-hang/create.jsp");
        return "admin/layout";
    }

    @PostMapping("add")
    public String add( @Valid @ModelAttribute("khachHang") KhachHang khachHang, BindingResult bindingResult, Model model){
        if (khachHang.getSdt().trim().isEmpty() || khachHang.getDiaChi().trim().isEmpty()) {
            model.addAttribute("error", "Không được để trống");
            model.addAttribute("view", "/WEB-INF/views/admin/khach-hang/create.jsp");
            return "admin/layout";
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("view", "/WEB-INF/views/admin/khach-hang/create.jsp");
            return "admin/layout";
        }
        khachHang.setMa(khachHangService.maKHCount());
        khachHangService.add(khachHang);
        return "redirect:/admin/khach-hang/index";
    }

    @GetMapping("edit/{id}")
    public String edit(Model model, @PathVariable("id") UUID id){;
        model.addAttribute("khachHang", khachHangService.detail(id));
        model.addAttribute("view", "/WEB-INF/views/admin/khach-hang/update.jsp");
        return "admin/layout";
    }

    @PostMapping("update/{id}")
    public String update(
            @Valid @ModelAttribute("khachHang") KhachHang khachHang, BindingResult bindingResult,Model model
    )
    {
        if (khachHang.getSdt().trim().isEmpty() || khachHang.getDiaChi().trim().isEmpty()) {
            model.addAttribute("error", "Không được để trống");
            model.addAttribute("view", "/WEB-INF/views/admin/khach-hang/update.jsp");
            return "admin/layout";
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("view", "/WEB-INF/views/admin/khach-hang/update.jsp");
            return "admin/layout";
        }
        khachHangService.update(khachHang);
        return "redirect:/admin/khach-hang/index";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") UUID id){
        khachHangService.deleteById(id);
        return "redirect:/admin/khach-hang/index";
    }

}
