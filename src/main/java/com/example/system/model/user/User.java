package com.example.system.model.user;

import com.example.system.model.blog.Blog;
import com.example.system.model.building.BuildingDetail;
import com.example.system.model.payment.Invoice;
import com.example.system.model.requestcontract.RequestContract;
import com.example.system.model.token.Token;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(nullable = false, columnDefinition = "varchar(50)")
    @NotBlank(message = "Name cannot be blank")
    private String name;
    @Column(nullable = false, columnDefinition = "varchar(50)")
    @NotBlank(message = "Email cannot be blank")
    @Pattern(regexp = "^[\\w-]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "The email must consist @")
    private String email;
    @Column(nullable = false, columnDefinition = "varchar(100)")
    @NotBlank(message = "Password cannot be blank")
    private String password;
    @Column(nullable = false, columnDefinition = "varchar(20)")
    @NotBlank(message = "Phone cannot be blank")
    @Pattern(regexp = "0[0-9]{9}", message = "The phone number must consist of 10 numbers and start with 0")
    private String phone;
    @Column(nullable = false, columnDefinition = "varchar(50)")
    @NotBlank(message = "Address cannot be blank")
    private String address;
    @Column(nullable = false)
    @NotNull(message = "Invalid status format (True - False)")
    private boolean status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private LocalDate birthday;
    @Column(nullable = false)
    @NotNull(message = "Invalid status format (True - False)") // 1:Nam - 0:Nu
    private boolean gender;
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private Set<Invoice> invoices;
    @OneToMany(mappedBy = "user")
    private Set<Token> tokens;
    @OneToMany(mappedBy = "user")
    private Set<RequestContract> requestContracts;
    @OneToMany(mappedBy = "user")
    private Set<Blog> blogs;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public String getUsername() {
        return email;
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
}
