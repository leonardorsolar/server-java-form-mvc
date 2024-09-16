package com.example.server_java_form_mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.server_java_form_mvc.model.Usuario;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @GetMapping
    public String mostrarFormulario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuario"; // Nome do arquivo de template .html em src/main/resources/templates
    }

    @PostMapping
    public String criarUsuario(@RequestParam String nome,
            @RequestParam String email,
            @RequestParam String senha,
            Model model) {
        Usuario usuario = new Usuario(nome, email, senha);
        model.addAttribute("usuario", usuario);
        return "usuario"; // Nome do arquivo de template .html em src/main/resources/templates
    }
}
