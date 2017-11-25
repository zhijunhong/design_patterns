package entity;

import com.fudaojun.fudaojunlib.utils.LibUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZhijunHong on 2017/11/25.
 */

public class WordDocumentDeep implements Cloneable {
    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    public List<String> getImages() {
        return mImages;
    }

    public void addImages(String images) {
        this.mImages.add(images);
    }

    private String mText;
    private List<String> mImages = new ArrayList<>();

    public WordDocumentDeep() {
        LibUtils.myLog("-------WordDocument构造函数---------");
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        try {
            WordDocumentDeep doc = (WordDocumentDeep) super.clone();
            doc.mText = this.mText;
            //对mImages 对象也调用 clone()函数，进行深拷贝
//            doc.mImages = (ArrayList<String>)this.mImages.clone();
//            doc.mImages = this.mImages;
            return doc;
        } catch (Exception e) {
            return null;
        }
    }

    public void showDocument() {
        LibUtils.myLog("--------Word Content Start----------");

        LibUtils.myLog("Text:" + mText);
        LibUtils.myLog("Image List: ");
        for (String imageName : mImages) {
            LibUtils.myLog("image name:" + imageName);
        }

        LibUtils.myLog("--------Word Content End----------");
    }
}
