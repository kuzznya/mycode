package ru.teamnull.mycode.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.teamnull.mycode.model.Role;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USER_ENTITY")
@JsonIdentityReference(alwaysAsId = true)
public class User {
    @Id
    @GeneratedValue
    private UUID id;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String middlename;
    private String email;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date birthDate;
    @Enumerated(EnumType.STRING)
    private Role role;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private Group group;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SELECT)
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private List<Submission> submissions;

    public User(String username, String password, String name, String surname, String middlename, String email, Date birthDate, Role role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.middlename = middlename;
        this.email = email;
        this.birthDate = birthDate;
        this.role = role;
    }

    @JsonIgnore
    public UserDetails getDetails() {
        return new UserDetails() {
            @Override
            public String getUsername() {
                return username;
            }

            @Override
            public String getPassword() {
                return password;
            }

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return List.of(new SimpleGrantedAuthority(role.name()));
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
        };
    }
}
