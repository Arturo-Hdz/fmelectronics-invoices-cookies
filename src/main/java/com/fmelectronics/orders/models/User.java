package com.fmelectronics.orders.models;

import com.fmelectronics.orders.models.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users",
       uniqueConstraints = {
           @UniqueConstraint(columnNames = "username"),
           @UniqueConstraint(columnNames = "email")
       })
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotEmpty(message = "Username Required")
  @Size(max = 20)
  private String username;

  @NotEmpty(message = "Email Required")
  @Size(max = 50)
  @Email
  private String email;

  @NotEmpty(message = "Password Required")
  @Size(max = 120)
  private String password;

  @Column(name = "gender", nullable = false, length = 20)
//  @NotEmpty(message = "Gender Required")
  @Enumerated(EnumType.STRING)
  private Gender gender;

  private String address;

//  @Column(name = "phone_number", nullable = false, length = 20)
  @NotEmpty(message = "Phone Required")
  @Size(max = 20)
  private String phone_number;

  public boolean status=true;

  @CreationTimestamp
  @Column(name="createDate", nullable = false,
          columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
  public LocalDateTime createDate;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "user_roles", 
             joinColumns = @JoinColumn(name = "user_id"),
             inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();

  public User(String username, String email, String password, Gender gender, String address, String phone_number) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.gender = gender;
    this.address = address;
    this.phone_number = phone_number;
  }
}
