package com.example.asm.controller.admin;

import com.example.asm.entity.ChucVu;
import com.example.asm.entity.DongSp;
import com.example.asm.services.DongSPService;
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
@RequestMapping("/admin/dong-sp")
public class DongSPController {
    @Autowired
    private DongSPService dongSPService;

    @Autowired
    private DongSp dongSP;
    @Autowired
    private HttpSession session;

    @GetMapping("/index")
    public String index(Model model, @RequestParam(defaultValue = "0", name = "page") int page) {
        session.setAttribute("thongBao", "");
        Page<DongSp> list = dongSPService.findAllPage(page);
        model.addAttribute("list", list);
        model.addAttribute("view", "/WEB-INF/views/admin/dong-sp/index.jsp");
        return "admin/layout";
    }

    @GetMapping("/create")
    public String create(Model model, DongSp dongSp) {
        model.addAttribute("dongSp", dongSp);
        model.addAttribute("view", "/WEB-INF/views/admin/dong-sp/create.jsp");
        return "admin/layout";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("dongSp") DongSp dongSp, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("view", "/WEB-INF/views/admin/dong-sp/create.jsp");
            return "admin/layout";
        }
        dongSp.setMa(dongSPService.maDSPCount());
        dongSPService.add(dongSp);
        session.setAttribute("thongBao", "Thêm thành công");
        return "redirect:/admin/dong-sp/index";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("dongSp", dongSPService.detail(id));
        model.addAttribute("view", "/WEB-INF/views/admin/dong-sp/update.jsp");
        return "admin/layout";
    }

    @PostMapping("/update/{id}")
    public String update(@Valid @ModelAttribute("dongSp") DongSp dongSp, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("view", "/WEB-INF/views/admin/dong-sp/update.jsp");
            return "admin/layout";
        }
        dongSPService.update(dongSp);
        session.setAttribute("thongBao", "Cập nhật thành công");
        return "redirect:/admin/dong-sp/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") UUID id) {
        dongSPService.deleteById(id);
        return "redirect:/admin/dong-sp/index";
    }

}
