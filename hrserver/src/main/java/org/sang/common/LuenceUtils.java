package org.sang.common;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.sang.bean.Employee;
import org.sang.bean.Company;
import org.sang.controller.CompanyController;
import org.sang.mapper.CompanyMapper;
import org.sang.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.wltea.analyzer.lucene.IKAnalyzer;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.sang.mapper.CompanyMapper;

public class LuenceUtils {

    public void createIndex() throws IOException {
        Collection<Document> docs = new ArrayList<Document>();

        // 创建文档对象
        Document document1 = new Document();
        document1.add(new StringField("id", "1", Field.Store.YES));
        document1.add(new TextField("name", "四川省绵阳市鸿永盛模塑有限公司", Field.Store.YES));
        document1.add(new TextField("address", "绵阳市安州区花荄镇工业园区青安片纵一路11号", Field.Store.YES));
        document1.add(new StringField("longitude", "104.5845800000", Field.Store.YES));
        document1.add(new StringField("latitude", "31.5116310000", Field.Store.YES));
        docs.add(document1);
        // 创建文档对象
        Document document2 = new Document();
        document2.add(new StringField("id", "2", Field.Store.YES));
        document2.add(new TextField("name", "四川三鑫南蕾气门座制造有限公司", Field.Store.YES));
        document2.add(new TextField("address", "南部县南隆镇工业集中区梁家垭大道", Field.Store.YES));
        document2.add(new StringField("longitude", "106.0514500000", Field.Store.YES));
        document2.add(new StringField("latitude", "31.3388200000", Field.Store.YES));
        docs.add(document2);
        // 创建文档对象
        Document document3 = new Document();
        document3.add(new StringField("id", "3", Field.Store.YES));
        document3.add(new TextField("name", "清远市新业电器有限公司", Field.Store.YES));
        document3.add(new TextField("address", "清远市龙塘镇银盏大份田村１０７国道旁", Field.Store.YES));
        document3.add(new StringField("longitude", "113.1171500000", Field.Store.YES));
        document3.add(new StringField("latitude", "23.5671800000", Field.Store.YES));
        docs.add(document3);
        // 创建文档对象
        Document document4 = new Document();
        document4.add(new StringField("id", "4", Field.Store.YES));
        document4.add(new TextField("name", "四川科盛汽车零部件制造有限公司", Field.Store.YES));
        document4.add(new TextField("address", "四川省南充市南部县工业集中区管理委员会办公室", Field.Store.YES));
        document4.add(new StringField("longitude", "104.9937440000", Field.Store.YES));
        document4.add(new StringField("latitude", "29.5881940000", Field.Store.YES));
        docs.add(document4);
        // 创建文档对象
        Document document5 = new Document();
        document5.add(new StringField("id", "5", Field.Store.YES));
        document5.add(new TextField("name", "四川国跃新能源汽车", Field.Store.YES));
        document5.add(new TextField("address", "四川省内江市经济技术开发区汉晨路888号1幢", Field.Store.YES));
        document5.add(new StringField("longitude", "106.0901640000", Field.Store.YES));
        document5.add(new StringField("latitude", "31.3552250000", Field.Store.YES));

        // 索引目录类,指定索引在硬盘中的位置
        Directory directory = FSDirectory.open(new File("d:\\indexDir"));
        // 引入IK分词器
        Analyzer analyzer = new IKAnalyzer();
        // 索引写出工具的配置对象
        IndexWriterConfig conf = new IndexWriterConfig(Version.LATEST, analyzer);
        // 设置打开方式：OpenMode.APPEND 会在索引库的基础上追加新索引。OpenMode.CREATE会先清空原来数据，再提交新的索引
        conf.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
        // 创建索引的写出工具类。参数：索引的目录和配置信息
        IndexWriter indexWriter = new IndexWriter(directory, conf);
        // 把文档集合交给IndexWriter
        indexWriter.addDocuments(docs);
        // 提交
        indexWriter.commit();
        // 关闭
        indexWriter.close();
    }

