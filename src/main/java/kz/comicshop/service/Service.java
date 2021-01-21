package kz.comicshop.service;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Service {
    void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,  ParseException, SQLException;
}

