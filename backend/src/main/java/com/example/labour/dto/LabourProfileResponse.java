package com.example.labour.dto;

public class LabourProfileResponse {
  private Long id;
  private String name;
  private String email;
  private String phone;
  private String skills;
  private Long createdAt;

  public LabourProfileResponse(Long id, String name, String email, String phone, String skills, Long createdAt) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.phone = phone;
    this.skills = skills;
    this.createdAt = createdAt;
  }

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
