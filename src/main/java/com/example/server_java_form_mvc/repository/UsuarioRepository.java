package com.example.server_java_form_mvc.repository;

import com.example.server_java_form_mvc.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Métodos de consulta adicionais podem ser adicionados aqui.
}
