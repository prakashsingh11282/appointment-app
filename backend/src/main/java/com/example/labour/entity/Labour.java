package com.example.labour.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "labour_profiles")
public class Labour {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Column(nullable = false)
  private String phone;

  @Column(nullable = false, length = 500)
  private String skills;

  @Column(name = "created_at", nullable = false, updatable = false)
  private Long createdAt;

  @PrePersist
  protected void onCreate() {
    createdAt = System.currentTimeMillis();
  }

  public Labour() {
  }

  public Labour(User user, String phone, String skills) {
    this.user = user;
    this.phone = phone;
    this.skills = skills;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getSkills() {
    return skills;
  }

  public void setSkills(String skills) {
    this.skills = skills;
  }

  public Long getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Long createdAt) {
    this.createdAt = createdAt;
  }
}
