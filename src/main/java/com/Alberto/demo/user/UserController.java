package com.Alberto.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller

public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String showUserList(Model model){
        List<User> listUsers = userService.listAll();
        model.addAttribute("listUsers",listUsers);

        return "users";
    }

    @GetMapping("/users/new")
    public String showNewForm(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle", "Añadir Nuevo Usuario");
        return  "user_form";
    }

    @PostMapping("/users/save")
    public String saveUser(User user, RedirectAttributes ra){
        try {
            userService.salvar(user);
            ra.addFlashAttribute("message", "El Usuario se Inserto Correctamente");


            return "redirect:/users";
         }catch (EmailNotFoundException e){
        ra.addFlashAttribute("message",e.getMessage());

            return "redirect:/users";
    }
    }

    @GetMapping("/users/edit/{id}")
    public String showEditForm(@PathVariable("id")Integer id, Model model,RedirectAttributes ra){
        try{
            User user = userService.get(id);
            model.addAttribute("user",user);
            model.addAttribute("pageTitle", "Editar Usuario (ID: " + id +")");

            return "user_form";
        }catch (UserNotFoundException e){
            ra.addFlashAttribute("message",e.getMessage());
            return "redirect:/users";
        }

    }
    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id")Integer id,RedirectAttributes ra){
        try{
            userService.delete(id);
            ra.addFlashAttribute("message","El Usuario con ID" +id +" ha sido eliminado");

        }catch (UserNotFoundException e){
            ra.addFlashAttribute("message",e.getMessage());

        }
        return "redirect:/users";
    }


}
