package br.pucgoias.agenda.controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.component.html.HtmlInputHidden;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.pucgoias.agenda.entidade.Pessoa;
import br.pucgoias.agenda.entidade.Telefone;
import br.pucgoias.agenda.negocio.PessoaService;
import br.pucgoias.util.AgendaException;

/**
 * Classe que controla as requisicoes do cliente web
 * @author Gilcimar
 *
 */
@ManagedBean(name="pessoaController")
@RequestScoped
@Controller
public class PessoaController {
	
	@Autowired
	private PessoaBean pessoaBean;
	@Autowired
	private List<PessoaBean> listaPessoaBean;
	@Autowired
	private PessoaService pessoaService;
	@Autowired
	private TelefoneBean telefoneBean;

	/**
	 * Construtor da classe de pessoa
	 */
	@SuppressWarnings("unchecked")
	public PessoaController(){
		pessoaBean = new PessoaBean();
		if(this.getFacesContext().getExternalContext().getSessionMap().get("telefones")!=null){
			pessoaBean.setListaTelefone((List<TelefoneBean>)getSession("telefones"));
		}
		else{
			pessoaBean.setListaTelefone(new ArrayList<TelefoneBean>());
		}
		telefoneBean = new TelefoneBean();
	}
	
	/**
	 * Inclui uma pessoa na base de dados
	 * @return
	 */
	public String incluir() {
		try{

			Pessoa pessoa = new Pessoa();

			//preenche os dados da tela no objeto persistente
			pessoa.setIdPessoa(pessoaBean.getIdPessoa());
			pessoa.setDsNome(pessoaBean.getDsNome());
			pessoa.setVlIdade(pessoaBean.getVlIdade().toString());

			//preeche a lista de telefones da tela na lista de telefones persistente
			pessoa.setListaTelefone(new ArrayList<Telefone>());
			for (TelefoneBean telefoneBean : pessoaBean.getListaTelefone()) {
				Telefone telefone = new Telefone();
				telefone.setClTipo(telefoneBean.getClTipo());
				telefone.setDsNumero(telefoneBean.getDsNumero());
				telefone.setPessoa(pessoa);
				pessoa.getListaTelefone().add(telefone);
			}

			getPessoaService().incluir(pessoa);
			
			return "sucesso";
		}
		catch (Exception e) {
			String msg = "Inclusao nao realizada. Movito: " + ((e instanceof AgendaException ? ((AgendaException)e).getEx().getMessage():""));
			FacesMessage message = new FacesMessage(msg);
			this.getFacesContext().addMessage("formulario", message);
			return "falha";
		}
	}

	/**
	 * Lista as pessoas cadastradas
	 * @return
	 */
	public String listar() {
		try{

			List<Pessoa> listaPessoa = getPessoaService().listar();

			if(listaPessoa==null || listaPessoa.size()==0){
				FacesMessage message = new FacesMessage("Nenhum registro encontrado.");
				this.getFacesContext().addMessage("formulario", message);
			}

			//preeche a lista de pessoas da tela
			listaPessoaBean = new ArrayList<PessoaBean>();
			for (Pessoa pessoa : listaPessoa) {
				PessoaBean pessoaBean = new PessoaBean();
				pessoaBean.setIdPessoa(pessoa.getIdPessoa());
				pessoaBean.setDsNome(pessoa.getDsNome());
				pessoaBean.setVlIdade((pessoa.getVlIdade()!=null && !"".equals(pessoa.getVlIdade())) ? Integer.parseInt(pessoa.getVlIdade()):0);
				listaPessoaBean.add(pessoaBean);
			}

			return "listar";
		}
		catch (Exception e) {
			String msg = "Listagem nao realizada. Movito: " + ((e instanceof AgendaException ? ((AgendaException)e).getEx().getMessage():""));
			FacesMessage message = new FacesMessage(msg);
			this.getFacesContext().addMessage("formulario", message);
			return "falha";
		}
	}

