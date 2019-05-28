package work1;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



@WebServlet("/uvi.do")
public class ProcessMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session =request.getSession();
		Collection<String> errorMessage = new ArrayList<String>();
		request.setAttribute("ErrorMsg", errorMessage);

		request.setCharacterEncoding("UTF-8");

		String de = request.getParameter("action");

		String Id = request.getParameter("Id");

		int ids = 0;

		if (Id == null || Id.trim().length() == 0) {
			
				errorMessage.add("帳號欄必須輸入");
		}
				// 讀取使用者所輸入，由瀏覽器送來的 pswd 欄位內的資料，注意大小寫
				String County = request.getParameter("County");
				// 檢查使用者所輸入的資料
				if (County == null || County.trim().length() == 0) {
					errorMessage.add("請輸入縣市欄位");
				}
				// 讀取使用者所輸入，由瀏覽器送來的 mName 欄位內的資料
				String PublishAgency = request.getParameter("PublishAgency");
				// 檢查使用者所輸入的資料
				if (PublishAgency == null || PublishAgency.trim().length() == 0) {
					errorMessage.add("發佈機關必須輸入");
				}
				// 讀取使用者所輸入，由瀏覽器送來的 mAddress 欄位內的資料
				String PublishTime = request.getParameter("PublishTime");
				// 檢查使用者所輸入的資料
				java.sql.Date date = null;

				if (PublishTime != null && PublishTime.trim().length() > 0) {
					try {
						date = java.sql.Date.valueOf(PublishTime);
					} catch (IllegalArgumentException e) {
						System.out.println(e.getMessage());
						e.printStackTrace();
						errorMessage.add("日期錯誤");
					}
				}

				String SiteName = request.getParameter("SiteName");
				if (SiteName == null || SiteName.trim().length() == 0) {
					errorMessage.add("測站名稱必須輸入");

				}
//			Timestamp ts = new Timestamp(System.currentTimeMillis());
				String UVI = request.getParameter("UVI");
				double dUVI = -1;
				// 檢核使用者的輸入資料
				if (UVI != null && UVI.trim().length() > 0) {
					try {
						dUVI = Double.parseDouble(UVI.trim());
					} catch (NumberFormatException e) {
						System.out.println(e.getMessage());
						e.printStackTrace();
						errorMessage.add("紫外線必須為數值");
					}
				}

				// 讀取使用者所輸入，由瀏覽器送來的 mPhone 欄位內的資料
				System.out.println("***1***" + errorMessage);
				// 如果有錯誤，呼叫view元件，送回錯誤訊息

				// MemberBean 扮演封裝輸入資料的角色
				UVIBean uvibean = new UVIBean(Id, County, PublishAgency, PublishTime, SiteName, UVI);
				try {
					UviService uviservice = new UviService();
					if (de.equals("新增")) {
						if (!errorMessage.isEmpty()) {

							RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
							rd.forward(request, response);
							return;
						}
						uviservice.insertuvi(uvibean);
//						RequestDispatcher rd = request.getRequestDispatcher("/src/webapp1/InsertuviSuccess.jsp");
//						// 請容器代為呼叫下一棒程式
//						rd.forward(request, response);
//						return;
						
						session.setAttribute("uvibean", uvibean);

						response.sendRedirect(
					             response.encodeRedirectURL("InsertuviSuccess.jsp"));
						return;
					} else if (de.equals("刪除")) {
						uviservice.delete(Id);
						session.setAttribute("uvibean", uvibean);

						response.sendRedirect(
					             response.encodeRedirectURL("InsertuviSuccess2.jsp"));
						return;
					} else if (de.equals("修改")) {
						if (!errorMessage.isEmpty()) {

							RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
							rd.forward(request, response);
							return;
						}
						uviservice.update(uvibean);
						session.setAttribute("uvibean", uvibean);

						response.sendRedirect(
					             response.encodeRedirectURL("InsertuviSuccess3.jsp"));
						return;
					} else if (de.equals("查詢")) {
						uvibean = uviservice.select(Id);
						
						session.setAttribute("uvibean", uvibean);

						response.sendRedirect(
					             response.encodeRedirectURL("InsertuviSuccess4.jsp"));
						return;

					}

					request.setAttribute("UVIBean", uvibean);
					// 依照執行的結果挑選適當的view元件，送回相關訊息
					// 產生 RequestDispatcher 物件 rd
					
				
				} catch (SQLException e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
					if (e.getMessage().indexOf("重複的索引鍵") != -1 || e.getMessage().indexOf("Duplicate entry") != -1) {
						errorMessage.add("地點重複，請重新輸入地點");
					} else {
						errorMessage.add("資料庫存取錯誤:" + e.getMessage());
					}
					RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
					rd.forward(request, response);
					return;
				}
			
		}
	}
