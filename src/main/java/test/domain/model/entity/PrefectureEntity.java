package test.domain.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "prefecture")
public class PrefectureEntity implements Serializable {
    @Id     //これが主キーというマー
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    String name;
    String capital;
    String tree;
    String flower;
    String bird;
}
