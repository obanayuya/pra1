package test.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import test.domain.model.entity.AreaEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
public class PrefectureDto implements Serializable {
    Integer id;
    String name;
    String capital;
    String tree;
    String flower;
    String bird;

    List<AreaEntity> areaList;

    public PrefectureDto(Integer id, String name, String capital, String tree, String flower, String bird) {
        this.id = id;
        this.name = name;
        this.capital = capital;
        this.tree = tree;
        this.flower = flower;
        this.flower = bird;
        this.areaList = new ArrayList<>();
    }

    public PrefectureDto(){
        this.areaList = new ArrayList<>();
    }
}
