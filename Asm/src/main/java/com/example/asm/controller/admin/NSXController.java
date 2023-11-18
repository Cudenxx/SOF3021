package com.example.asm.controller.admin;

import com.example.asm.entity.ChucVu;
import com.example.asm.entity.NSX;
import com.example.asm.services.NSXService;
import com.example.asm.services.impl.NSXServiceImpl;
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
@RequestMapping("/admin/nsx")
public class NSXController {
    @Autowired
    private NSXService nsxService = new NSXServiceImpl();
    @Autowired
    public NSX nsx;
    @Autowired
    private HttpSession session;

    @GetMapping("/index")
    public String index(Model model,@RequestParam(defaultValue = "0", name = "page") int page) {
        session.setAttribute("thongBao", "");
        Page<NSX> list = nsxService.findAllPage(page);
        model.addAttribute("list", list);
        model.addAttribute("view", "/WEB-INF/views/admin/nsx/index.jsp");
        return "admin/layout";
    }

    @GetMapping("/create")
    public String create(Model model,NSX nsx){
        model.addAttribute("nsx", nsx);
        model.addAttribute("view", "/WEB-INF/views/admin/nsx/create.jsp");
        return "admin/layout";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("nsx") NSX nsx, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            model.addAttribute("view", "/WEB-INF/views/admin/nsx/create.jsp");
            return "admin/layout";
        }
        nsx.setMa(nsxService.maNSXCount());
        nsxService.add(nsx);
        return "redirect:/admin/nsx/index";
    }
    @GetMapping("/edit/{id}")
    public String edit(Model model,@PathVariable("id") UUID id){
        model.addAttribute("nsx", nsxService.detail(id));
        model.addAttribute("view", "/WEB-INF/views/admin/nsx/update.jsp");
        return "admin/layout";
    }
    @PostMapping("/update/{id}")
    public String update(@Valid @ModelAttribute("nsx") NSX nsx, BindingResult bindingResult,Model model){
        if (bindingResult.hasErrors()) {
            model.addAttribute("view", "/WEB-INF/views/admin/nsx/update.jsp");
            return "admin/layout";
        }

        nsxService.update(nsx);
        return "redirect:/admin/nsx/index";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") UUID id){
        nsxService.deleteById(id);
        return "redirect:/admin/nsx/index";
    }
}
