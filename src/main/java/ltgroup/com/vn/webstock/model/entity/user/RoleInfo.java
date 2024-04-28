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
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RoleInfo {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  String id;
  String description;
  String createdBy;
  Timestamp createdAt;
  String updatedBy;
  Timestamp updatedAt;
  boolean expired;
}
