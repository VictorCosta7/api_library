package br.com.victorcosta.libraryapi.modules.user;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import br.com.victorcosta.libraryapi.modules.book.domain.BookEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.validation.constraints.Email;
import io.micrometer.common.lang.Nullable;

@Entity(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String fullName;

    private String username;

    @Email
    private String email;

    @Length(min = 10, max = 100, message = "Password must contain between (10) and (100) characters")
    private String password;

    @Column(name = "user_id")
    private UUID userId;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private UserEntity user;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Nullable
    private LocalDateTime deletedAt;

    public UserEntity() {
    }

    public UserEntity(UUID id, String fullName, String username, String email, String password, LocalDateTime createdAt, LocalDateTime deletedAt) {
        this.id = id;
        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
        this.deletedAt = deletedAt;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return java.util.Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Nullable
    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(@Nullable LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    @Override
    public String toString() {
        return "UserEntity{" + "id=" + id + ", fullName='" + fullName + '\'' + ", username='" + username + '\'' + ", email='" + email + '\'' + ", password='" + "[PROTECTED]" + '\'' + ", createdAt=" + createdAt + ", deletedAt=" + deletedAt + '}';
    }
}