	/**
	 * Consulta uma pessoa cadastrada
	 * @return
	 */
	public String consultar() {
		try{

			String idPessoa = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idPessoa");
			
			Pessoa pessoa = getPessoaService().consultar(Integer.parseInt(idPessoa));

			if(pessoa==null || pessoa.getIdPessoa()==null){
				FacesMessage message = new FacesMessage("Nenhum registro encontrado.");
				this.getFacesContext().addMessage("formulario", message);
				return "listar";
			}

			//preenche os dados do bean da tela
			pessoaBean.setIdPessoa(pessoa.getIdPessoa());
			pessoaBean.setDsNome(pessoa.getDsNome());
			pessoaBean.setVlIdade((pessoa.getVlIdade()!=null && !"".equals(pessoa.getVlIdade())) ? Integer.parseInt(pessoa.getVlIdade()):0);

			//preeche a lista de telefones da tela
			pessoaBean.setListaTelefone(new ArrayList<TelefoneBean>());
			for (Telefone telefone : pessoa.getListaTelefone()) {
				TelefoneBean telefoneBean = new TelefoneBean();
				telefoneBean.setIdTelefone(telefone.getIdTelefone());
				telefoneBean.setClTipo(telefone.getClTipo());
				telefoneBean.setDsNumero(telefone.getDsNumero());
				pessoaBean.getListaTelefone().add(telefoneBean);
			}
			
			return "editar";
		}
		catch (Exception e) {
			String msg = "Consulta nao realizada. Movito: " + ((e instanceof AgendaException ? ((AgendaException)e).getEx().getMessage():""));
			FacesMessage message = new FacesMessage(msg);
			this.getFacesContext().addMessage("formulario", message);
			return "falha";
		}
	}

	/**
	 * Cria uma nova pessoa
	 * @return
	 */
	public String criar() {
		try{

			pessoaBean = new PessoaBean();
			pessoaBean.setListaTelefone(new ArrayList<TelefoneBean>());
			
			this.setSession("telefones", pessoaBean.getListaTelefone());

			return "criar";
		}
		catch (Exception e) {
			String msg = "Criacao nao realizada. Movito: " + ((e instanceof AgendaException ? ((AgendaException)e).getEx().getMessage():""));
			FacesMessage message = new FacesMessage(msg);
			this.getFacesContext().addMessage("formulario", message);
			return "falha";
		}
	}

	/**
	 * Adiciona um telefone de uma pessoa
	 * @return
	 */
	public String adicionar() {
		try{

			TelefoneBean novo = new TelefoneBean();
			novo.setClTipo(telefoneBean.getClTipo());
			novo.setDsNumero(telefoneBean.getDsNumero());
			
			pessoaBean.getListaTelefone().add(novo);
			
			telefoneBean = new TelefoneBean();
			
			return "criar";
		}
		catch (Exception e) {
			FacesMessage message = new FacesMessage("Criacao nao realizada. Movito: " + e.getMessage());
			this.getFacesContext().addMessage("formulario", message);
			return "falha";
		}
	}

	/**
	 * Adiciona um telefone de uma pessoa
	 * @return
	 */
	public String adicionarEditar() {
		try{

			TelefoneBean novo = new TelefoneBean();
			novo.setClTipo(telefoneBean.getClTipo());
			novo.setDsNumero(telefoneBean.getDsNumero());
			
			pessoaBean.getListaTelefone().add(novo);
			
			telefoneBean = new TelefoneBean();
			
			return "editar";
		}
		catch (Exception e) {
			FacesMessage message = new FacesMessage("Criacao nao realizada. Movito: " + e.getMessage());
			this.getFacesContext().addMessage("formulario", message);
			return "falha";
		}
	}

	/**
	 * Remove um telefone da lista de uma pessoa
	 * @return
	 */
	public String remover() {
		try{

			HtmlDataTable telefones = (HtmlDataTable) this.getFacesContext().getViewRoot().findComponent("formulario:telefones");
			pessoaBean.getListaTelefone().remove(pessoaBean.getListaTelefone().indexOf(telefones.getRowData()));
			
			return null;
		}
		catch (Exception e) {
			String msg = "Exclusao nao realizada. Movito: " + ((e instanceof AgendaException ? ((AgendaException)e).getEx().getMessage():""));
			FacesMessage message = new FacesMessage(msg);
			this.getFacesContext().addMessage("formulario", message);
			return "falha";
		}
	}

