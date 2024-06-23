package bookmanagement.persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmanagement.models.Author;



public class AuthorRepository {
	// connection
	public static Connection con = null;
	static {
		con = MyConnection.getConnection();

	}
	//insert
	public int add(Author author) {
		
		int result=0;
		String sql="INSERT INTO author(name,address,copyright_contact,contact_email) VALUES(?,?,?,?)";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1,author.getName());
			ps.setString(2, author.getAddress());
			ps.setString(3,author.getCopyright_contact());
			ps.setString(4, author.getContact_email());
			
			result=ps.executeUpdate();
			
		}catch(SQLException e) {
			result=0;
			System.out.println("Author insert err: "+e);
		}
		return result;
	}
	
	//update
	public int edit(Author author) {
		int result=0;
		String sql="UPDATE author SET name=?,address=?,copyright_contact=?,contact_email=? WHERE id=?";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			
			ps.setString(1, author.getName());
			ps.setString(2,author.getAddress());
			ps.setString(3, author.getCopyright_contact());
			ps.setString(4,author.getContact_email());
			ps.setInt(5, author.getId());
			
			result=ps.executeUpdate();
			
		}catch(SQLException e) {
			result=0;
			System.out.println("author edit err : "+e);
		}
		return result;
	}

	//delete
	public int delete(int id) {
		int result=0;
		String sql="DELETE FROM author WHERE id=?";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			
			ps.setInt(1,id);
			
			
			result=ps.executeUpdate();
			
		}catch(SQLException e) {
			result=0;
			System.out.println("author delete err : "+e);
		}
		return result;
	}


	//selectAll
	public List<Author>getAll(){
		List<Author> authors=new ArrayList<>();
		String sql="SELECT * FROM author";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				Author author=new Author();
				author.setId(rs.getInt("id"));
				author.setName(rs.getString("name"));
				author.setAddress(rs.getString("address"));
				author.setCopyright_contact(rs.getString("copyright_contact"));
				author.setContact_email(rs.getString("contact_email"));
				
				authors.add(author);
				
			}
			
			
		}catch(SQLException e) {
			System.out.println("author select err : "+e);
		}
		
		return authors;
	}
	
	//Selectone
	public Author getById(int id) {
		Author author=null;
		String sql="SELECT * FROM author WHERE id=?";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				author=new Author();
				author.setId(rs.getInt("id"));
				author.setName(rs.getString("name"));
				author.setAddress(rs.getString("address"));
				author.setCopyright_contact(rs.getString("copyright_contact"));
				author.setContact_email(rs.getString("contact_email"));
				
				
			}
			
			
		}catch(SQLException e) {
			author=null;
			System.out.println("author getbycode err : "+e);
		}
		return author;
		
	}
	
	//GetNameByBookCode
	public List<Author> getAuthorsByBookCode(String code) {
		List<Author> authors = new ArrayList<>();
		String sql="SELECT a.*  FROM author a inner join book_has_author  ba on a.id=ba.author_id " + " WHERE ba.book_code=?";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, code);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				Author author=new Author();
				author.setId(rs.getInt("id"));
				author.setName(rs.getString("name"));
				author.setAddress(rs.getString("address"));
				author.setCopyright_contact(rs.getString("copyright_contact"));
				author.setContact_email(rs.getString("contact_email"));
				
				authors.add(author);
				
			}
			
			
		}catch(SQLException e) {
			System.out.println("authors getAuthorsBycode err : "+ e );
		}
		return authors;
		
	}
	
	
}