package controller;

import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Accounts;

public class DeleteAccountServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DeleteAccountServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DeleteAccountServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        HttpSession session = request.getSession();
        Accounts a = (Accounts) session.getAttribute("account");
        DAO dao = new DAO();
        List<Accounts> list = dao.getAllAccounts();
        request.setAttribute("accList", list);
        String msg;
        if (username != null) {
            dao.deleteAccounts(username);
            msg = "Delete username " + username + " successfully!";
        } else {
            msg = "Delete failed!";
        }

        // Đặt thông báo vào session để hiển thị trên trang đích
        request.setAttribute("mess", msg);

        // Kiểm tra nếu tài khoản bị xóa là tài khoản hiện tại
        if (a != null && username.equals(a.getUser())) {
            session.removeAttribute("account");  // Xóa thông tin người dùng khỏi session
            request.getRequestDispatcher("Home.jsp").forward(request, response);
        } else {
//            response.sendRedirect("delete_account_ajax.jsp");
            request.getRequestDispatcher("ManagerAccount.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
