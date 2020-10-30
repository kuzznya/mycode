package ru.teamnull.mycode.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.teamnull.mycode.model.Role;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USER_ENTITY")
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
    private Date birthDate;
    @Enumerated(EnumType.STRING)
    private Role role;
    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "id", nullable = false)
    private Group group;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Submission> submissions;
}
