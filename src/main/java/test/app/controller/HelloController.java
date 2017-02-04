package test.app.controller;

//import com.sun.xml.internal.ws.api.ha.StickyFeature;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import test.domain.model.dto.PrefectureDto;
import test.domain.model.entity.PrefectureEntity;
import test.Rectangle;
import test.domain.dao.IdtableDao;
import test.domain.dao.PrefectureDao;
import test.domain.dao.TagtableDao;
import test.domain.service.PrefectureDtoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

@Controller
@EnableAutoConfiguration
@RequestMapping(value = "/hello")
public class HelloController {
    @Autowired
    //   　フィールド変数　メソッドの中じゃなくてクラスの中に書く。
    private PrefectureDao prefectureDao;

    @Autowired
    private IdtableDao idtableDao;

    @Autowired
    private TagtableDao tagtableDao;

    @Autowired
    private PrefectureDtoService prefectureDtoService;


    @RequestMapping("/")
    @ResponseBody
    public String home() {
        return "Hello, Spring Boot Sample Application!";
    }


    List<String> prefectures = Arrays.asList("北海道", "青森県", "岩手県", "宮城県", "秋田県", "山形県", "福島県", "茨城県", "栃木県", "群馬県", "埼玉県", "千葉県", "東京都", "神奈川県", "新潟県", "富山県", "石川県", "福井県", "山梨県", "長野県", "岐阜県", "静岡県", "愛知県", "三重県", "滋賀県", "京都府", "大阪府", "兵庫県", "奈良県", "和歌山県", "鳥取県", "島根県", "岡山県", "広島県", "山口県", "徳島県", "香川県", "愛媛県", "高知県", "福岡県", "佐賀県", "長崎県", "熊本県", "大分県", "宮崎県", "鹿児島県", "沖縄県");


    List<String> prefs = Arrays.asList("札幌", "青森", "盛岡", "仙台", "秋田", "山形", "福島", "水戸", "宇都宮", "前橋", "さいたま", "千葉", "東京", "横浜", "新潟", "富山", "金沢", "福井", "甲府", "長野", "岐阜", "静岡", "名古屋", "津", "大津", "京都", "大阪", "神戸", "奈良", "和歌山", "鳥取", "松江", "岡山", "広島", "山口", "徳島", "高松", "松山", "高知", "福岡", "佐賀", "長崎", "熊本", "大分", "宮崎", "鹿児島", "那覇");


    List<String> bird = Arrays.asList("タンチョウ", "ハクチョウ", "キジ", "ガン", "ヤマドリ", "オシドリ", "キビタキ", "ヒバリ", "オオルリ", "ヤマドリ", "ホオジロ", "シラコバト", "ユリカモメ", "カモメ", "トキ", "ライチョウ", "イヌワシ", "ツグミ", "ウグイス", "ライチョウ", "ライチョウ", "サンコウチョウ", "コノハズク", "シロチドリ", "カイツブリ", "オオミズナギドリ", "モズ", "コウノトリ", "コマドリ", "メジロ", "オシドリ", "オオハクチョウ", "キジ", "アビ", "ナベヅル", "シラサギ", "ホトトギス", "コマドリ", "ヤイロチョウ", "ウグイス", "カササギ", "オシドリ", "ヒバリ", "メジロ", "コシジロヤマドリ", "ルリカケス", "ノグチゲラ");


    List<String> flower = Arrays.asList("ハマナス", "リンゴ", "キリ", "ミヤギノハギ", "フキノトウ", "ベニバナ", "ネモトシャクナゲ", "バラ", "ヤシオツツジ", "レンゲツツジ", "サクラソウ", "ナノハナ", "ソメイヨシノ", "ヤマユリ", "チューリップ", "チューリップ", "クロユリ", "スイセン", "フジザクラ", "リンドウ", "レンゲソウ", "ツツジ", "カキツバタ", "ハナショウブ", "シャクナゲ", "シダレザクラ", "サクラソウ", "ウメ", "ノジギク", "ナラノヤエザクラ", "ウメ", "二十世紀ナシ", "ボタン", "モモ", "モミジ", "ナツミカン", "スダチ", "オリーブ", "ミカン", "ヤマモモ", "ウメ", "クス", "ウンゼンツツジ", "リンドウ", "ブンゴウメ", "ハマユウ", "ミヤマキリシマ", "デイゴ");


