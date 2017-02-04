package test.app.controller;

//import com.sun.xml.internal.ws.api.ha.StickyFeature;


import com.ibm.icu.text.Transliterator;
import org.apache.lucene.analysis.ja.JapaneseTokenizer;
import org.apache.lucene.analysis.ja.tokenattributes.ReadingAttribute;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.expression.spel.ast.PropertyOrFieldReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import test.domain.dao.MyDataDaoImpl;
import test.domain.model.dto.PrefectureDto;
import test.domain.model.entity.AreaEntity;
import test.domain.model.entity.MyData;
import test.domain.model.entity.PrefectureAreaConectionEntity;
import test.domain.model.entity.PrefectureEntity;
import test.domain.repository.MsgDataRepository;
import test.domain.repository.MyDataRepository;
import test.domain.service.AreaService;
import test.domain.service.MyDataService;
import test.domain.service.PrefectureDtoService;
import test.domain.service.PrefectureService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Controller
@EnableAutoConfiguration
public class PrefectureController {
    //   　フィールド変数　メソッドの中じゃなくてクラスの中に書く。
    @Autowired
    private PrefectureDtoService prefectureDtoService;
    @Autowired
    private PrefectureService prefectureService;
    @Autowired
    private AreaService areaService;
    @Autowired
    private MyDataRepository myDataRepository;
    @PersistenceContext
    EntityManager entityManager;
    @Autowired
    private MsgDataRepository msgDataRepository;
    @Autowired
    private MyDataService myDataService;


    MyDataDaoImpl dao;

    @RequestMapping("/")
    @ResponseBody
    public String home() {
        return "Hello, Spring Boot Sample Application!";
    }

    @ResponseBody
    @RequestMapping(value = "/prefecture", method = RequestMethod.GET)
    public ModelAndView prefecture() {
        ModelAndView mav = new ModelAndView("answer");

        List<PrefectureDto> prefectureDtoList = prefectureDtoService.getAll();
        mav.addObject("prefectures", prefectureDtoList);

        return mav;
    }


    @ResponseBody
    @RequestMapping(value = "/prefecture", method = RequestMethod.POST)
    public ModelAndView prefecture(@ModelAttribute("search") String ken) {
        ModelAndView mav = new ModelAndView("answer");

        // ここのPrefectureServiceがPrevectureDtoServicenになるのでは？
        List<PrefectureDto> result = (List<PrefectureDto>) prefectureService.search(ken);

        mav.addObject("prefectures", result);
        mav.addObject("search", ken);

        return mav;
    }


    @ResponseBody
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("change");

        PrefectureEntity result = prefectureService.getOne(id);

        //  tagnameを画面に表示させるための処理、なんでわざわざDto型？最初からAreaEntity型じゃダメ？
//        PrefectureDto pref = new PrefectureDto();
        List<AreaEntity> areaEntityList = new ArrayList<>();
        List<PrefectureAreaConectionEntity> connectionList = new ArrayList<>();

        connectionList = prefectureService.addAreaToPrefecture(id);

        for (PrefectureAreaConectionEntity connection : connectionList) {
            AreaEntity areaName = areaService.getOne(connection.getTagid());
//            pref.getAreaList().add(areaName);
            areaEntityList.add(areaName);
        }

//        List<AreaEntity> areaEntityList = pref.getAreaList();

        mav.addObject("areaEntityList", areaEntityList);
        mav.addObject("prefecture", result);


        //チェックボックスを表示するためのコード
        List<PrefectureDto> prefectureDtoList = prefectureDtoService.getAll();
        List<AreaEntity> areaEntity = areaService.getAllAreaEntity();
        PrefectureDto prefectureDto = new PrefectureDto(200, "東京", "東京と", "木", "花", "鳥", areaEntity);

        mav.addObject("prefectureDto", prefectureDto);

