package br.com.caelum.online.loja.controlador;

import java.util.List;

import br.com.caelum.online.loja.dominio.Produto;
import br.com.caelum.online.loja.repositorio.RepositorioDeProdutos;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.com.caelum.vraptor.validator.Validations;
import br.com.caelum.vraptor.view.Results;

@Resource
public class ProdutoController {
	
	private final RepositorioDeProdutos produtos;
	private final Result result;
	private final Validator validator;

	public ProdutoController(Result result, RepositorioDeProdutos produtos, Validator validator) {
		this.result = result;
		this.produtos = produtos;
		this.validator = validator;
	}
	
	public List<Produto> lista() {
		return produtos.pegaTodos();
	}
	
	public void formulario() {
		
	}
	
	@Post
	public void adiciona(final Produto produto) {
		
		/*
		 * Trata a validacao dos erros sem insercao de varios if's
		 * Foi criado um arquivo messages.properties onde as mensagens são validadas
		 */
		validator.checking(new Validations() {
			{
				that(produto.getPreco()> 0.1, "erro", "produto.preco.invalido");
			}
		});
		
		/*
		if(produto.getPreco() < 0.1) {
			validator.add(new ValidationMessage("O preço deve ser maior do que R$ 0.1", "preco"));
		}
		*/
		validator.onErrorUsePageOf(ProdutoController.class).formulario();
		
		this.produtos.salva(produto);
		
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
	
	public void remove(Produto produto) {
		produtos.remove(produto);
		result.nothing();
	}

}
