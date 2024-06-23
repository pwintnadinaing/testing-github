package bookmanagement.persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmanagement.models.Author;
import bookmanagement.models.Book;

public class BookRepository {
		//connection
		public static Connection con = null;
		static {
			con = MyConnection.getConnection();
		}
		
		//crud
		//create
		public int add(Book book) {
			int result = 0;
			String sql = "INSERT INTO book(code, name, price) VALUES(?, ?, ?)";
			try {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, book.getCode());
				ps.setString(2, book.getName());
				ps.setDouble(3, book.getPrice());
				//ps.setInt(3, book.getAuthor_id());								
				ps.executeUpdate();		
				
				for(Author author : book.getAuthors()) {
					sql = "INSERT INTO book_has_author(book_code, author_id) VALUES(?, ?)";
					
					ps = con.prepareStatement(sql);
					ps.setString(1, book.getCode());
					ps.setInt(2, author.getId());
					
					result = ps.executeUpdate();
				}
				
			}catch (SQLException e) {
				result = 0;
				System.out.println("book insert error: " + e);
			}
			return result;
		}
		//update
				public int edit(Book book) {
					int result = 0;
					String sql = "UPDATE book SET name=?, price=? where code=?";
					try {
						PreparedStatement ps = con.prepareStatement(sql);
						ps.setString(1, book.getName());
						ps.setDouble(2, book.getPrice());
						ps.setString(3, book.getCode());
						result = ps.executeUpdate();				
						
						sql = "DELETE FROM book_has_author WHERE book_code=?";
						ps = con.prepareStatement(sql);
						ps.setString(1, book.getCode());
						ps.executeUpdate();
						for(Author author : book.getAuthors()) {
							sql = "INSERT INTO book_has_author(book_code, author_id) VALUES(?, ?)";
							
							ps = con.prepareStatement(sql);
							ps.setString(1, book.getCode());
							ps.setInt(2, author.getId());
							
							result = ps.executeUpdate();
						}
												
					}catch (SQLException e) {
						result = 0;
						System.out.println("book update error: " + e);
					}
					return result;
				}
				
				//delete
				public int delete(String code) {
					int result = 0;
					String sql = "DELETE FROM book WHERE code=?";
					try {
						PreparedStatement ps = con.prepareStatement(sql);
						ps.setString(1, code);
					
						result = ps.executeUpdate();				
						
					}catch (SQLException e) {
						result = 0;
						System.out.println("book delete error: " + e);
					}
					return result;
				}
				
				//getAll
				public List<Book>getAll(){
					AuthorRepository authorRepo = new AuthorRepository();
					List<Book> books = new ArrayList<>();
					
					String sql = "SELECT * 		FROM book";
					try {
						PreparedStatement ps = con.prepareStatement(sql);
						ResultSet rs = ps.executeQuery();
						while(rs.next()) {
							Book book = new Book();
							book.setCode(rs.getString("code"));
							book.setName(rs.getString("name"));
							book.setPrice(rs.getDouble("price"));
							book.setAuthors(authorRepo.getAuthorsByBookCode(rs.getString("code")));
						
							books.add(book);
						}
						
					} catch (SQLException e) {
						System.out.println("Book Get All Err : " + e);
					}
					return books;
				}
				
				
		//getOne
		public  Book getByCode(String code) {
			Book book = null;
			AuthorRepository authorRepo = new AuthorRepository();
			String sql = "SELECT * FROM book WHERE code=?";
			try {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, code);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					 book = new Book();
					book.setCode(rs.getString("code"));
					book.setName(rs.getString("name"));
					book.setPrice(rs.getDouble("price"));
					
					book.setAuthors(authorRepo.getAuthorsByBookCode(rs.getString("code")));//relationtable
					//book.setAuthor_id(rs.getInt("author_id"));
					
				}
				
			} catch (SQLException e) {
				book = null;
				System.out.println("Book get By Code  Err : " + e);
			}
			return book;
		}
		}

