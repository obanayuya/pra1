package test.domain.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import test.domain.model.dto.PrefectureDto;
import test.domain.model.entity.AreaEntity;
import test.domain.model.entity.PrefectureAreaConectionEntity;
import test.domain.model.entity.PrefectureEntity;

import java.util.ArrayList;
import java.util.List;

@Component
public class PrefectureDaoImpl implements PrefectureDao {
    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    private IdtableDao idtableDao;

    @Autowired
    private TagtableDao tagtableDao;

    @Override
    public List<PrefectureEntity> getAll() {
        RowMapper<PrefectureEntity> mapper = new BeanPropertyRowMapper<PrefectureEntity>(PrefectureEntity.class);
        List<PrefectureEntity> result = jdbc.query("SELECT * FROM prefecture", mapper);

        return result;
    }

//PrefectureServiceのgetOneに対応
    @Override
    public PrefectureEntity getOne(int id) {
        RowMapper<PrefectureEntity> mapper = new BeanPropertyRowMapper<PrefectureEntity>(PrefectureEntity.class);
        PrefectureEntity result = jdbc.queryForObject("SELECT * FROM prefecture where id = ?", mapper, id);
        return result;
    }


//    エラーになる原因はPrefectureDto型を返すべきところにPrefectureEntity型を返しているから。ここにsearch入らない？　searchを書くのはPrefectureDtoServiceの方？
    @Override
    public List<PrefectureDto> search(String ken) {
        RowMapper<PrefectureEntity> mapper = new BeanPropertyRowMapper<PrefectureEntity>(PrefectureEntity.class);

        String word = "%" + ken + "%";
        List<PrefectureEntity> result = jdbc.query("SELECT * FROM prefecture WHERE concat_ws(' ', name, capital, flower, tree, bird) LIKE ?", mapper, word);


        List<PrefectureAreaConectionEntity> conectionList = new ArrayList<PrefectureAreaConectionEntity>();

        List<PrefectureDto> prefectureDtoList = new ArrayList<PrefectureDto>();


        for (PrefectureEntity prefecture : result) {
            Integer id = prefecture.getId();
            PrefectureDto pref = new PrefectureDto();


            // id一つについているtagidをconectionListに入れる

            conectionList = (List<PrefectureAreaConectionEntity>) idtableDao.getOne(id);

            for (PrefectureAreaConectionEntity conection : conectionList) {

                AreaEntity areaname = tagtableDao.getOne(conection.getTagid());


                pref.getAreaList().add(areaname);

            }

            pref.setId(prefecture.getId());
            pref.setName(prefecture.getName());
            pref.setCapital(prefecture.getCapital());
            pref.setFlower(prefecture.getFlower());
            pref.setBird(prefecture.getBird());
            pref.setTree(prefecture.getTree());

            prefectureDtoList.add(pref);
        }

        return prefectureDtoList;
    }

    @Override
    public void update(String name, String capital, String flower, String bird, String tree, int num) {

        jdbc.update("update prefecture set name = ? , capital = ?, flower = ?, tree = ?, bird = ? where id = ?", name, capital, flower, bird, tree, num);

        return;
    }

    @Override
    public void delete(int num) {
        jdbc.update("delete from prefecture where id = ?", num);

    }

    @Override
    public void insert(String name, String capital, String flower, String bird, String tree) {
        jdbc.update("insert into prefecture(name, capital, flower, bird, tree) values(?, ?, ?, ?, ?)", name, capital, flower, tree, bird);

    }


}

