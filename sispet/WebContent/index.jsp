<%
	response.setStatus(301);

	response.setHeader( "Location", "index.jsf" );

	response.setHeader( "Connection", "close" );
%>