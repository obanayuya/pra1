package test.domain.dao;


import test.domain.model.entity.MyData;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Queue;

public class MyDataDaoImpl implements MyDataDao<MyData>{

    private EntityManager entityManager;


    public MyDataDaoImpl(){
        super();
    }

    public MyDataDaoImpl(EntityManager manager){
        entityManager = manager;
    }

    @Override
    public List<MyData> getAll() {
        Query query = entityManager.createQuery("from MyData");
        List<MyData> list = query.getResultList();
        entityManager.close();
        return list;
    }
}
