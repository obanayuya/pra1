package test;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by yuya on 西暦16/08/14.
 */
@Getter
@Setter
public class Rectangle {
    private int height;
    private int width;

//    private int area;
//    private  int perimeter;   //これがあると何回も各setterに書かないといけないから。 getに処理を書く


    //    コンストラクタ
    public Rectangle(int height, int width) {
        this.height = height;
        this.width = width;

//        this.area = height * width;
//        this.perimeter = height * 2 + width * 2;

    }


    //  getter
//    public int getWidth() {
//        return width;
//    }
//
//    public int getHeight() {
//        return height;
//    }

    public int getArea() {

//        return area;
        return width * height;
    }

    public int getPerimeter() {
//        return perimeter;
        return height * 2 + width * 2;

    }
    //        return height * width;
    //        areaでなくheight * widthにするとareaプロパティもいらない



    //setter
//    public void setHeight(int height) {
//        this.height = height;
//
////        this.area = height * width;  //これを書かないとareaは書き変わらない。　setHeightが呼ばれたときに磨る処理を書くから。メソッドだから値を書き換えるだけでなく他の処理も追加できる。
////        this.perimeter = height * 2 + width * 2;
//
//    }
//
//    public void setWidth(int width) {
//        this.width = width;
//
////        this.area = height * width;
////        this.perimeter = height * 2 + width * 2;
//    }

//    public void setArea(int area) {
//        this.area = area;
//    }

//    public void setPerimeter(int perimeter) {
//        this.perimeter = perimeter;
//    }
}
