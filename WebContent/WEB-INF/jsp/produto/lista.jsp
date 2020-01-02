<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Lista de Produtos</title>
	</head>
	<body>
		
		${mensagem}
		
		<table>
			<c:forEach var="produto" items="${produtoList}">
				<tr>
					<td>${produto.nome}</td>
					<td>${produto.descricao}</td>
					<td>${produto.preco}</td>
				</tr>
			</c:forEach>
		</table>
	
	</body>
</html>