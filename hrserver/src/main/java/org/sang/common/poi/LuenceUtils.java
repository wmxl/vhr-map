package org.sang.common.poi;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.sang.bean.Company;
import org.sang.bean.Employee;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LuenceUtils {
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
            System.out.println("address: " + doc.get("address"));
            // 取出文档得分
            System.out.println("得分： " + scoreDoc.score);
        }
    }

    static public List<String> searchResults(Query query) throws Exception {
        List<String > strs = new ArrayList<>();
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
            System.out.println("address: " + doc.get("address"));
            // 取出文档得分
            System.out.println("得分： " + scoreDoc.score);
            String title =  doc.get("title");
            strs.add(title);

        }
        return strs;
    }

    static public void testTermQuery(String s1) throws Exception {
        // 创建词条查询对象
        Query query = new TermQuery(new Term("title", s1));
        search(query);
    }

    static public void TermQueryStrings(String s1) throws Exception {
        // 创建词条查询对象
        Query query = new TermQuery(new Term("title", s1));
        System.out.println(searchResults(query));;
    }
}
