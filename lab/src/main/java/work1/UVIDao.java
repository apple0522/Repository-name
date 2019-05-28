package work1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class UVIDao {
//	private static final String INSERT_STMT = "INSERT INTO AQI VALUES (?, ?, ?, ?, ?, ?)";
//	private static final String UPDATE_STMT = "UPDATE AQI SET SiteName=?, Country=?, AQI=?, Status=?, SO2=?, SO2_AVG=? WHERE SiteName=?";
//	private static final String DELETE_STMT = "DELETE FROM AQI WHERE SiteName=?";
//	private static final String GET_ONE_STMT = "SELECT SiteName, Country, AQI, Status, SO2, SO2_AVG FROM AQI WHERE SiteName=?";
//	private static final String GET_ALL_STMT = "SELECT SiteName, Country, AQI, Status, SO2, SO2_AVG FROM AQI ORDER BY SiteName";

	DataSource ds = null;

	public UVIDao() {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/MemberDB");
		
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "insert into UVI (Id,County,PublishAgency,PublishTime,UVI)values(?,?,?,?,?,?)";

	public UVIBean insert(UVIBean uvi) throws SQLException {
		UVIBean result = null;
		try (Connection conn = ds.getConnection(); 
			PreparedStatement pstmt = conn.prepareStatement(INSERT_STMT);) {
			pstmt.setString(1, uvi.getId());
			pstmt.setString(2, uvi.getCounty());
			pstmt.setString(3, uvi.getPublishAgency());
			pstmt.setString(4, uvi.getPublishTime());
			pstmt.setString(5, uvi.getSiteName());
			pstmt.setString(6, uvi.getUvi());

			int i = pstmt.executeUpdate();
			if (i == 1) {
				result = this.findByUVI(uvi.getId());
			}
		}
		return result;
	}

	private static final String UPDATE_STMT = "Update UVI set County=?, PublishAgency=?, PublishTime=? ,SiteName=?,UVI=? where Id=?";

	public int update(UVIBean uvi) throws SQLException {
		int result = 0;
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(UPDATE_STMT);) {
			pstmt.setString(6, uvi.getId());
			pstmt.setString(1, uvi.getCounty());
			pstmt.setString(2, uvi.getPublishAgency());
			pstmt.setString(3, uvi.getPublishTime());
			pstmt.setString(4, uvi.getSiteName());
			pstmt.setString(5, uvi.getUvi());
			System.out.println(pstmt);
			result = pstmt.executeUpdate();
			System.out.println("111111");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	private static final String DELETE_STMT = "DELETE FROM UVI WHERE Id=?";

	public int delete(String Id) throws SQLException {
		int result = 0;
		try (Connection conn = ds.getConnection(); 
		PreparedStatement pstmt = conn.prepareStatement(DELETE_STMT);) {
			pstmt.setString(1, Id);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	private static final String GET_ONE_STMT = "SELECT * from UVI where Id=?";

	public UVIBean findByUVI(String Id) {
		UVIBean uvi = null;
		try (Connection conn = ds.getConnection();
			
				
				PreparedStatement pstmt = conn.prepareStatement(GET_ONE_STMT);) {
			pstmt.setString(1, Id);
			try (ResultSet rs = pstmt.executeQuery();) {
				if (rs.next()) {
					uvi = new UVIBean();
					uvi.setId(rs.getString("Id"));
					uvi.setCounty(rs.getString("County"));
					uvi.setPublishAgency(rs.getString("PublishAgency"));
					uvi.setPublishTime(rs.getString("PublishTime"));
					uvi.setSiteName(rs.getString("SiteName"));
					uvi.setUvi(rs.getString("UVI"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return uvi;
	}

	private static final String GET_ALL_STMT = "select * from UVI";

	public List<UVIBean> getAll() throws SQLException {
		UVIBean uvi = null;
		List<UVIBean> uvis = new Vector<UVIBean>();
		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(GET_ALL_STMT);
				ResultSet rs = pstmt.executeQuery();) {
			while (rs.next()) {
				uvi = new UVIBean();
				uvi.setId(rs.getString("id"));
				uvi.setCounty(rs.getString("County"));
				uvi.setPublishAgency(rs.getString("PublishAgency"));
				uvi.setPublishTime(rs.getString("PublishTime"));
				uvi.setSiteName(rs.getString("SiteName"));
				uvi.setUvi(rs.getString("UVI"));
				uvis.add(uvi);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return uvis;
	}

} // end of class DAO