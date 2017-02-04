package test.domain.service;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import test.domain.dao.IdtableDao;
import test.domain.dao.PrefectureDao;
import test.domain.model.dto.PrefectureDto;
import test.domain.model.entity.PrefectureAreaConectionEntity;
import test.domain.model.entity.PrefectureEntity;
import test.domain.repository.PrefectureRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class PrefectureServiceImpl implements PrefectureService {
    // フィールド変数　メソッドの中じゃなくてクラスの中に書く。
    @Autowired
    private JdbcTemplate jdbc;
    @Autowired
    private PrefectureDao prefectureDao;
    @Autowired
    private IdtableDao idtableDao;

    @Autowired
    private PrefectureRepository prefectureRepository;


    private static final int PAGE_SIZE = 5;


    @Override
    public PrefectureEntity getOne(int id) {
        PrefectureEntity result = prefectureDao.getOne(id);
        return result;
    }

    @Override
    public List<PrefectureDto> search(String ken) {
        List<PrefectureEntity> kekka =
                prefectureRepository.findByNameLikeOrCapitalLikeOrFlowerLikeOrBirdLikeOrTreeLike("%" + ken + "%", "%" + ken + "%", "%" + ken + "%", "%" + ken + "%", "%" + ken + "%");

        System.out.println(kekka);

        List<PrefectureDto> result = new ArrayList<>();
        System.out.println(kekka);

        for (int i = 0; i < kekka.size(); i++) {
            PrefectureDto pref = new PrefectureDto();
            result.add(pref);
            BeanUtils.copyProperties(kekka.get(i), result.get(i));
        }

        // List<PrefectureDto> result = prefectureDao.search(ken);　　この文章をいかにしてRepositoryにするかが上の文
        return result;
    }

    @Override
    public void update(String name, String capital, String flower, String bird, String tree, int num) {
        jdbc.update("update prefecture set name = ? , capital = ?, flower = ?, tree = ?, bird = ? where id = ?", name, capital, flower, bird, tree, num);
    }

    @Override
    public void delete(int num) {
        jdbc.update("delete from prefecture where id = ?", num);
    }

    @Override
    public void insert(String name, String capital, String flower, String bird, String tree) {
        jdbc.update("insert into prefecture(name, capital, flower, bird, tree) values(?, ?, ?, ?, ?)", name, capital, flower, tree, bird);
    }


    @Override
    public List<PrefectureAreaConectionEntity> addAreaToPrefecture(int tagid) {
        List<PrefectureAreaConectionEntity> conectionList = new ArrayList<>();
        conectionList = idtableDao.getOne(tagid);

        return conectionList;
    }

    @Override
    public void deleteTag(Integer num, Integer tagid) {
        idtableDao.deleteTag(num, tagid);
    }

    @Override
    public void addTag(String addtagname, Integer num) {
        idtableDao.addTag(addtagname, num);
    }

    @Override
    public List<PrefectureEntity> allPrefecture() {
        List<PrefectureEntity> prefectureEntityList = prefectureRepository.findAll();
        return prefectureEntityList;
    }

    @Override
    public Page<PrefectureEntity> getPagePrefectureEntity(int pageNumber){
        PageRequest pageRequest = new PageRequest(pageNumber - 1, PAGE_SIZE);
        return prefectureRepository.findAll(pageRequest);
    }
}
