package test.domain.model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "msgdata")
@Data
@AllArgsConstructor
public class MsgData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @NotNull
    private long id;

    @Column
    private String title;

    @Column
    @NotEmpty
    private String message;

    @ManyToOne
    private MyData myData;

    public MsgData(){
        super();
        myData = new MyData();
    }
}
