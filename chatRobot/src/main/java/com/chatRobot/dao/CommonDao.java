package com.chatRobot.dao;
import com.chatRobot.model.Article;
import com.chatRobot.model.Product;
import com.chatRobot.utils.LoginUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CommonDao {

	private Connection conn = null;
    private Statement stmt = null;

	public CommonDao(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Properties properties = new Properties();
			FileInputStream fis = null;
			String url = null;
			try {
				fis = new FileInputStream(new File(CommonDao.class.getResource("/").getPath()+"jdbc.properties"));
				properties.load(fis);
				url = properties.getProperty("jdbc.url") + "&user=" + properties.getProperty("jdbc.username") + "&password=" + properties.getProperty("jdbc.password");
			} catch (Exception e) {
				e.printStackTrace();
			}
			conn = DriverManager.getConnection(url);
			stmt = conn.createStatement();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 添加文章到数据库
	 * @param article
	 * @return
	 */
	public int addArticle(Article article) {
		try {
			String sql = "INSERT INTO article(collarTime,articleTitle,articleSubTitle,articleAuthor,updateTime,articleTag,shopBuyLink,articleContent,articleImage,articleArea,articleLanguage,articleType,articleLink,issueState,articleLanguageType,articleAreaType,webSiteType) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,article.getCollarTime());
			ps.setString(2,article.getArticleTitle());
			ps.setString(3,article.getArticleSubTitle());
			ps.setString(4,article.getArticleAuthor());
			ps.setString(5,article.getUpdateTime());
			ps.setString(6,article.getArticleTag());
			ps.setString(7,article.getShopBuyLink());
			ps.setString(8,article.getArticleContent());
			ps.setString(9,article.getArticleImage());
			ps.setString(10,article.getArticleArea());
			ps.setString(11,article.getArticleLanguage());
			ps.setInt(12,article.getArticleType());
			ps.setString(13,article.getArticleLink());
			ps.setString(14,Article.ARTICLE_NO_ISSUE);
			ps.setInt(15,article.getArticleLanguageType());
			ps.setInt(16,article.getArticleAreaType());
			ps.setInt(17,article.getWebSiteType());

			return ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

		}
		return -1;
	}

	/**
	 * 添加商品到数据库
	 * @param p
	 */
    public int addProduct(Product p) {
		try {
			String sql = "INSERT INTO product(prodcutTitle,productPrice,productImage,productLink) VALUES (?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,p.getProductTitle());
			ps.setString(2,p.getProductPrice());
			ps.setString(3,p.getProductImage());
			ps.setString(4,p.getProductLink());
			return ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return -1;
	}

	/**
	 * 根据标题查询数据列表
	 * @param articleTitle
	 * @return
	 */
	public List<Article> queryArticle(String articleTitle){
		String sql = "select * from article where articleTitle = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,articleTitle);
            ResultSet resultSet = ps.executeQuery();
            List<Article> articles = new ArrayList<>();
            while (resultSet.next()){
                Article article = new Article();
                article.setArticleId(resultSet.getInt("articleId"));
                article.setCollarTime(resultSet.getString("collarTime"));
                article.setArticleTitle(resultSet.getString("articleTitle"));
                article.setArticleSubTitle(resultSet.getString("articleSubTitle"));
                article.setArticleAuthor(resultSet.getString("articleAuthor"));
                article.setUpdateTime(resultSet.getString("updateTime"));
                article.setArticleTag(resultSet.getString("articleTag"));
                article.setShopBuyLink(resultSet.getString("shopBuyLink"));
                article.setArticleContent(resultSet.getString("articleContent"));
                article.setArticleImage(resultSet.getString("articleImage"));
                article.setArticleArea(resultSet.getString("articleArea"));
                article.setArticleLanguage(resultSet.getString("articleLanguage"));
                article.setArticleType(resultSet.getInt("articleType"));
                article.setArticleLink(resultSet.getString("articleLink"));
				article.setArticleAreaType(resultSet.getInt(Article.ARTICLE_AREA_TYPE));
				article.setArticleType(resultSet.getInt(Article.ARTICLE_TYPE));
				article.setArticleLanguageType(resultSet.getInt(Article.ARTICLE_LANGUAGE_TYPE));
				article.setWebSiteType(resultSet.getInt("webSiteType"));
                articles.add(article);
            }
            return articles;
        } catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}