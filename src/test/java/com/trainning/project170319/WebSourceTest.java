package com.trainning.project170319;

import org.junit.Test;

public class WebSourceTest {

    @Test
    public void testdownloadPage() {
        // 模拟爬百度主页，因为内容比较少
        String path = "https://www.baidu.com/";
        String downloadPage = WebSource.downloadPage(path);
        System.out.println(downloadPage);
    }

    @Test
    public void testdownloadPage2() {
      //可以使用下面的方式把百度搜索的结果拿到
        String keyword ="java";
        String path = "http://www.baidu.com/s?word="+keyword;
        String downloadPage = WebSource.downloadPage2(path);
        System.out.println(downloadPage);
    }

    @Test
    public void testdownloadPage3() {
        WebSource.downloadPage3();
    }
}
