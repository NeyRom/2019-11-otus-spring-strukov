package ru.strukov.springjpa.model;
/* Created by Roman Strukov in 29.03.2020 */

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@Data
@Table(name = "genres")
public class Genre {
    @Getter @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "title")
    private String title;

}
