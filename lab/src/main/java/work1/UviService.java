package work1;

import java.sql.SQLException;
import java.util.List;

public class UviService {
	
	UVIDao dao = null;
	
	public UviService() {
		dao = new UVIDao();
	}	

	public UVIBean select(String Id) {
		return dao.findByUVI(Id);
	}
	
	public List<UVIBean> select() throws SQLException {
		return dao.getAll();
	}

	public UVIBean insertuvi(UVIBean bean) throws SQLException {
		return dao.insert(bean);
	}

	public int delete(String Id) throws SQLException { 
		return dao.delete(Id);
	}
	public int update(UVIBean bean) throws SQLException {
		return dao.update(bean); 
		
	}

	
}