package test.domain.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by yuya on 西暦16/09/13.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrefectureAreaConectionEntity implements Serializable {

    Integer id;
    Integer tagid;

    public PrefectureAreaConectionEntity(Integer id) {
        this.id = id;
    }


}
