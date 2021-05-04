<%@ page import="com.tech.model.User" %>
<%@ page import="java.util.ArrayList" %>
<html>
<h1>Hi There</h1>
<%! String name;%>
<%! ArrayList<User> alluser;%>
<%
    alluser = (ArrayList<User>) request.getAttribute("alluser");
    //out.println("<h2>"+user.getUsername()+"</h2>");
    for (int i = 0; i < alluser.size(); i++) {
        name = alluser.get(i).getUsername();
        out.println("<h2>" + alluser.get(i).getUserid()
                + ":" + alluser.get(i).getUsername()
                + ":" + alluser.get(i).getPassword() + "</h2>");
    }
%>
</body>
</html>
