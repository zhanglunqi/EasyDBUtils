<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
  <head>
  </head>
  
  <body>
		<form action="${pageContext.request.contextPath }/servlet/textLogin"  method="post">
				<input type="text" name="username" />
				<input type="password" name="password" />
				<input type="submit" name="submit" />
		</form>
  </body>
</html>
