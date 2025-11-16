## 🚀 Backend Spring Boot: server-java-form-mvc

Projeto de exemplo usando Spring Boot (MVC + JPA) para cadastro de usuários com persistência em **SQLite**. Ideal para integrar com um frontend (ex.: React/Vite) via chamadas HTTP (JSON) e testar fluxo Controller → Service → Repository JPA → Banco de Dados.

---

### ✅ 1. Pré-requisitos

-   Java 17+ (compatível com Spring Boot moderno) – verifique com `java -version`
-   Maven (usa wrapper `mvnw`, não é obrigatório ter Maven instalado globalmente)
-   Git instalado

Opcional para testes de integração:

-   Ferramenta de API (Insomnia, Postman ou `curl`)
-   Frontend rodando em `http://127.0.0.1:5500` (CORS já configurado) ou outro que você ajuste.

---

### 📥 2. Clonando o repositório

```bash
git clone https://github.com/leonardorsolar/server-java-form-mvc.git
cd server-java-form-mvc
```

---

### ▶️ 3. Executando a aplicação

Usando o Maven Wrapper (recomendado):

```bash
./mvnw spring-boot:run
```

Ou compilando e rodando o JAR:

```bash
./mvnw clean package
java -jar target/server-java-form-mvc-*.jar
```

Servidor padrão configurado para porta `3000` (ver `application.properties`).

Acesse:

-   Página do formulário (Thymeleaf): `http://localhost:3000/api/usuario`

---

### 🌐 4. Endpoints Principais

Prefixo geral: `/api`

| Método | Rota              | Descrição                                  | Corpo Esperado (JSON)                           |
| ------ | ----------------- | ------------------------------------------ | ----------------------------------------------- |
| GET    | /api/usuario      | Retorna a view `usuario.html` (formulário) | N/A                                             |
| POST   | /api/criarUsuario | Cria usuário no SQLite e devolve JSON      | `{"name":"...","email":"...","password":"..."}` |

Exemplo `curl`:

```bash
curl -X POST http://localhost:3000/api/criarUsuario \
  -H "Content-Type: application/json" \
  -d '{"name":"Ana","email":"ana@example.com","password":"123"}'
```

Resposta:

```json
{
    "id": 1,
    "name": "Ana",
    "email": "ana@example.com",
    "password": "123"
}
```

Observação: senha não é criptografada (apenas exemplo didático). O campo `id` é gerado automaticamente pelo banco.

---

### 🧩 5. Arquitetura / Camadas

-   `controller/UsuarioController`: recebe requisições HTTP, faz binding do corpo para `UsuarioDTO`.
-   `service/UserService`: regra de criação e orquestra fluxo; transforma DTO → Modelo.
-   `repository/UsuarioRepository`: interface Spring Data JPA que gerencia persistência no SQLite.
-   `model/Usuario`: entidade JPA com `@Entity`, mapeada para tabela `usuarios`.
-   `dto/UsuarioDTO`: objeto de transporte (entrada JSON).
-   `config/CorsConfig`: libera CORS para `http://127.0.0.1:5500`.
-   `templates/usuario.html`: formulário estático (ajustável para binder correto).

**Fluxo:** Request → Controller → Service → UsuarioRepository (JPA) → SQLite → retorna Modelo serializado como JSON.

**Persistência:** Arquivo `usuarios.db` criado automaticamente na raiz do projeto. Os dados **persistem entre reinicializações**.

---

### 🛠 6. Ajustes Recomendados

-   Corrigir o `form` em `usuario.html` para apontar `action="/api/criarUsuario"` e usar nomes de campos `name`, `email`, `password`.
-   Adicionar endpoint de listagem (`GET /api/usuarios`) para listar todos os usuários do banco.
-   Validar entrada (ex.: `@NotBlank`, `@Email`) usando Bean Validation.
-   Criptografar senhas com BCrypt (`spring-security-crypto`).
-   Adicionar índice único no campo `email` para evitar duplicatas.
-   Tratar erros com `@ControllerAdvice` + `@ExceptionHandler` para respostas padronizadas.
-   Implementar paginação para endpoint de listagem.

---

### 🤝 7. Integração com Frontend (Exemplo)

Se estiver usando um frontend React (como no outro repositório `iff-basic-program-react-front`):

```ts
// Exemplo simples usando fetch
await fetch("http://localhost:3000/api/criarUsuario", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ name, email, password }),
})
```

Certifique-se de que a origem (`http://127.0.0.1:5500` ou `http://localhost:5173`) esteja configurada em `CorsConfig`.

---

### 📂 8. Estrutura de Pastas (Resumo)

```txt
src/main/java/com/example/server_java_form_mvc/
├── ServerJavaFormMvcApplication.java
├── config/CorsConfig.java
├── controller/UsuarioController.java
├── service/UserService.java
├── repository/UsuarioRepository.java    ← Interface JPA
├── dto/UsuarioDTO.java
├── model/Usuario.java                   ← Entidade JPA (@Entity)
src/main/resources/
├── application.properties               ← Configuração SQLite
├── templates/usuario.html
usuarios.db                               ← Banco SQLite (criado automaticamente)
```

---

### � 9. Testes

Arquivo base de teste: `ServerJavaFormMvcApplicationTests.java`. Pode-se ampliar com testes de:

-   Controller (MockMvc)
-   Service (teste de lógica / validação)

Exemplo (futuro): verificar se criação retorna objeto correto e incrementa lista.

---

### 🔐 10. Limitações Atuais

-   Sem validação de entrada (campos vazios, formato de email).
-   Senha em texto puro (sem criptografia).
-   Sem paginação ou autenticação.
-   Sem índice único para email (permite duplicatas).

---

### 📈 11. Próximos Passos (Roadmap)

1. Adicionar endpoint GET `/api/usuarios` para listar todos.
2. Implementar Bean Validation (javax / jakarta).
3. Adicionar índice único para email (`@Column(unique=true)`).
4. Criptografar senha com BCrypt.
5. Adicionar testes automatizados (MockMvc, TestContainers).
6. Dockerfile para containerizar.
7. Migrar para PostgreSQL em produção (manter SQLite para dev).

---

### ✅ 12. Conclusão

Este backend fornece base para estudar fluxo MVC com Spring Boot + JPA e persistência SQLite. Perfeito para evoluir gradualmente adicionando validação, segurança e testes.

---

### 🗄️ 13. Sobre o SQLite

O arquivo `usuarios.db` é criado automaticamente na primeira execução. Para inspecionar o banco:

```bash
# Instalar sqlite3 (se necessário)
sudo apt install sqlite3   # Linux
brew install sqlite        # macOS

# Abrir o banco
sqlite3 usuarios.db

# Comandos úteis
.tables                    # Listar tabelas
SELECT * FROM usuarios;    # Ver todos os usuários
.quit                      # Sair
```

Para resetar o banco, delete o arquivo:

```bash
rm usuarios.db
```

---

### 📄 Licença

Uso educacional / exemplo. Ajuste conforme necessidade.