    List<String> kana = Arrays.asList("ほっかいどう", "あおもりけん", "いわてけん", "みやぎけん", "あきたけん", "やまがたけん", "ふくしまけん", "いばらきけん", "とちぎけん", "ぐんまけん", "さいたまけん", "ちばけん", "とうきょうと", "かながわけん", "にいがたけん", "とやまけん", "いしかわけん", "ふくいけん", "やまなしけん", "ながのけん", "ぎふけん", "しずおかけん", "あいちけん", "みえけん", "しがけん", "きょうとふ", "おおさかふ", "ひょうごけん", "ならけん", "わかやまけん", "とっとりけん", "しまねけん", "おかやまけん", "ひろしまけん", "やまぐちけん", "とくしまけん", "かがわけん", "えひめけん", "こうちけん", "ふくおかけん", "さがけん", "ながさきけん", "くまもとけん", "おおいたけん", "みやざきけん", "かごしまけん", "おきなわけん");


    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ModelAndView hi() {
        ModelAndView mav = new ModelAndView("answer");


        List<PrefectureEntity> list = new ArrayList<>();


        //        for (int i = 0; i < prefectures.size(); i++) {
        //            list.add(new PrefectureEntity(prefectures.get(i), prefs.get(i), bird.get(i), flower.get(i), kana.get(i)));
        //        }


        //        for (PrefectureEntity prefecture : list) {
        //
        //            System.out.println("県名:" + prefecture.name);
        //            System.out.println("県庁所在地:" + prefecture.capital);
        //        }

        mav.addObject("prefectures", list);

        return mav;
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public ModelAndView answerrr(@ModelAttribute("search") String ken) {
        ModelAndView mav = new ModelAndView("answer");


        List<String> result = new ArrayList<>();


        for (String prefecture : prefectures) {
            if (!prefecture.contains(ken)) {
                continue;
            }
            result.add(prefecture);
        }

        mav.addObject("prefecture", result);


        return mav;
    }


    @Autowired
    JdbcTemplate jdbc;

    @ResponseBody
    @RequestMapping(value = "/prefecture", method = RequestMethod.GET)
    public ModelAndView prefecture() {
        ModelAndView mav = new ModelAndView("answer");

        List<PrefectureDto> prefectureDtoList = prefectureDtoService.getAll();
        mav.addObject("prefectures", prefectureDtoList);


        return mav;


                        //        Long allUsersCount = jdbc.queryForObject("SELECT COUNT(*) FROM prefecture", Long.class);
                        //        System.out.println("allUsersCount = " + allUsersCount);

                        //        name = jdbc.queryForObject("SELECT name, capital, flower, bird, tree FROM prefecture WHERE name = ?", new Object[] {
                        //                "東京都"
                        //        }, String.class);

                        //        RowMapper<PrefectureEntity> mapper = new BeanPropertyRowMapper<PrefectureEntity>(PrefectureEntity.class);
                        //        List<PrefectureEntity> pref = jdbc.query("SELECT name,capital, flower, bird, tree FROM prefecture WHERE name = '大阪府'",mapper);


                        //       　継承すればこれが消せる。 mapper　とかは外に書く。
                        //        RowMapper<PrefectureEntity> mapper = new BeanPropertyRowMapper<PrefectureEntity>(PrefectureEntity.class);
                        //        List<PrefectureEntity> result = jdbc.query("SELECT * FROM prefecture", mapper);
//        List<PrefectureEntity> result = prefectureDao.getAll();
//        System.out.println(result.size());

                        //　IdtableDaoを書いたからこれが消せる。
                        //        RowMapper<PrefectureAreaConectionEntity> mapper2 = new BeanPropertyRowMapper<PrefectureAreaConectionEntity>(PrefectureAreaConectionEntity.class);
                        //        List<PrefectureAreaConectionEntity> conections = jdbc.query("SELECT * FROM idtable", mapper2);
//        List<PrefectureAreaConectionEntity> conections = idtableDao.getAll();


//        RowMapper<AreaEntity> mapper3 = new BeanPropertyRowMapper<AreaEntity>(AreaEntity.class);

//        List<PrefectureAreaConectionEntity> conectionList = new ArrayList<PrefectureAreaConectionEntity>();
        //⑤
//        List<PrefectureDto> prefectureDtoList = new ArrayList<PrefectureDto>();
                          //        AreaEntity areaname = new AreaEntity();


        //①
//        for (PrefectureEntity prefecture : result) {
             // ②  idにはいるのは絶対に１つだけ。
//            Integer id = prefecture.id;
//            PrefectureDto pref = new PrefectureDto();


            //③ 　 conecitonListの中身はtagidが複数個入るときもある。
//            conectionList = jdbc.query("select tagid from idtable where id = ?", mapper2, id);
//            conectionList = (List<PrefectureAreaConectionEntity>) idtableDao.getOne(id);
//
//            for (PrefectureAreaConectionEntity conection : conectionList) {

                //④ areanamには＃東北と必ず一つ
//                AreaEntity areaname = jdbc.queryForObject("select tagname from tagtable where tagid = ?", mapper3, conection.tagid);
//                AreaEntity areaname = tagtableDao.getOne(conection.tagid);


//                pref.areaList.add(areaname.tagname);

                //⑤ ⑥
//            }
//            pref.id = prefecture.id;
//            pref.name = prefecture.name;
//            pref.capital = prefecture.capital;
//            pref.flower = prefecture.flower;
//            pref.bird = prefecture.bird;
//            pref.tree = prefecture.tree;

            //⑧
//            prefectureDtoList.add(pref);
//        }


                                //        List<PrefectureEntity> id = jdbc.query("SELECT id FROM prefecture", mapper);
                                //        List<PrefectureAreaConectionEntity> tagid = jdbc.query("SELECT tagid FROM idtable where id = ?", mapper2, id);
                                //        List<AreaEntity> area = jdbc.query("SELECT tagname FROM tagtable where tagid = ?", mapper3, tagid);

                                //        mav.addObject("area", area);

    }


    @ResponseBody
    @RequestMapping(value = "/prefecture", method = RequestMethod.POST)
    public ModelAndView prefecture(@ModelAttribute("search") String ken) {
        ModelAndView mav = new ModelAndView("answer");

//        RowMapper<PrefectureEntity> mapper = new BeanPropertyRowMapper<PrefectureEntity>(PrefectureEntity.class);
//        String word = "%" + ken + "%";
//        List<PrefectureEntity> result = jdbc.query("SELECT * FROM prefecture WHERE concat_ws(' ', name, capital, flower, tree, bird, area) LIKE ?", mapper, word);

        List<PrefectureDto> result = (List<PrefectureDto>) prefectureDao.search(ken);


        mav.addObject("prefectures", result);
        mav.addObject("search", ken);


        //        jdbc.update("delete from prefecture where id = 96");


        return mav;
    }


    @ResponseBody   //viewを呼び出さないからModelAndViewを書かない   @ResponseBodyを書く。
    @RequestMapping(value = "/area")   //デフォルトではGETメソッドになる
    public String area() {
        Rectangle test = new Rectangle(10, 100);

        String result = "";
        result = result + "<br>縦：" + test.getHeight();
        result = result + "<br>横：" + test.getWidth();
        result = result + "<br>面積：" + test.getArea();   //"<br>"がないと文字列でなく数字の足し算になる
        result = result + "<br>周囲：" + test.getPerimeter();
        test.setHeight(20);
        test.setWidth(200);  //ここだけ変えても値が変わるだけでarea は変わらない。
        //        test.height = 20;
        result = result + "<br>縦：" + test.getHeight();
        result = result + "<br>横：" + test.getWidth();
        result = result + "<br>面積：" + test.getArea();   //"<br>"がないと文字列でなく数字の足し算になる
        result = result + "<br>周囲：" + test.getPerimeter();  //getで呼ばれたときに初めて計算して値をだす。

        return result;
    }


                //    @Autowired
                //    JdbcTemplate jdbc;
                //
                //    @ResponseBody
                //    @RequestMapping(value = "/user")
                //    public  String user() {
                //
                //        Long allUsersCount = jdbc.queryForObject("SELECT COUNT(*) FROM users", Long.class);
                //        System.out.println("allUsersCount = " + allUsersCount);
                //
                //
                //
                //        String password = jdbc.queryForObject("SELECT password FROM users WHERE username = ?", new Object[] {
                //                "miyamoto"
                //        }, String.class);
                //        System.out.println("password = " + password);
                //
                ////        User user = jdbc.queryForObject("SELECT * FROM users WHERE username = ?", new Object[] {
                ////                "miyamoto"
                ////        }, new RowMapper<User>() {
                ////
                ////            @Override
                ////            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                ////                User user = new User();
                ////                user.setUsername(rs.getString("username"));
                ////                user.setPassword(rs.getString("password"));
                ////                return user;
                ////            }
                ////        });
                ////        System.out.println("user = " + user);
                //
                //        return "OK!!!";
                //    }
                //

    @ResponseBody
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("change");

//        RowMapper<PrefectureEntity> mapper = new BeanPropertyRowMapper<PrefectureEntity>(PrefectureEntity.class);
//        PrefectureEntity result = jdbc.queryForObject("SELECT * FROM prefecture where id = ?", mapper, id);

        PrefectureEntity result = prefectureDao.getOne(id);

        mav.addObject("prefecture", result);
        return mav;
    }


