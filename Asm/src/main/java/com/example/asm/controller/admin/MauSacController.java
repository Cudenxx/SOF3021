package com.example.asm.controller.admin;

import com.example.asm.entity.ChucVu;
import com.example.asm.entity.MauSac;
import com.example.asm.services.MauSacService;
import com.example.asm.services.impl.MauSacServiceImpl;
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
@RequestMapping("/admin/mau-sac")
public class MauSacController {
    @Autowired
    private MauSac mauSac;

    @Autowired
    private MauSacService mauSacService = new MauSacServiceImpl();
    @Autowired
    private HttpSession session;

    @GetMapping("/index")
    public String index(Model model, @RequestParam(defaultValue = "0", name = "page") int page) {
        session.setAttribute("thongBao", "");
        Page<MauSac> list = mauSacService.findAllPage(page);
        model.addAttribute("list", list);
        model.addAttribute("view", "/WEB-INF/views/admin/mau-sac/index.jsp");
        return "admin/layout";
    }

    @GetMapping("/create")
    public String create(
            Model model
    ) {
        model.addAttribute("mauSac", mauSac);
        model.addAttribute("view", "/WEB-INF/views/admin/mau-sac/create.jsp");
        return "admin/layout";
    }

    @PostMapping("/add")
    public String add(
            @Valid @ModelAttribute("mauSac") MauSac mauSac, BindingResult bindingResult, Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("view", "/WEB-INF/views/admin/mau-sac/create.jsp");
            return "admin/layout";
        }
        mauSac.setMa(mauSacService.maMSCount());
        mauSacService.add(mauSac);
        return "redirect:/admin/mau-sac/index";
    }

    @GetMapping("/edit/{id}")
    public String edit(
            Model model, @PathVariable("id") UUID id
    ) {
        model.addAttribute("mauSac", mauSacService.detail(id));
        model.addAttribute("view", "/WEB-INF/views/admin/mau-sac/update.jsp");
        return "admin/layout";
    }

    @PostMapping("/update/{id}")
    public String update(
            @Valid @ModelAttribute("mauSac") MauSac mauSac, BindingResult bindingResult, Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("view", "/WEB-INF/views/admin/mau-sac/update.jsp");
            return "admin/layout";
        }
        mauSacService.update(mauSac);
        return "redirect:/admin/mau-sac/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(
            @PathVariable("id") UUID id
    ) {
        mauSacService.deleteById(id);
        return "redirect:/admin/mau-sac/index";
    }
}
