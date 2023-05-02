
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet({ "/register", "/read", "/update", "/updaterecord", "/delete" })
@MultipartConfig(fileSizeThreshold = 1000000000, maxFileSize = 10000000000l, maxRequestSize = 10000000000l)
public class RegisterServlet extends HttpServlet {
    Operation opr = new Operation();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getServletPath().equals("/register")) {

            String n = req.getParameter("name");
            String e = req.getParameter("email");
            String p = req.getParameter("pass");
            String m = req.getParameter("mobile");
            String fname = req.getPart("file").getSubmittedFileName();
            for (Part part : req.getParts()) {
                part.write("C:\\Users\\sethi\\eclipse-workspace\\Class_12_Project\\src\\main\\webapp\\upload\\" + fname);
            }

            try {
                int status = opr.Insert(n, e, p, m, fname);
                System.out.println(status == 0 ? "Inserted" : "Check Connection");
            } catch (Exception err) {
                System.out.println(err.getMessage());
            }
        }

        if (req.getServletPath().equals("/updaterecord")) {
            String n = req.getParameter("name");
            String e = req.getParameter("email");
            String p = req.getParameter("pass");
            String m = req.getParameter("mobile");
            String fname = req.getPart("file").getSubmittedFileName();
            System.out.println(fname);
            if (fname != null) {
                for (Part part : req.getParts()) {
                    part.write("C:\\Users\\sethi\\eclipse-workspace\\Class_12_Project\\src\\main\\webapp\\upload\\" + fname);
                }

                try {
                    int status = opr.Update(n, e, p, m, fname);
                    System.out.println(status == 0 ? "Updated" : "Check Connection");
                } catch (Exception err) {
                    System.out.println(err.getMessage());
                }

            } else {
                try {
                    int status = opr.Update(n, e, p, m);
                    System.out.println(status == 0 ? "Updated" : "Check Connection");
                } catch (Exception err) {
                    System.out.println(err.getMessage());
                }
            }

        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    	if (req.getServletPath().equals("/delete")) {
    		String email =req.getParameter("email");
    		
    		ArrayList <HashMap> data = opr.readbyemail(email);
    		System.out.println(data.get(0).get("image"));
    		
    		File f = new File ("C:\\Users\\sethi\\eclipse-workspace\\Class_12_Project\\src\\main\\webapp\\upload\\"+data.get(0).get("image"));
    				f.delete();
    				opr.deletebyemail(email);
    	}
    	
    	
        if (req.getServletPath().equals("/read")) {
            ArrayList<HashMap> record = opr.read();
            req.setAttribute("record", record);

            RequestDispatcher rd = req.getRequestDispatcher("show.jsp");
            rd.forward(req, resp);
        }

        if (req.getServletPath().equals("/update")) {
            String email = req.getParameter("email");
            ArrayList<HashMap> record = opr.readbyemail(email);
            req.setAttribute("record", record);
            System.out.println(record);
            RequestDispatcher rd = req.getRequestDispatcher("update.jsp");
            rd.forward(req, resp);
        }

    }
}