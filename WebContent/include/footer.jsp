<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%
	Date d = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
	String date = sdf.format(d);
%>
    
    <footer>
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <p>Copyright &copy; By Park 2018.XX.XX ~ <%=date %></p>
                </div>
            </div>
        </div>
    </footer>
	
	
	
</body>
</html>