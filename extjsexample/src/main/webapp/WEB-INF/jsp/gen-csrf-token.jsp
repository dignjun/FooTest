<%@page import="java.util.regex.Matcher"%>
<%@page import="java.util.regex.Pattern"%>
<%@page import="java.util.Random"%>
<%@page language="java" pageEncoding="UTF-8"%>
<% 
if ("true".equals(System.getProperty("enable_csrf_token"))){
	String csrftoken = String.valueOf(new Random().nextInt(1000));
	Pattern p = Pattern.compile("/([a-z]{1,5})mgr/");
	Matcher m = p.matcher(request.getRequestURI());
	if (m.find()){
		request.getSession().setAttribute("csrf_token_"+m.group(1), csrftoken);
	}
%><script type="text/javascript">
var sys_csrftoken = '<%=csrftoken%>'
</script>
<%
}
%>
