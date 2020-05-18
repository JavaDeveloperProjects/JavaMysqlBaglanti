package mysqlOrnekBaglanti;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public class databasee {

	private String kullaniciadi="root";
	private String parola="";
	private String host="127.0.0.1";
	private String datab="kullanici";
	private int port=3306;
	private Connection con=null;

	public void baglan() throws ClassNotFoundException, SQLException {
		String url="jdbc:mysql://"+this.host+":"+this.port+"/"+this.datab;
		Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection(url,kullaniciadi,parola);
		System.out.println("Baðlantý baþarýlý");
		
	}
	
	
	public void kullaniciListesi() {
		if(con==null) {
			System.out.println("Baðlantý yok baðlantýyý saðlýyorum"); 
			try {
				baglan();
			} catch (ClassNotFoundException | SQLException e) {
				System.out.println("Baðlantý yapýlamýyor...");
			}
		}
		try {
			Statement veriCek= (Statement) con.createStatement();

			ResultSet sonuc= veriCek.executeQuery("Select * from kullanicilar");
			while(sonuc.next()) {
				System.out.println("-"+sonuc.getString(1)+"-"+sonuc.getString(2)+"-"+sonuc.getString(3)+"-"+sonuc.getString(4));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	public boolean kullaniciKontrol(String Kadi,String sifre) {
		
		if(con==null) {
			System.out.println("Baðlantý yok baðlantýyý saðlýyorum"); 
			try {
				baglan();
			} catch (ClassNotFoundException | SQLException e) {
				System.out.println("Baðlantý yapýlamýyor...");
			}
		}
		try {
			Statement veriCek= (Statement) con.createStatement();

			ResultSet sonuc= veriCek.executeQuery("Select * from kullanicilar where  Adi='"+Kadi+"'");
			sonuc.next();
			
			return sifre.equals( sonuc.getString(2));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void kullaniciEkle(String ad,String isim,String sifre,int tel) throws SQLException {
		ad="'"+ad+"'";
		sifre="'"+sifre+"'";
		isim="'"+isim+"'";
		String sorgu="insert into kullanicilar(Adi,sifre,konum,tel)"+"values("+ad+","+sifre+","+isim+","+tel+")";
		Statement sta=(Statement)con.createStatement();
		int executeUpdate=sta.executeUpdate(sorgu);
		
	}
	
}
