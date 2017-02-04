package test.domain.service;


import test.domain.model.dto.PrefectureDto;
import test.domain.model.entity.PrefectureEntity;

import java.util.List;

public interface PrefectureDtoService {
    List<PrefectureDto> getAll();

}
