package mysqlOrnekBaglanti;

import java.sql.SQLException;

public class main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		databasee db=new databasee();
		db.baglan();
		
		db.kullaniciListesi();
		
		System.out.println(db.kullaniciKontrol("asd", "1234"));
	}

}