        return mav;
    }

    @ResponseBody
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public ModelAndView edit(@ModelAttribute("prefecture") PrefectureEntity before, @PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("change");

        PrefectureEntity edit = before;
        prefectureService.update(edit.getName(), edit.getCapital(), edit.getFlower(), edit.getBird(), edit.getTree(), id);
        mav.addObject("prefecture", edit);
        // mav.addObject("num", num);

        return mav;
    }


    //th:objectで値を受け渡せるかの練習

    @ResponseBody
    @RequestMapping(value = "/test/{id}", method = RequestMethod.POST)
    public ModelAndView testt(@ModelAttribute("prefecture") PrefectureEntity before, @ModelAttribute("prefectureDto") PrefectureDto prefectureDto) {
        ModelAndView mav = new ModelAndView("change");

        System.out.println(prefectureDto);

//        PrefectureEntity edit = before;
//        List<PrefectureDto> prefectureDtoList = prefectureDtoService.getAll();
        mav.addObject("prefectureDto", prefectureDto);

        return mav;
    }


    @ResponseBody
    @RequestMapping(value = "/tag/delete/{id}", method = RequestMethod.POST)
    public ModelAndView editt(@ModelAttribute("prefname, capitalname, treename, flowrename, birdname, tagname") PrefectureEntity before, @ModelAttribute("tagid") Integer tagid, @PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("change");

        PrefectureEntity edit = before;

        Integer num = id;
        mav.addObject("prefecture", edit);

        prefectureService.deleteTag(num, tagid);

        // tagを画面に表示させる文
        PrefectureDto pref = new PrefectureDto();
        List<PrefectureAreaConectionEntity> connectionList = new ArrayList<>();
        connectionList = prefectureService.addAreaToPrefecture(id);

        for (PrefectureAreaConectionEntity conection : connectionList) {
            AreaEntity areaName = areaService.getOne(conection.getTagid());
            pref.getAreaList().add(areaName);
        }
        List<AreaEntity> areaEntityList = pref.getAreaList();
        mav.addObject("areaEntityList", areaEntityList);


        return mav;
    }

    @ResponseBody
    @RequestMapping(value = "/tag/insert/{id}", method = RequestMethod.POST)
    public ModelAndView edittt(@ModelAttribute("prefname, capitalname, treename, flowrename, birdname, tagname") PrefectureEntity before, @PathVariable Integer id, @ModelAttribute("addtagname") String addtagname) {
        ModelAndView mav = new ModelAndView("change");

        PrefectureEntity edit = before;

        mav.addObject("prefecture", edit);

        prefectureService.addTag(addtagname, id);

        // tagを画面に表示させる文
        PrefectureDto pref = new PrefectureDto();
        List<PrefectureAreaConectionEntity> conectionList = new ArrayList<>();
        conectionList = prefectureService.addAreaToPrefecture(id);

        for (PrefectureAreaConectionEntity conection : conectionList) {
            AreaEntity areaname = areaService.getOne(conection.getTagid());
            pref.getAreaList().add(areaname);
        }
        List<AreaEntity> areaEntityList = pref.getAreaList();
        mav.addObject("areaEntityList", areaEntityList);

        return mav;
    }

    @ResponseBody
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView prefecturee(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("delete");

        PrefectureEntity result = prefectureService.getOne(id);

        mav.addObject("prefecture", result);
        return mav;
    }

    @ResponseBody
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public ModelAndView prefecture(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("delete");

        Integer num = id;
        prefectureService.delete(num);

        PrefectureEntity empty = new PrefectureEntity();
        mav.addObject("prefecture", empty);
        return mav;
    }


    @ResponseBody
    @RequestMapping(value = "/regi", method = RequestMethod.GET)
    public ModelAndView prefectur() {
        ModelAndView mav = new ModelAndView("regi");

        PrefectureEntity empty = new PrefectureEntity();
        mav.addObject("prefecture", empty);

        return mav;
    }

    @ResponseBody
    @RequestMapping(value = "/regi", method = RequestMethod.POST)
    public ModelAndView prefectureee(@ModelAttribute("newname, newcapital, newflower, newtree, newbird, newarea") PrefectureEntity newpref) {
        ModelAndView mav = new ModelAndView("regi");

        PrefectureEntity pref = newpref;
        prefectureService.insert(pref.getName(), pref.getCapital(), pref.getFlower(), pref.getBird(), pref.getTree());

        mav.addObject("prefecture", pref);

        StringBuilder sb = new StringBuilder(256);
        try (JapaneseTokenizer tokenizer = new JapaneseTokenizer(null, false, JapaneseTokenizer.Mode.NORMAL)) {
            tokenizer.setReader(new StringReader("明日のご飯はなに"));
            ReadingAttribute readingAttribute = tokenizer.addAttribute(ReadingAttribute.class);
            CharTermAttribute charTermAttribute = tokenizer.addAttribute(CharTermAttribute.class);
            tokenizer.reset();
            while (tokenizer.incrementToken()) {
                String kana = readingAttribute.getReading();
                if (kana == null) {
                    kana = charTermAttribute.toString();
                }
                sb.append(kana);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(sb);

        Transliterator transliterator = Transliterator.getInstance("Katakana-Hiragana");
        String result = transliterator.transliterate(String.valueOf(sb));
        System.out.println(result);

        return mav;
    }

    // 値の受け渡しの練習のコード
    @ResponseBody
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ModelAndView prefectureee(@ModelAttribute("word") String word) {
        ModelAndView mav = new ModelAndView("test");

        String pref = word;

        // mav.addObject("prefecture", pref);
        return mav;
    }

    @ResponseBody
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public ModelAndView prefectureeee(@ModelAttribute("word") String word, @ModelAttribute("king") String king) {
        ModelAndView mav = new ModelAndView("test");
        String pref = word;
        mav.addObject("prefecture", pref);
        System.out.println(pref);

        System.out.println(king);
        return mav;
    }

    //pagenationのためのコード練習
    @RequestMapping(value = "/page/{num}", method = RequestMethod.GET)
    public ModelAndView page(@PathVariable Integer num, ModelAndView mav){
        Page<PrefectureEntity> page = prefectureService.getPagePrefectureEntity(num);
        List<PrefectureEntity> prefectureEntityList = prefectureService.allPrefecture();
        int prefectureEntityListNum = prefectureEntityList.size();
        int PAGE_SIZE = 5;
        int pageNum = prefectureEntityListNum / PAGE_SIZE + 1;
        mav.setViewName("index");
        mav.addObject("pageNumber", num);
        mav.addObject("prefectureEntityList", page);
        mav.addObject("pageNum", pageNum);

        return mav;
    }
}


//pageクラスでデータベースの中身を全部持ってくる。
//Listで全部持ってくる。数を取る。
//page_sizeを確定させる。
//数をpage_sizeで割る pageNumに入れる。
//page

//htmlはaタグ
//prevとnextをつける。