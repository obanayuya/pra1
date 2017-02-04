import org.springframework.beans.factory.annotation.Autowired;
import test.domain.model.entity.MyData;
import test.domain.repository.MyDataRepository;

public class MyDataBean {
    @Autowired
    MyDataRepository myDataRepository;

    public String getTableTagById(Long id){
        MyData data = myDataRepository.findOne(id);

        String result = "<tr><td>" + data.getName() + "</td><td>" + data.getMail() + "</td><td>" + data.getAge()
                        + "</td><td>" + data.getMemo() + "</td></tr>";

        return result;
    }
}
