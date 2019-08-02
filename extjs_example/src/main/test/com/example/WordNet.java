package com.example;

import edu.mit.jwi.Dictionary;
import edu.mit.jwi.IDictionary;
import edu.mit.jwi.IRAMDictionary;
import edu.mit.jwi.RAMDictionary;
import edu.mit.jwi.data.ILoadPolicy;
import edu.mit.jwi.item.*;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

/**
 * WordNet Test
 */
public class WordNet {

    /**
     * hypernyms() # 上位（父类）
     * hyponyms() # 下位（子类）
     * <p>
     * lemma_names() # 同义
     * antonyms() # 反义
     * <p>
     * entailments() # 蕴涵关系
     * <p>
     * part_meronyms() # 部分
     * substance_meronyms() # 实质
     * member_holonyms() # 成员
     * <p>
     * path_similarity()　#　相似度
     * lowest_common_hypernyms() # 在何种层面相似
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

//        testDictionary(); // 测试库

//        testRAMDictionary(new File("E:\\wordnettest\\test1.txt")); // 加载wordnet到Memony

//        demonstrateExportAndLoad(new File("E:\\wordnettest\\test2.txt")); // Not in GZIP format

//        getSynonyms(getDict()); // 同义词

//        getHypernyms(getDict()); // 上义词
    }

    public static IDictionary getDict() throws Exception {
        //建立指向WordNet词典目录的URL。
//        String wnhome = System.getenv("WNHOME");
//        String path = wnhome + File.separator + "dict";
        URL url = new URL("file", null, "E:\\dict");

        //建立词典对象并打开它。
        IDictionary dict = new Dictionary(url);
        dict.open();
        return dict;
    }

    // getting started
    @Test
    public void testDictionary() throws IOException {
        //建立指向WordNet词典目录的URL。
//        String wnhome = System.getenv("WNHOME");
//        String path = wnhome + File.separator + "dict";
        URL url = new URL("file", null, "E:\\dict");

        //建立词典对象并打开它。
        IDictionary dict = new Dictionary(url);
        dict.open();

        //查询money这个词的第一种意思。POS后面的参数表示要选的哪种词性的含义
        IIndexWord idxWord = dict.getIndexWord("good", POS.ADJECTIVE); // 名词，动词，副词
        System.out.println("idxWord:" + idxWord);
        System.out.println("wordIDs:" + idxWord.getWordIDs());
        IWordID wordID = (IWordID) idxWord.getWordIDs().get(0);
        IWord word = dict.getWord(wordID);
        System.out.println("Id(wordID) = " + wordID);
        System.out.println("Lemma(词根) = " + word.getLemma());
        System.out.println("Gloss（释义） = " + word.getSynset().getGloss());
        System.out.println("Synset(同义词) = " + word.getSynset());
        System.out.println("HYPERNYM(上位词) = " + word.getSynset().getRelatedSynsets(Pointer.HYPERNYM));

        //第二种意思
        IWordID wordID2 = (IWordID) idxWord.getWordIDs().get(1);
        IWord word2 = dict.getWord(wordID2);
        System.out.println("Gloss（释义）2 = " + word2.getSynset().getGloss());

        //第三种意思
        IWordID wordID3 = (IWordID) idxWord.getWordIDs().get(2);
        IWord word3 = dict.getWord(wordID3);
        System.out.println("Gloss（释义）3 = " + word3.getSynset().getGloss());

        System.out.println("-------IDictionary---------");
        System.out.println(dict.getExceptionEntry("dog", POS.NOUN));
        IIndexWord dog = dict.getIndexWord("dog", POS.NOUN);
        System.out.println(dog);

        System.out.println("-------IIndexWord----------");
        System.out.println(dog.getPointers());
        System.out.println(dog.getLemma());
        List<IWordID> wordIDs = dog.getWordIDs();
        System.out.println(wordIDs);

        System.out.println("--------IWordID--------");
        for (IWordID iw: wordIDs) {//词根：同义词ID：词类计数：词类型
            System.out.println(iw.getLemma() + " <> " + iw.getSynsetID() + "<>" + iw.getWordNumber() + "<>" + iw.getPOS());
        }

        System.out.println("-------IWord---------");
        IWord word1 = dict.getWord(wordIDs.get(0));//获取词类1/7
        System.out.println(word1);
        System.out.println(word1.getLemma());//词根
        ISynset synset = word1.getSynset();//同义词集
        System.out.println(synset);
        System.out.println(word1.getLexicalID());//词汇ID：0
        System.out.println(word1.getRelatedWords());//关系词：[]
        System.out.println(word1.getRelatedWords(Pointer.HYPERNYM));//上位词：[]
        System.out.println(word1.getSenseKey());//语义key：dog%1:05:00::

        System.out.println("--------ISynset---------");
        System.out.println(synset.getWords());//同义词
        System.out.println(synset.getWord(1));//从1开始计
        System.out.println(synset.getGloss());//同义词简介
        System.out.println(synset.getRelatedSynsets());//所有的关系同义词（上下位，成员，部分）
        System.out.println(synset.getRelatedSynsets(Pointer.HYPERNYM));//getRelatedMap()中key为上位词的同义词集
        System.out.println(synset.getOffset());//词库中的偏移量
        System.out.println(synset.getRelatedMap());//获取同义词的关系集合，key就是Pointer表示的枚举
        System.out.println(synset.getType());//词类型1，表示名词
        System.out.println(synset.getPOS());//词类型noun，表示名词
        System.out.println(synset.getID());//获取ISynsetID
    }


    // Loading Wordnet into Memory
    @Test
    public  void testRAMDictionary() throws Exception {
        //建立指向WordNet词典目录的URL。
//        String wnhome = System.getenv("WNHOME");
//        String path = wnhome + File.separator + "dict";
        URL url = new URL("file", null, "E:\\dict");
        IRAMDictionary dict = new RAMDictionary(url);

        // construct the dictionary object and open it
//        IRAMDictionary dict = new RAMDictionary(wnDir, ILoadPolicy.NO_LOAD);
        dict.open();

        // do something slowly
        trek(dict);

        // now load into memory
        System.out.print("\\ nLoading Wordnet into memory ... ");
        long t = System.currentTimeMillis();
        dict.load(true);
        System.out.printf(" done (%1 d msec )\n", System.currentTimeMillis() - t);

        // do the same thing again , only faster
        trek(dict);
    }

    public static void trek(IDictionary dict) {
        int tickNext = 0;
        int tickSize = 20000;
        int seen = 0;
        System.out.print(" Treking across Wordnet ");
        long t = System.currentTimeMillis();
        for (POS pos : POS.values())
            for (Iterator<IIndexWord> i = dict.getIndexWordIterator(pos); i.
                    hasNext(); )
                for (IWordID wid : i.next().getWordIDs()) {
                    seen += dict.getWord(wid).getSynset().getWords().size();
                    if (seen > tickNext) {
                        System.out.print('.');
                        tickNext = seen + tickSize;
                    }
                }
        System.out.printf(" done (%1 d msec )\n", System.currentTimeMillis() - t);
        System.out.println("In my trek I saw " + seen + " words ");
    }

    // Loading Wordnet from a Stream
    @Test
    public void demonstrateExportAndLoad() throws IOException {
        File wnDir = new File("");
        // load RAM dictionary data into memory
        System.out.print(" Loading dictionary data ... ");
        long t = System.currentTimeMillis();
        IRAMDictionary ramDict = new RAMDictionary(wnDir);
        ramDict.setLoadPolicy(ILoadPolicy.IMMEDIATE_LOAD);
        ramDict.open();
        t = System.currentTimeMillis() - t;
        System.out.printf(" done (%1 d sec )\n", t / 1000);

        // we will store our exported Wordnet data here
        File exFile = File.createTempFile(" JWI_Export_ ", ".wn");
        exFile.deleteOnExit();

        // export in - memory data
        System.out.print(" Exporting dictionary data ... ");
        t = System.currentTimeMillis();
        ramDict.export(new FileOutputStream(exFile));
        ramDict.close();
        t = System.currentTimeMillis() - t;
        System.out.printf(" done (%1 d sec )\n", t / 1000);

        // take a look at the file
        System.out.printf(" Export is %1d MB\n", exFile.length() / 1048576);

        // load RAM dictionary data
        System.out.print(" Loading from exported data ... ");
        t = System.currentTimeMillis();
        ramDict = new RAMDictionary(exFile);
        ramDict.open();
        t = System.currentTimeMillis() - t;
        System.out.printf(" done (%1 d sec )\n", t / 1000);

        trek(ramDict);
    }

    // retrieve the synonyms of a word
    @Test
    public void getSynonyms() throws Exception {
        IDictionary dict = getDict();
        // look up first sense of the word " dog "
        IIndexWord idxWord = dict.getIndexWord("dog", POS.NOUN);
        IWordID wordID = idxWord.getWordIDs().get(0); // 1st meaning
        IWord word = dict.getWord(wordID);
        ISynset synset = word.getSynset();

        // iterate over words associated with the synset
        for (IWord w : synset.getWords())
            System.out.println(w.getLemma()); //
    }

    // retrieve the hypernyms of a word
    @Test
    public void getHypernyms() throws Exception {
        IDictionary dict = getDict();
        // get the synset
        IIndexWord idxWord = dict.getIndexWord(" dog ", POS.NOUN);
        IWordID wordID = idxWord.getWordIDs().get(0); // 1st meaning
        IWord word = dict.getWord(wordID);
        ISynset synset = word.getSynset();
        System.out.println("");

        // get the hypernyms
        List<ISynsetID> hypernyms =
                synset.getRelatedSynsets(Pointer.HYPERNYM);

        // print out each h y p e r n y m s id and synonyms
        List<IWord> words;
        for (ISynsetID sid : hypernyms) {
            words = dict.getSynset(sid).getWords();
            System.out.print(sid + " {");
            for (Iterator<IWord> i = words.iterator(); i.hasNext(); ) {
                System.out.print(i.next().getLemma());
                if (i.hasNext())
                    System.out.print(", ");
            }
            System.out.println("}");
        }
    }

    @Test
    public void test() throws Exception {
        IDictionary dict = getDict();
        IIndexWord idxWord = dict.getIndexWord(" wonderful ", POS.ADJECTIVE);// noun,名词
        System.out.println("idxWord: " + idxWord);
        System.out.println("WordIDs: " + idxWord.getWordIDs()); // 获取狗的相关词类id
//        String lemma = idxWord.getLemma(); // 获取词类，如果要查询一个词的同义词，难道不是使用这个，而是使用相关词，进行查询
        IWordID wordID = idxWord.getWordIDs().get(0); // 1st meaning

        IWord word = dict.getWord(wordID); // 通过id获取词类
        System.out.println("word: " + word); // word = word.getID()
        ISynset synset = word.getSynset(); // 获取同义词
        System.out.println("synset: "+ synset);


        // 获取上位词
        List<ISynsetID> hypernyms = synset.getRelatedSynsets(Pointer.HYPERNYM); // 同义词获取上位词

        // print out each h y p e r n y m s id and synonyms
        List<IWord> words;
        for (ISynsetID sid : hypernyms) {
            words = dict.getSynset(sid).getWords();
            System.out.print(sid + " {");
            for (Iterator<IWord> i = words.iterator(); i.hasNext(); ) {
                System.out.print(i.next().getLemma());
                if (i.hasNext())
                    System.out.print(", ");
            }
            System.out.println("}");
        }
    }
}