    //ここから先はまだ直してない。
    @ResponseBody
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public ModelAndView edit(@ModelAttribute("prefname, capitalname, treename, flowrename, birdname, ") PrefectureEntity before, @PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("change");


        PrefectureEntity edit = before;
        Integer num = id;
//        jdbc.update("update prefecture set name = ? , capital = ?, flower = ?, tree = ?, bird = ?  where id = ?", edit.name, edit.capital, edit.flower, edit.bird, edit.tree, num);
        prefectureDao.update(edit.getName(), edit.getCapital(), edit.getFlower(), edit.getBird(), edit.getTree(), num);
        //        jdbc.update("update prefecture set capital = ? where id = ?", edit.capital, num);
        //        jdbc.update("update prefecture set flower = ? where id = ?", edit.flower, num);
        //        jdbc.update("update prefecture set tree = ? where id = ?", edit.tree, num);
        //        jdbc.update("update prefecture set bird = ? where id = ?", edit.bird, num);

//        jdbcTemplate.update("DELETE * FROM pet WHERE pet_id=?", pet.getPetId());
//
//
//        String word = "%" + ken + "%";
//        List<PrefectureEntity> result = jdbc.query("SELECT * FROM prefecture WHERE concat_ws(' ', name, capital, flower, tree, bird) LIKE ?", mapper, word);
//
        mav.addObject("prefecture", edit);

        return mav;
    }


