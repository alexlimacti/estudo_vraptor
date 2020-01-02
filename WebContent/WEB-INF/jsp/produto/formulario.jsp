<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Novo Produto</title>
	</head>
	<body>
	
		<c:forEach items="${errors}" var="error">
			${error.category} - ${error.message}
		</c:forEach>
		
		<table>
			<form action="<c:url value="/produto/adiciona"/>" method="post">
				<tr>
					<td>Nome</td>
					<td><input name="produto.nome" value="${produto.nome}"/></td>
				</tr>
				<tr>
					<td>Descricao</td>
					<td><input name="produto.descricao" value="${produto.descricao}"/></td>
				</tr>
				<tr>
					<td>Preco</td>
					<td><input name="produto.preco" value="${produto.preco}"/></td>
				</tr>
				<tr>
					<td><input type="submit" value="Adicionar" /></td>
				</tr>
			</form>
		</table>
	</body>
</html>