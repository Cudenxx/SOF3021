package com.example.asm.controller.admin;

import com.example.asm.entity.ChucVu;
import com.example.asm.entity.CuaHang;
import com.example.asm.services.CuaHangService;
import com.example.asm.services.impl.CuaHangServiceImpl;
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
@RequestMapping("/admin/cua-hang")
public class CuaHangController {

    @Autowired
    private CuaHangService cuaHangService = new CuaHangServiceImpl();

    @Autowired
    private CuaHang cuaHang;

    @Autowired
    private HttpSession session;

    @GetMapping("/index")
    public String index(Model model, @RequestParam(defaultValue = "0", name = "page") int page) {
        session.setAttribute("thongBao", "");
        Page<CuaHang> list = cuaHangService.findAllPage(page);
        model.addAttribute("list", list);
        model.addAttribute("view", "/WEB-INF/views/admin/cua-hang/index.jsp");
        return "admin/layout";
    }

    @GetMapping("/create")
    public String create(Model model, CuaHang cuaHang) {
        model.addAttribute("cuaHang", cuaHang);
        model.addAttribute("view", "/WEB-INF/views/admin/cua-hang/create.jsp");
        return "admin/layout";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("cuaHang") CuaHang cuaHang, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("view", "/WEB-INF/views/admin/cua-hang/create.jsp");
            return "admin/layout";
        }
        cuaHang.setMa(cuaHangService.maCHCount());
        cuaHangService.add(cuaHang);
        session.setAttribute("thongBao", "Thêm thành công");
        return "redirect:/admin/cua-hang/index";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") UUID id) {
        model.addAttribute("cuaHang", cuaHangService.detail(id));
        model.addAttribute("view", "/WEB-INF/views/admin/cua-hang/update.jsp");
        return "admin/layout";
    }

    @PostMapping("/update/{id}")
    public String update(@Valid @ModelAttribute("cuaHang") CuaHang cuaHang, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("view", "/WEB-INF/views/admin/cua-hang/update.jsp");
            return "admin/layout";
        }
        cuaHangService.update(cuaHang);
        session.setAttribute("thongBao", "Cập nhật thành công");
        return "redirect:/admin/cua-hang/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") UUID id) {
        cuaHangService.deleteById(id);
        return "redirect:/admin/cua-hang/index";
    }
}
