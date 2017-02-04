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
@Table(name = "tagtable")
public class AreaEntity implements Serializable {
    @Id     //これが主キーというマー
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer tagid;
    String  tagname;

}



