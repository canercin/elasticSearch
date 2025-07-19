package com.learning.elastic.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;
import java.util.UUID;

@Entity(name = "personel")
@Document(indexName = "personel")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, updatable = false)
    @Field(type =  FieldType.Keyword)
    private UUID id;

    @Column(nullable = false)
    @Field(type = FieldType.Text)
    private String firstName;

    @Column(nullable = false)
    @Field(type = FieldType.Text)
    private String lastName;

    @Column(unique = true, nullable = false)
    @Field(type = FieldType.Keyword)
    private String email;

    @Column(nullable = false)
    @Field(type = FieldType.Double)
    private BigDecimal salary;

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