    @ResponseBody
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView prefecturee(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("delete");


//        RowMapper<PrefectureEntity> mapper = new BeanPropertyRowMapper<PrefectureEntity>(PrefectureEntity.class);
//        PrefectureEntity result = jdbc.queryForObject("SELECT * FROM prefecture where id = ?", mapper, id);

        PrefectureEntity result = prefectureDao.getOne(id);

        mav.addObject("prefecture", result);
        return mav;

    }


    @ResponseBody
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public ModelAndView prefecture(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("delete");

        Integer num = id;
//        jdbc.update("delete from prefecture where id = ?", num);
        prefectureDao.delete(num);

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
//        jdbc.update("insert into prefecture(name, capital, flower, tree, bird, area) values(?, ?, ?, ?, ?, ?)", pref.name, pref.capital, pref.flower, pref.tree, pref.bird);
//        prefectureDao.insert(pref.name, pref.capital, pref.flower, pref.bird, pref.tree);
        prefectureDao.insert(pref.getName(), pref.getCapital(), pref.getFlower(), pref.getBird(), pref.getTree());

        //        INSERT INTO 商品表 (商品コード , 商品名 , 単価)
        //        VALUES ('1001','鉛筆',200);

        mav.addObject("prefecture", pref);
        return mav;
    }


}

