package com.example.server_java_form_mvc.service;

import com.example.server_java_form_mvc.dto.UsuarioDTO;
import com.example.server_java_form_mvc.model.Usuario;
import com.example.server_java_form_mvc.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario criarUsuario(UsuarioDTO usuarioDTO) {
        System.out.println("UserService: Criando novo usuário");
        System.out.println(usuarioDTO);

        Usuario usuario = new Usuario(usuarioDTO.getName(), usuarioDTO.getEmail(), usuarioDTO.getPassword());
        Usuario salvo = usuarioRepository.save(usuario);
        System.out.println("Usuário salvo: " + salvo);
        return salvo;
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }
}
