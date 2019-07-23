package com.example.test;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//@Service
//@Lazy(false)
//@Transactional
public class WordclassService {

//    @Autowired
//    PropertyPlaceholderRecorder recorder;

//    public void importWordclass(ImportRequest<BREWordclassCategory> importRequest, ObjectImportResponse importResponse) {
//
//        /**
//         * 外部导入参数：
//         *      actionParam：target_type，save_mode
//         *      actionFeature：K:V
//         *      list<BREWordclassCategory>：
//         */
//        Map<String, String> actionParam = importRequest.getActionParam();
//        // action param
//        String target_type = actionParam.get("target_type");
//        String save_mode = actionParam.get("save_mode");
//
//        Map<String, String> actionFeature = importRequest.getActionFeature();
//        // TODO action feature 导入特性，1.是否删除子分类以及词类名 2.是否验证词类的重复性 default：true
//        String feature1 = actionFeature.get("isClear");
//        String feature2 = actionFeature.get("isRepeat");
//
//        List<BREWordclassCategory> data = importRequest.getData();
//        // 判断数据的有效性 1.有没有同义词，数据的合法性 2.目录/名字是否相同，数据重复 3.同义词是否有重复
//        // 数据库中有没有相同的数据
//
//
//        if ("1".equals(target_type)) { // 新增
//
//        } else { // 修改
//
//        }
//    }

    private void valiWCC(List<BREWordclassCategory> data) {
        if (null == data || data.size() == 0) {
            return;
        }
        Set<String> WCCName = new HashSet<>();
        for (BREWordclassCategory WCC :
                data) {

            String categoryName = WCC.getName();
            List<BREWordclassCategory> categories = WCC.getCategories();
            List<String> synoWords = WCC.getSynoWords();

            boolean boo = WCCName.add(categoryName);
            if (!boo) {
                throw new RuntimeException("数据不合法，目录" + categoryName + "重复");
            }

            if (!StringUtils.isEmpty(categoryName) && (null == categories || categories.size() == 0)) {
                // 这是一个末级目录，就是一个词类
                valiSynoClassWord(synoWords);

            } else {
                // 这是一个目录
                if (null != synoWords && synoWords.size() > 0) {
                    valiSynoClassWord(synoWords);
                }
                valiWCC(categories);
            }
        }


    }

    private void valiSynoClassWord(List<String> synoWords) {
        if (null == synoWords || synoWords.size() == 0) {
            throw new RuntimeException("数据不合法，同义词为空");
        }

        Set<String> _synoWords = new HashSet<>();
        for (String synoWord :
                synoWords) {
            boolean notRepeat = _synoWords.add(synoWord);
            if (!notRepeat) {
                throw new RuntimeException("数据不合法，同义词" + synoWord + "重复");
            }
        }
    }

    // TODO test
    public static void main(String[] args) {
        BREWordclassCategory b1 = new BREWordclassCategory();
        b1.setName("1word");
        List<BREWordclassCategory> list1 = new ArrayList<BREWordclassCategory>();
        b1.setCategories(list1);

        BREWordclassCategory b2 = new BREWordclassCategory();
        b2.setName("单词");
        list1.add(b2);
        List<String> syno = new ArrayList<>();
        b2.setSynoWords(syno);
        syno.add("我");
        syno.add("们");

        BREWordclassCategory b3 = new BREWordclassCategory();
        b3.setName("cate1");
        list1.add(b3);
        List<BREWordclassCategory> list3 = new ArrayList<BREWordclassCategory>();
        b3.setCategories(list3);

        BREWordclassCategory b4 = new BREWordclassCategory();
        list3.add(b4);
        b4.setName("双词");
        List<String> syno4 = new ArrayList<>();
        b4.setSynoWords(syno4);
        syno4.add("今天");
        syno4.add("明天");


        BREWordclassCategory b5 = new BREWordclassCategory();
        list3.add(b5);
        b5.setName("双词");
        List<String> syno5 = new ArrayList<>();
        b5.setSynoWords(syno5);
        syno5.add("今天2");
        syno5.add("今天2");


        ArrayList<BREWordclassCategory> bc = new ArrayList<>();
        bc.add(b1);
        WordclassService ws = new WordclassService();
        ws.valiWCC(bc);
    }
}
