package test.domain.service;

import org.springframework.data.domain.Page;
import test.domain.model.dto.PrefectureDto;
import test.domain.model.entity.PrefectureAreaConectionEntity;
import test.domain.model.entity.PrefectureEntity;

import java.util.List;


public interface PrefectureService {

    PrefectureEntity getOne(int id);

    List<PrefectureDto> search(String ken);

    void update(String name, String capital, String flower, String bird, String tree, int num);

    void delete(int num);

    void insert(String name, String capital, String flower, String bird, String tree);




    List<PrefectureAreaConectionEntity> addAreaToPrefecture(int tagid);

    void deleteTag(Integer num, Integer tagid);

    void addTag(String addtagname, Integer num);

    List<PrefectureEntity> allPrefecture();

    Page<PrefectureEntity> getPagePrefectureEntity(int pageNumber);
}