	/**
	 * Exclui uma pessoa cadastrada
	 * @return
	 */
	public String excluir() {
		try{

			HtmlInputHidden idPessoa = (HtmlInputHidden) this.getFacesContext().getViewRoot().findComponent("formulario:idPessoa");
			
			Pessoa pessoa = getPessoaService().consultar((Integer)idPessoa.getValue());

			if(pessoa==null || pessoa.getIdPessoa()==null){
				FacesMessage message = new FacesMessage("Nenhum registro encontrado.");
				this.getFacesContext().addMessage("formulario", message);
				return "listar";
			}

			getPessoaService().excluir(pessoa.getIdPessoa());
			
			return "sucesso";
		}
		catch (Exception e) {
			String msg = "Exclusao nao realizada. Movito: " + ((e instanceof AgendaException ? ((AgendaException)e).getEx().getMessage():""));
			FacesMessage message = new FacesMessage(msg);
			this.getFacesContext().addMessage("formulario", message);
			return "falha";
		}
	}
	
	/**
	 * Exclui uma pessoa cadastrada
	 * @return
	 */
	public String alterar() {
		try{

			Pessoa pessoa = getPessoaService().consultar(pessoaBean.getIdPessoa());

			if(pessoa==null || pessoa.getIdPessoa()==null){
				FacesMessage message = new FacesMessage("Nenhum registro encontrado.");
				this.getFacesContext().addMessage("formulario", message);
				return "listar";
			}

			//preenche os dados da tela no objeto persistente
			pessoa.setDsNome(pessoaBean.getDsNome());
			pessoa.setVlIdade(pessoaBean.getVlIdade().toString());
			
			//preeche a lista de telefones da tela na lista de telefones persistente
			pessoa.setListaTelefone(new ArrayList<Telefone>());
			for (TelefoneBean telefoneBean : pessoaBean.getListaTelefone()) {
				Telefone telefone = new Telefone();
				telefone.setIdTelefone(telefoneBean.getIdTelefone()==0?null:telefoneBean.getIdTelefone());
				telefone.setClTipo(telefoneBean.getClTipo());
				telefone.setDsNumero(telefoneBean.getDsNumero());
				telefone.setPessoa(pessoa);
				pessoa.getListaTelefone().add(telefone);
			}

			getPessoaService().alterar(pessoa);
			return "sucesso";
			
		}
		catch (Exception e) {
			String msg = "Alteracao nao realizada. Movito: " + ((e instanceof AgendaException ? ((AgendaException)e).getEx().getMessage():""));
			FacesMessage message = new FacesMessage(msg);
			this.getFacesContext().addMessage("formulario", message);
			return "falha";
		}
	}
	
	public PessoaService getPessoaService() {
		return pessoaService;
	}

	public void setPessoaService(PessoaService pessoaService) {
		this.pessoaService = pessoaService;
	}
	
	private FacesContext getFacesContext(){
		return FacesContext.getCurrentInstance();
	}
	
	private Object getSession(String variavel){
		return this.getFacesContext().getExternalContext().getSessionMap().get(variavel);
	}
	
	private void setSession(String variavel, Object objeto){
		this.getFacesContext().getExternalContext().getSessionMap().put(variavel, objeto);
	}
	
	public PessoaBean getPessoaBean() {
		return pessoaBean;
	}

	public void setPessoaBean(PessoaBean pessoaBean) {
		this.pessoaBean = pessoaBean;
	}

	public List<PessoaBean> getListaPessoaBean() {
		return listaPessoaBean;
	}

	public void setListaPessoaBean(List<PessoaBean> listaPessoaBean) {
		this.listaPessoaBean = listaPessoaBean;
	}

	public TelefoneBean getTelefoneBean() {
		return telefoneBean;
	}

	public void setTelefoneBean(TelefoneBean telefoneBean) {
		this.telefoneBean = telefoneBean;
	}

}
