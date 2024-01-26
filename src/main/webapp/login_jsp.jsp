<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>

<%@ page import="java.sql.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<%
    // Oracle 데이터베이스 연결 정보
    String url = "jdbc:oracle:thin:@localhost:1521:your_sid";
    String username = "your_username";
    String password = "your_password";

    // 사용자로부터 전송된 데이터 가져오기
    String enteredUsername = request.getParameter("username");
    String enteredPassword = request.getParameter("password");

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    try {
        // JDBC 드라이버 로드
        Class.forName("oracle.jdbc.driver.OracleDriver");

        // 데이터베이스 연결
        connection = DriverManager.getConnection(url, username, password);

        // SQL 쿼리 작성
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, enteredUsername);
        preparedStatement.setString(2, enteredPassword);

        // 쿼리 실행
        int rowsAffected = preparedStatement.executeUpdate();

        if (rowsAffected > 0) {
            out.println("사용자 정보가 성공적으로 DB에 저장되었습니다.");
        } else {
            out.println("사용자 정보 저장에 실패했습니다.");
        }
    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
    } finally {
        // 리소스 해제
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }
%>
