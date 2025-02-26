package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Accounts;
import model.Categories;
import model.Products;

public class DAO extends DBContext {

    public Connection con = null;
    public PreparedStatement ps = null;
    public ResultSet rs = null;
    public String xSql = null;

    public DAO() {
        con = connection;
    }

    public void finalize() {
        try {
            if (con != null) {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int countProducts() {
        int count = 0;
        String query = "select * from products";
        try {
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                count++;
            }
        } catch (Exception e) {
        }
        return count;
    }

    public List<Products> getAllProducts() {
        List<Products> list = new ArrayList<>();
        String query = "select * from products";
        try {
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Products(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Products> getTopPricedProducts() {
        List<Products> list = new ArrayList<>();
        String query = "SELECT id, name, price, image, title, description FROM products ORDER BY price DESC LIMIT 4";
        try {
            ps = con.prepareStatement(query);

            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Products(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6)));
            }
        } catch (Exception e) {
        }
        return list;

    }

    public List<Products> getAllProductsByCategories(String cid) {
        List<Products> list = new ArrayList<>();
        String query = "select * from products where cateID=?";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, cid);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Products(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Products> searchByName(String txtSearch) {
        List<Products> list = new ArrayList<>();
        String query = "select * from products where name like ?";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, "%" + txtSearch + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Products(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public Products getAllProductsByIDCategories(String id) {
        List<Products> list = new ArrayList<>();
        String query = "select * from products where id = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                return new Products(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public List<Categories> getAllCategories() {
        List<Categories> list = new ArrayList<>();
        String query = "select * from Categories";
        try {
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Categories(rs.getInt(1),
                        rs.getString(2)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public Products getLast() {
        String query = "select top 1 * from products\n"
                + "order by id desc";
        try {
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Products(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public Accounts login(String user, String pass) {
        String query = "select * from accounts where [user] = ? and pass =?";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Accounts(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getInt(6));
            }
        } catch (Exception e) {

        }
        return null;
    }

    public Accounts checkAccountExist(String user) {
        String query = "select * from accounts where [user] = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, user);

            rs = ps.executeQuery();
            while (rs.next()) {
                return new Accounts(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getInt(6));
            }
        } catch (Exception e) {

        }
        return null;
    }

    public void singup(String user, String pass) {
        String query = "insert into accounts values(?,?,0,0)";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            ps.executeUpdate();
        } catch (Exception e) {

        }
    }

    public void deleteProduct(String pid) {
        String query = "delete from products\n"
                + "where id = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, pid);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void insertProduct(String name, String image, String price,
            String title, String description, String category, int sid) {
        String query = "INSERT [dbo].[products] \n"
                + "([name], [image], [price], [title], [description], [cateID], [sell_ID])\n"
                + "VALUES(?,?,?,?,?,?,?)";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, image);
            ps.setString(3, price);
            ps.setString(4, title);
            ps.setString(5, description);
            ps.setString(6, category);
            ps.setInt(7, sid);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void editProduct(String name, String image, String price, String title, String description, String category, String pid) {
        String query = "update products  set [name] = ?, [image] = ?, price = ?, title = ?, [description] = ?, cateID = ? where id = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, image);
            ps.setString(3, price);
            ps.setString(4, title);
            ps.setString(5, description);
            ps.setString(6, category);
            ps.setString(7, pid);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public List<Products> getProductBySellID(int id) {
        List<Products> list = new ArrayList<>();
        String query = "select * from products where sell_ID=?";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Products(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5), rs.getString(6)));
            }

        } catch (Exception e) {
        }
        return list;
    }


    public List<Accounts> getAllAccounts() {
        List<Accounts> list = new ArrayList<>();
        String query = "select * from accounts";
        try {
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Accounts(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getInt(6)));
            }
        } catch (Exception E) {
        }
        return list;
    }

    public void insertAccount(String user, String pass,
            int isSell, int isAdmin, String email) {
        String sql = "INSERT INTO [dbo].[Accounts]\n"
                + "           ([user], "
                + "             [pass], "
                + "             [isSell], "
                + "             [isAdmin],"
                + "             [email])"
                + "     VALUES\n"
                + "           (?,?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, user);
            st.setString(2, pass);
            st.setInt(3, isSell);
            st.setInt(4, isAdmin);
            st.setString(5, email);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void deleteAccounts(String username) {
        String sql = "DELETE FROM Accounts WHERE [user] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

    public List<Accounts> getAccountsBySearchName(String txtSearch) {
        List<Accounts> list = new ArrayList<>();
        String sql = "SELECT * FROM [dbo].[Accounts] where [user] LIKE ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            //set ?
            st.setString(1, "%" + txtSearch + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Accounts(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4), 
                        rs.getInt(5), 
                        rs.getInt(6)));
            }
        } catch (Exception e) {
        }
        return list;
    }
}
