package com.example.server_java_form_mvc.service;

import com.example.server_java_form_mvc.model.Usuario;
import com.example.server_java_form_mvc.repository.Db; // Certifique-se de que o pacote e a classe estão corretos
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private Db db;

    public void criarUsuario(Usuario usuario) {
        db.adicionarUsuario(usuario); // Adiciona o usuário ao banco de dados
    }

    public List<Usuario> listarUsuarios() {
        return db.getUsuarios(); // Retorna a lista de usuários
    }
}
