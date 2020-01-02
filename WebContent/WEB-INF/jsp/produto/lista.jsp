<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<script type="text/javascript" src="<c:url value="/js/jquery-1.6.1.min.js" />"></script>
		<script type="text/javascript">
			function remove(id){
				$.get('remove?produto.id=' + id, function(){
					alert('produto removido com sucesso');
					$('#produto-'+id).remove();
				});
			}
		</script>
		<title>Lista de Produtos</title>
	</head>
	<body>
		
		${mensagem}
		
		<table>
			<c:forEach var="produto" items="${produtoList}">
				<tr id="produto-${produto.id}">
					<td>${produto.nome}</td>
					<td>${produto.descricao}</td>
					<td>${produto.preco}</td>
					<td><a href="" onclick="remove(${produto.id}); return false;">Remove</a></td>
				</tr>
			</c:forEach>
		</table>
	
	</body>
</html>