package biz.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCT")
@Data
@NoArgsConstructor
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "PRODUCTNAME", nullable = false)
    private String productName;

    @Column(name = "PRODUCTRATE", nullable = false)
    private double productRate;

}
