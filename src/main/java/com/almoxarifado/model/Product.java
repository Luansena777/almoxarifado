package com.almoxarifado.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

<<<<<<< HEAD
import java.time.LocalDateTime;

@Builder
@Entity
=======
@Entity
@Table(name = "products")
>>>>>>> origin
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

<<<<<<< HEAD
    @Column(unique = true, nullable = false)
    private String code;

    @Column(nullable = false)
=======
    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false, length = 100)
>>>>>>> origin
    private String name;

    @Column(nullable = false)
    private Integer quantity;
<<<<<<< HEAD

    private String category;

    private Integer minimumQuantity;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

=======
>>>>>>> origin

}


