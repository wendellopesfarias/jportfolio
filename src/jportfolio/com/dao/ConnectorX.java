package jportfolio.com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ConnectorX {
	
	private static final String SQL = "SELECT * FROM asset;";
	
	public static void main(String[] argv) throws ClassNotFoundException {
		
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		
		try {
			con = ConnectorX.getConnectionCrawler();
			pst = con.prepareStatement(SQL);
			rs = pst.executeQuery();

			int numberCols = rs.getMetaData().getColumnCount();
				
			for(int k = 0; k < numberCols;k++) {
				System.out.println(rs.getMetaData().getColumnName(k+1));
			}
			
			if(rs.next()) {
				do {
					for(int k = 0; k < numberCols;k++) {
						System.out.println(rs.getString(rs.getMetaData().getColumnName(k+1)));
					}
				}while(rs.next());
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	  }
	
	public static Connection getConnectionCrawler() throws SQLException {

		String driver = "org.postgresql.Driver";
        String user   = "postgres";
        String senha  = "654321";
        String url    = "jdbc:postgresql://localhost:5433/db_finance";
		
		Connection conn = null;
		
		  try
	        {
	            Class.forName(driver);
	            conn = (Connection) DriverManager.getConnection(url, user, senha);
	        }
	        catch (ClassNotFoundException ex)
	        {
	            System.err.print(ex.getMessage());
	        } 
	        catch (SQLException e)
	        {
	            System.err.print(e.getMessage());
	        }

		return conn;

	}
	

}
