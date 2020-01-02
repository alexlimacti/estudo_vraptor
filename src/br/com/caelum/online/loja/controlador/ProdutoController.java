package br.com.caelum.online.loja.controlador;

import java.util.List;

import br.com.caelum.online.loja.dao.ProdutoDao;
import br.com.caelum.online.loja.dominio.Produto;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;

@Resource
public class ProdutoController {
	
	private final ProdutoDao produtos;
	private final Result result;

	public ProdutoController(Result result) {
		this.result = result;
		this.produtos = new ProdutoDao();
	}
	
	public List<Produto> lista() {
		return produtos.pegaTodos();
	}
	
	public void formulario() {
		
	}
	
	@Post
	public void adiciona(Produto produto) {
		produtos.salva(produto);
		
		/*
		 * Adiciona uma mensagem no resultado da requisição
		 * Na página JSP adicionar ${mensagem}, conforme primeiro parâmentro do include
		 */
		result.include("mensagem","Novo produto adicionado com sucesso!");
		/*
		 * Redirecionamento no lado do cliente
		 */
		result.redirectTo(ProdutoController.class).lista();
	}
	
	@Path("/produto/{id}")
	public Produto exibe(Long id) {
		return produtos.pegaPorId(id);
	}
	
	@Path("/produto/{id}/xml")
	public void exibeComoXml(Long id) {
		Produto produto = produtos.pegaPorId(id);
		/*
		 * transforma o retorno da requisição em XML e retorna ao usuário
		 */
		result.use(Results.xml()).from(produto).serialize();
	}
	
	@Path("/produto/{id}/json")
	public void exibeComoJson(Long id) {
		Produto produto = produtos.pegaPorId(id);
		/*
		 * transforma o retorno da requisição em Json e retorna ao usuário
		 */
		result.use(Results.json()).from(produto).serialize();
	}

}
