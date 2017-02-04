package test.domain.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.print.attribute.IntegerSyntax;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "mydata")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @NotNull
    private long id;

    @Column(length = 50, nullable = false)
    @NotEmpty
    private String name;

    @Column(length = 200, nullable = true)
    @Email
    private String mail;

    @Column(nullable = true)
    @Min(0)
    @Max(200)
    private Integer age;

    @Column(nullable = true)
    private String memo;

    @OneToMany(cascade = CascadeType.ALL)
    @Column(nullable = true)
    private List<MsgData> msgDataList;

}