    public void createIndexFromDB() throws IOException {
        Collection<Document> docs = new ArrayList<Document>();

        List <Company> coms = new CompanyService().Companies();

        for (int i = 0; i <coms.size(); i++) {
            Document doc = new Document();
            Company com = coms.get(i);
            doc.add(new StringField("id", String.valueOf(i), Field.Store.YES));
            doc.add(new TextField("name", com.getName(), Field.Store.YES));
            doc.add(new TextField("address", com.getAddress(), Field.Store.YES));
            doc.add(new StringField("longitude", com.getLongitude(), Field.Store.YES));
            doc.add(new StringField("latitude", com.getLatitude(), Field.Store.YES));
            docs.add(doc);
        }
        // 索引目录类,指定索引在硬盘中的位置
        Directory directory = FSDirectory.open(new File("d:\\indexDir"));
        // 引入IK分词器
        Analyzer analyzer = new IKAnalyzer();
        // 索引写出工具的配置对象
        IndexWriterConfig conf = new IndexWriterConfig(Version.LATEST, analyzer);
        // 设置打开方式：OpenMode.APPEND 会在索引库的基础上追加新索引。OpenMode.CREATE会先清空原来数据，再提交新的索引
        conf.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
        // 创建索引的写出工具类。参数：索引的目录和配置信息
        IndexWriter indexWriter = new IndexWriter(directory, conf);
        // 把文档集合交给IndexWriter
        indexWriter.addDocuments(docs);
        // 提交
        indexWriter.commit();
        // 关闭
        indexWriter.close();
    }

    static public void search(Query query) throws Exception {
        // 索引目录对象
        Directory directory = FSDirectory.open(new File("d://indexDir"));
        // 索引读取工具
        IndexReader reader = DirectoryReader.open(directory);
        // 索引搜索工具
        IndexSearcher searcher = new IndexSearcher(reader);

        // 搜索数据,两个参数：查询条件对象要查询的最大结果条数
        // 返回的结果是 按照匹配度排名得分前N名的文档信息（包含查询到的总条数信息、所有符合条件的文档的编号信息）。
        TopDocs topDocs = searcher.search(query, 10);
        // 获取总条数
        System.out.println("本次搜索共找到" + topDocs.totalHits + "条数据");
        // 获取得分文档对象（ScoreDoc）数组.SocreDoc中包含：文档的编号、文档的得分
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;

        for (ScoreDoc scoreDoc : scoreDocs) {
            // 取出文档编号
            int docID = scoreDoc.doc;
            // 根据编号去找文档
            Document doc = reader.document(docID);
            System.out.println("id: " + doc.get("id"));
            System.out.println("title: " + doc.get("title"));
            // 取出文档得分
            System.out.println("得分： " + scoreDoc.score);
        }
    }

    static public List<Company> searchResults(Query query) throws Exception {
        List<Company > coms = new ArrayList<>();
        // 索引目录对象
        Directory directory = FSDirectory.open(new File("d://indexDir"));
        // 索引读取工具
        IndexReader reader = DirectoryReader.open(directory);
        // 索引搜索工具
        IndexSearcher searcher = new IndexSearcher(reader);
        // 搜索数据,两个参数：查询条件对象要查询的最大结果条数
        // 返回的结果是 按照匹配度排名得分前N名的文档信息（包含查询到的总条数信息、所有符合条件的文档的编号信息）。
        TopDocs topDocs = searcher.search(query, 10);
        // 获取总条数
        System.out.println("本次搜索共找到" + topDocs.totalHits + "条数据");
        // 获取得分文档对象（ScoreDoc）数组.SocreDoc中包含：文档的编号、文档的得分
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;


//        =-------高亮 部分----
        // 格式化器
        Formatter formatter = new SimpleHTMLFormatter("【", "】");
        QueryScorer scorer = new QueryScorer(query);
        // 准备高亮工具
        Highlighter highlighter = new Highlighter(formatter, scorer);

        for (ScoreDoc scoreDoc : scoreDocs) {
            // 取出文档编号
            int docID = scoreDoc.doc;
            // 根据编号去找文档
            Document doc = reader.document(docID);
            System.out.println("id: " + doc.get("id"));

            String hTitle = highlighter.getBestFragment(new IKAnalyzer(), "name", doc.get("name"));

//            System.out.println("name: " + hTitle);

            System.out.println("name: " + hTitle);
            System.out.println("address: " + doc.get("address"));
            System.out.println("longitude: " + doc.get("longitude"));
            System.out.println("latitude: " + doc.get("latitude"));
            // 取出文档得分
            System.out.println("得分： " + scoreDoc.score);
            Company com = new Company();
            com.setName(hTitle);
            com.setAddress(doc.get("address"));
            com.setLongitude(doc.get("longitude"));
            com.setLatitude(doc.get("latitude"));

            coms.add(com);
        }
        return coms;
    }

    static public void testTermQuery(String s1) throws Exception {
        // 创建词条查询对象
        Query query = new TermQuery(new Term("title", s1));
        search(query);
    }

    static public  List<Company> TermQueryCompanys(String s1) throws Exception {
        // 创建词条查询对象
        Query query = new TermQuery(new Term("name", s1));
        return searchResults(query);
    }

    public static void main(String[] args) throws Exception {
//        new LuenceUtils().createIndex();
        System.out.println(LuenceUtils.TermQueryCompanys("公司"));
    }
}
