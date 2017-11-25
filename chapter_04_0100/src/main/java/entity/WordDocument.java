package entity;

import com.fudaojun.fudaojunlib.utils.LibUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZhijunHong on 2017/11/25.
 */

public class WordDocument implements Cloneable {
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

    public WordDocument() {
        LibUtils.myLog("-------WordDocument构造函数---------");
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        try {
            WordDocument doc = (WordDocument) super.clone();
            doc.mText = this.mText;
            doc.mImages = this.mImages;
            return doc;
        } catch (CloneNotSupportedException e) {
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
