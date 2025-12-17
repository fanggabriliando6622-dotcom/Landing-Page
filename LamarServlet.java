package com.ids.career;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

@WebServlet("/lamar")
@MultipartConfig(
    maxFileSize = 5 * 1024 * 1024
)
public class LamarServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String nama = req.getParameter("nama");
        String email = req.getParameter("email");
        String posisi = req.getParameter("posisi");

        Part cv = req.getPart("cv");
        String fileName = cv.getSubmittedFileName();

        String uploadPath = getServletContext().getRealPath("") + "upload";
        File dir = new File(uploadPath);
        if (!dir.exists()) dir.mkdir();

        cv.write(uploadPath + File.separator + fileName);

        System.out.println("Lamaran masuk: " + nama + " - " + posisi);

        res.sendRedirect("success.html");
    }
}
