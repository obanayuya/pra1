package test.domain.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.integration.IntegrationAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import test.domain.model.entity.MyData;
import test.domain.repository.MyDataRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Service
public class MyDataService {

    private static final int PAGE_SIZE = 3;

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    MyDataRepository myDatarepository;

    public List<MyData> getAll(){
        return (List<MyData>) entityManager
                .createQuery("from MyData").getResultList();
    }


    public MyData get(int num){
        return (MyData)entityManager
                .createQuery("from MyData where id = " + num)
                .getSingleResult();
    }

    public Page<MyData> getMyDataInPage(Integer pageNumber){
        PageRequest pageRequest = new PageRequest(pageNumber - 1, PAGE_SIZE);
        return myDatarepository.findAll(pageRequest);
    }
}
