package most.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class Form3 {
	private String ud;
	private String period;
	
	private String urlDBF;
	
	private Connection con;
	
	private List<VDF302> vdf302;
	

//	private Form3() {}
	
	public Form3(String urlDBF, String period) {
		this.urlDBF = urlDBF;
		this.period = period;
	}
	
/*	public Form3(String urlDBF, String period) {
		this.urlDBF = urlDBF;
		this.period = period;
//		this.vdf302 = vdf302;
	}*/
	
	public Form3(String urlDBF, String period, String ud) {
		this.urlDBF = urlDBF;
		this.period = period;
		this.ud = ud;
//		this.vdf302 = vdf302;
	}
	
	
	private ResultSet getResultSet(String sql) {
		Properties connInfo = new Properties();
		String className = null;
		String conString = null;
		ResultSet rs = null;
		
		/*if (System.getProperty("os.name").startsWith ("Windows")) {
			connInfo.put("charSet", "Cp1251");
			className = "sun.jdbc.odbc.JdbcOdbcDriver";
			conString = "jdbc:odbc:Driver={Microsoft dBase Driver (*.dbf)};DefaultDir="+urlDBF;
		} else {*/
			// Кодировка Cp866
			connInfo.put("charSet", "Cp866");
			connInfo.put("CODEPAGEID", "66");
			
			className = "com.hxtt.sql.dbf.DBFDriver";
			conString = "jdbc:dbf:///"+urlDBF;
//		}
		try {
			Class.forName(className);
			con=DriverManager.getConnection(conString, connInfo);
			
			Statement stm=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
/*			if (System.getProperty("os.name").toUpperCase().contains("LINUX"))
				try {
					Runtime.getRuntime().exec("sudo chmod 777 " + urlDBF +"VDF3"+period+"01.dbf");
				} catch (IOException e) {
					e.printStackTrace();
				}*/
			
			
			rs = stm.executeQuery(sql);
			

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	
	private String getR317(String recno) {
//		long startTime = System.currentTimeMillis();
		String val = "";
		for (int i=0; i<vdf302.size(); i++) {
			if (vdf302.get(i).A999.equals(recno)) {
				val += vdf302.get(i).val;
				if (val.length()==4) break;
			}
		}
//		long timeSpent = System.currentTimeMillis() - startTime;
//		System.out.println(recno + ": "+ timeSpent);
		return val;
	}
	
/*	private String getR317(String recno) {
		long startTime = System.currentTimeMillis();
		String val = "";	
//		String urlDBF = System.getProperty("user.dir") + "/DataBase/";
		Connection con2;
		Properties connInfo = new Properties();
		String className = null;
		String conString = null;
		ResultSet rs = null;
		
		String sql="SELECT f3.R317 "
				+ "FROM vdf3"+ period +"02 as f3 "
				+ "WHERE (f3.A999='" + recno + "') AND (f3.R317 <> '17')";
		
		connInfo.put("charSet", "Cp866");
		connInfo.put("CODEPAGEID", "66");
			
		className = "com.hxtt.sql.dbf.DBFDriver";
		conString = "jdbc:dbf:///"+urlDBF;
		try {
			Class.forName(className);
			con2=DriverManager.getConnection(conString, connInfo);
			
			Statement stm=con2.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);

			rs = stm.executeQuery(sql);
			
			if (rs.next()) val = rs.getString(1);
			
			con2.close();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		long timeSpent = System.currentTimeMillis() - startTime;
		System.out.println(ud + ": "+ timeSpent);
		return val;
	}*/
	
	
	
	public Map<String,String> getF3(){
		long startTime = System.currentTimeMillis();
		Map<String,String> form3 = null;
//		String urlDBF = System.getProperty("user.dir") + "/DataBase/";
		
		String sql="SELECT * "
				+ "FROM vdf3"+ period +"01 as f3 "
				+ "WHERE (f3.R303='" + ud + "') AND (f3.L998 <> 3)";
//				+ "ORDER BY f3.R305";


		ResultSet rs = getResultSet(sql);
		
		int count;
		try {
			count = rs.getMetaData().getColumnCount();
			form3 = new HashMap<String,String>(1);
			
			if (rs.next()) {
				rs.last();
//				System.out.println(ud);
				
				for(int i = 1; i<=count; i++) {
					String key = rs.getMetaData().getColumnName(i).substring(1);
					String value = rs.getString(i);
					
					if ((rs.getString(i)!=null)&&(!rs.getString(i).equals(""))) {
						
						if (key.equals("ECNO")) {
//							String v = value;
							value = getR317(value);
							if (!value.equals("")) {
//								System.out.println(v + " : " + value);
								key = "317";
							}
						}
						if (Strings.isDigit(key))
							form3.put(key, value);
					}
				}
				//Добавить ревизит
				form3.put("302", "1");
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		long timeSpent = System.currentTimeMillis() - startTime;
		System.out.println(ud + ": "+ timeSpent);
		return form3;
	}		

	//		form3.forEach( (k,v)-> System.out.print(k + ": " + v +" |") );
		// Параметры соединения с базой
/*		Properties connInfo = new Properties();
		String urlDBF = System.getProperty("user.dir") + "/DataBase/";
		String className = null;
		String conString = null;			
		
		if (System.getProperty("os.name").startsWith ("Windows")) {
			connInfo.put("charSet", "Cp1251");
			className = "sun.jdbc.odbc.JdbcOdbcDriver";
			conString = "jdbc:odbc:Driver={Microsoft dBase Driver (*.dbf)};DefaultDir="+urlDBF;
		} else {
			// Кодировка Cp866
			connInfo.put("charSet", "Cp866");
			connInfo.put("CODEPAGEID", "66");
			
			className = "com.hxtt.sql.dbf.DBFDriver";
			conString = "jdbc:dbf:///"+urlDBF;
		}
		try {
			Class.forName(className);
			Connection con=DriverManager.getConnection(conString, connInfo);

//			System.out.println("\nOk!!!");
		
			String sql="SELECT * "
					+ "FROM vdf31701 as f3 "
					+ "WHERE (f3.R303=" + ud + ") AND (f3.L998 <> 3)";
//					+ "ORDER BY f3.R305";
			Statement stm=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stm.executeQuery(sql);
			
			int count = rs.getMetaData().getColumnCount();
			form3 = new HashMap<String,String>(1);
			
			if (rs.next()) {
				rs.last();
				for(int i = 1; i<=count; i++) {
					String key = rs.getMetaData().getColumnName(i).substring(1);
					String value = rs.getString(i);ResultSet rs
					if ((rs.getString(i)!=null)&&(!rs.getString(i).equals(""))&&(isDigit(key))) {
						form3.put(key, value);
					}
				}
			}
//			form3.forEach( (k,v)-> System.out.print(k + ": " + v +" |") );
			con.close();
//			System.out.println("\nclose!");
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return form3;
	}
*/
	
	public List<Map<String,String>> getListF3(Map<String,List<HashMap<String,String>>> vdf301){
		
		List<Map<String,String>> form3 = null;
		
//		long startTime2 = System.currentTimeMillis();
//		Map<String,List<HashMap<String,String>>> vdf301 = getVDF301();
//		long timeSpent2 = System.currentTimeMillis() - startTime2;
//		System.out.println("Time f3: "+ timeSpent2);
		
		String sql="SELECT f3.R303, count(*) "
				+ "FROM vdf3"+ period +"01 as f3 "
						+ "WHERE (f3.L998 <> 3) "
						+ "GROUP BY f3.R303 HAVING count(*)>0";
		
		long startTime = System.currentTimeMillis();
		ResultSet rs = getResultSet(sql);
		try {
			form3 = new ArrayList<Map<String,String>>();
//			int i = 0;
			while (rs.next()) {
//				if ((++i)>100) break;
				ud = rs.getString(1);
				int index = vdf301.get(ud).size();
				form3.add(vdf301.get(ud).get(index-1));
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		long timeSpent = System.currentTimeMillis() - startTime;
		System.out.println("Time f3: "+ timeSpent);
		return form3;
	}
	
	/*public List<Map<String,String>> getListF3(){
		
		List<Map<String,String>> form3 = null;

		String sql="SELECT f3.R303, count(*) "
				+ "FROM vdf3"+ period +"01 as f3 "
						+ "WHERE (f3.L998 <> 3) "
						+ "GROUP BY f3.R303 HAVING count(*)>0";

		long startTime = System.currentTimeMillis();
		ResultSet rs = getResultSet(sql);
		try {
			form3 = new ArrayList<Map<String,String>>();
			int i = 0;
			while (rs.next()) {
				if ((++i)>100) break;
				ud = rs.getString(1);
				form3.add(getF3());
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		long timeSpent = System.currentTimeMillis() - startTime;
		System.out.println("Time f3: "+ timeSpent);
		return form3;
	}*/
	
	public Map<String,List<HashMap<String,String>>> getVDF301(){
		vdf302 = getVDF302();
		Map<String,List<HashMap<String,String>>> map = new HashMap<String,List<HashMap<String,String>>>();

		Connection con2;
		Properties connInfo = new Properties();
		String className = null;
		String conString = null;
		ResultSet rs = null;
		
		String sql="SELECT * "
				+ "FROM vdf3"+ period +"01 as f3 "
				+ "WHERE (f3.L998 <> 3)";
		
		connInfo.put("charSet", "Cp866");
		connInfo.put("CODEPAGEID", "66");
		className = "com.hxtt.sql.dbf.DBFDriver";
		conString = "jdbc:dbf:///"+urlDBF;
		try {
			Class.forName(className);
			con2=DriverManager.getConnection(conString, connInfo);
			
			Statement stm=con2.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);

			rs = stm.executeQuery(sql);
			
			while (rs.next()) {
				String key = rs.getString("R303"); // номер УД в качестве ключа для основного массива
				
				HashMap<String,String> mapUD = new HashMap<String, String>(); // карта с реквизитами УД
				int count = rs.getMetaData().getColumnCount();
				//собираем реквизиты по столбцам
				for(int i = 1; i<=count; i++) {
					String key2 = rs.getMetaData().getColumnName(i).substring(1); //key2 - название реквизита
					String value = rs.getString(i);
					
					if ((rs.getString(i)!=null)&&(!rs.getString(i).equals(""))) {
						if (key2.equals("ECNO")) {
							value = getR317(value);
							if (!value.equals("")) {
								key2 = "317";
							}
						}
						if (Strings.isDigit(key2))
							mapUD.put(key2, value);
					}
				}
				mapUD.put("302", "1");
				if (!map.containsKey(key)) {
					List<HashMap<String,String>> val = new ArrayList<HashMap<String,String>>(1); // содержит формы 3 для каждого УД
					val.add(mapUD);
					map.put(key, val);
				} else {
					map.get(key).add(mapUD);
				}
			}
			con2.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return map;
	}
	
	public List<VDF302> getVDF302(){
		List<VDF302> ls = new ArrayList<VDF302>();

		Connection con2;
		Properties connInfo = new Properties();
		String className = null;
		String conString = null;
		ResultSet rs = null;
		
		String sql="SELECT * "
				+ "FROM VDF3"+ period +"02 as f3 "
				+ "WHERE (f3.R317 <> '17')";
		
		connInfo.put("charSet", "Cp866");
		connInfo.put("CODEPAGEID", "66");
			
		className = "com.hxtt.sql.dbf.DBFDriver";
		conString = "jdbc:dbf:///"+urlDBF;
		try {
			Class.forName(className);
			con2=DriverManager.getConnection(conString, connInfo);
			
			Statement stm=con2.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);

			rs = stm.executeQuery(sql);
			
			while (rs.next()) {
				String A999 = rs.getString(1);
				String val = rs.getString(2);
				ls.add(new VDF302(A999,val));
			}
			
			con2.close();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return ls;
	}
	
	public class VDF302{
		
		private String A999;
		private String val;
		
		public VDF302(String A999, String val){
			this.A999 = A999;
			this.val = val;
		}
		
	}
	
	
	
}


