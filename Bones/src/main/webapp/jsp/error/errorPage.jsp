<!DOCTYPE html>
<html lang=${language}>
<head>
    <title>Error Page</title>
    <link rel="stylesheet" href="/css/error.css">
</head>
<body>
Request from ${pageContext.errorData.requestURI} is failed
<br/>
Servlet name or type: ${pageContext.errorData.servletName}
<br/>
Status code: ${pageContext.errorData.statusCode}
<br/>
Exception: ${pageContext.errorData.throwable}
<br>
<a href="/index.jsp">Back</a>
</body>
</html>