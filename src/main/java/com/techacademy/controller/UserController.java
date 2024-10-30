package com.techacademy.controller;

import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.techacademy.entity.User;
import com.techacademy.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/list")
    public String getList(Model model) {
        model.addAttribute("userlist",service.getUserList());

        return "user/list";
    }

    /**登録画面の表示**/
    @GetMapping("/register")
    public String getRegister(@ModelAttribute User user) {
        return "user/register";
    }

    /**登録画面に遷移**/
    @PostMapping("/register")
    public String postRegister(@Validated User user,BindingResult res,Model model) {
        if(res.hasErrors()) {
            return getRegister(user);
        }

        service.saveUser(user);
        return "redirect:/user/list";
    }

    /**User更新画面の表示**/
    @GetMapping("/update/{id}/")
    public String getUser(@ModelAttribute User user,@PathVariable(value = "id", required = false) Integer id, Model model) {
        if (id != null) {
            model.addAttribute("user", service.getUser(id));
        } else {
            model.addAttribute("user", user);
        }
        return "user/update";
        }


    /**User更新処理**/
    @PostMapping("/update/{id}/")
    public String postUser(@PathVariable("id") Integer id,@Validated User user,BindingResult res, Model model) {
        if(res.hasErrors()) {
            return getUser(user, null, model);
            }
        service.saveUser(user);
        return "redirect:/user/list";
    }

    @PostMapping(path="list",params="deleteRun")
    public String deleteRun(@RequestParam(name="idck") Set<Integer> idck,Model model) {
        service.deleteUser(idck);
        return "redirect:/user/list";
    }



}
