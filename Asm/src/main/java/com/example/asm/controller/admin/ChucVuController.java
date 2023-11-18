package com.example.asm.controller.admin;

import com.example.asm.entity.ChiTietSp;
import com.example.asm.entity.ChucVu;
import com.example.asm.services.ChucVuService;
import com.example.asm.services.impl.ChucVuServiceImpl;
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
@RequestMapping("/admin/chuc-vu")
public class ChucVuController {
    @Autowired
    private ChucVuService chucVuService = new ChucVuServiceImpl();

    @Autowired
    private ChucVu chucVu;

    @Autowired
    private HttpSession session;

    @GetMapping("/index")
    public String index(Model model, @RequestParam(defaultValue = "0", name = "page") int page) {
        session.setAttribute("thongBao", "");
        Page<ChucVu> list = chucVuService.findAllPage(page);
        model.addAttribute("list", list);
        model.addAttribute("view", "/WEB-INF/views/admin/chuc-vu/index.jsp");
        return "admin/layout";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("chucVu", chucVu);
        model.addAttribute("view", "/WEB-INF/views/admin/chuc-vu/create.jsp");
        return "admin/layout";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("chucVu") ChucVu chucVu, BindingResult bindingResult,Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("view", "/WEB-INF/views/admin/chuc-vu/create.jsp");
            return "admin/layout";
        }
        chucVu.setMa(chucVuService.maCVCount());
        chucVuService.add(chucVu);
        session.setAttribute("thongBao", "Thêm thành công");
        return "redirect:/admin/chuc-vu/index";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("chucVu", chucVuService.detail(id));
        model.addAttribute("view", "/WEB-INF/views/admin/chuc-vu/update.jsp");
        return "admin/layout";
    }

    @PostMapping("/update/{id}")
    public String update( @Valid @ModelAttribute("chucVu") ChucVu chucVu,BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            model.addAttribute("view", "/WEB-INF/views/admin/chuc-vu/create.jsp");
            return "admin/layout";
        }
        chucVuService.update(chucVu);
        session.setAttribute("thongBao", "Cập nhật thành công");
        return "redirect:/admin/chuc-vu/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") UUID id) {
        chucVuService.deleteById(id);
        return "redirect:/admin/chuc-vu/index";
    }
}
