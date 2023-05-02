import java.sql.*; //to import all library from sql package
import java.util.ArrayList;
import java.util.HashMap;

public class Operation {
    // interface
    Connection conn = null;
    // Query
    Statement stm = null;
    // to perform long and complicated query
    PreparedStatement pstm = null;;

    public Operation() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // library register
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Records", "root", "Vanshi@123");
            stm = conn.createStatement(); // Create Connection to perform Query
            stm.execute("create table if not exists data(name text,email text,password text,mobile text,image text)");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int Insert(String n, String e, String p, String m, String fname) throws Exception {
        String q = "Insert into data values(?,?,?,?,?)";
        pstm = conn.prepareStatement(q);
        pstm.setString(1, n);
        pstm.setString(2, e);
        pstm.setString(3, p);
        pstm.setString(4, m);
        pstm.setString(5, fname);
        return pstm.executeUpdate();
    }

    public ArrayList<HashMap> read() {
        ArrayList<HashMap> record = new ArrayList<HashMap>();
        try {
            ResultSet data = stm.executeQuery("select * from data");

            while (data.next()) {

                HashMap map = new HashMap();
                map.put("name", data.getString(1));
                map.put("email", data.getString(2));
                map.put("password", data.getString(3));
                map.put("mobile", data.getString(4));
                map.put("image", data.getString(5));
                record.add(map);
            }
            return record;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<HashMap> readbyemail(String email) {
        ArrayList<HashMap> record = new ArrayList<HashMap>();
        try {
            pstm = conn.prepareStatement("select * from data where email = ?");
            pstm.setString(1, email);

            ResultSet data = pstm.executeQuery();

            while (data.next()) {

                HashMap map = new HashMap();
                map.put("name", data.getString(1));
                map.put("email", data.getString(2));
                map.put("password", data.getString(3));
                map.put("mobile", data.getString(4));
                map.put("image", data.getString(5));
                record.add(map);
            }
            return record;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public int Update(String n, String e, String p, String m, String fname) {

        try {
            String q = "update data set name=?,pass=?,mobile=?,image=? where email=?";
            pstm = conn.prepareStatement(q);
            pstm.setString(1, n);
            pstm.setString(2, p);
            pstm.setString(3, m);
            pstm.setString(4, fname);
            pstm.setString(5, e);
            return pstm.executeUpdate();
        } catch (Exception err) {
            // TODO: handle exception
            return 1;
        }
    }

    public int Update(String n, String e, String p, String m) {

        try {
            String q = "update data set name=?,password=?,mobile=? where email=?";
            pstm = conn.prepareStatement(q);
            pstm.setString(1, n);
            pstm.setString(2, p);
            pstm.setString(3, m);
            pstm.setString(4, e);
            return pstm.executeUpdate();
        } catch (Exception err) {
            // TODO: handle exception
            return 1;
        }
    }

	public void deletebyemail(String email) {
		try {
	     pstm = conn.prepareStatement("delete from data where email =?");
	     pstm.setString(1, email);
	     pstm.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}