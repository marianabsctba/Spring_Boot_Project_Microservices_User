package infnet.edu.microservice_users.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password; // Adicionar o campo de senha

    // Construtor padrão
    public User(String janeDoe, String mail) {}

    // Construtor com parâmetros
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password; // Inicializa a senha
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password; // Adicionar getter para senha
    }

    public void setPassword(String password) {
        this.password = password; // Adicionar setter para senha
    }
}
