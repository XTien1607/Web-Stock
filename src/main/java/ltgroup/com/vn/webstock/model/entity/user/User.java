package ltgroup.com.vn.webstock.model.entity.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;
  Long userId;
  String role;
  String userName;
  String password;
  String firstName;
  String lastName;
  String image;
  String createdBy;
  Timestamp createdAt;
  String updatedBy;
  Timestamp updatedAt;
  boolean expired;
}
