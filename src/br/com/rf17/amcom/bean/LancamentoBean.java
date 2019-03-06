package br.com.rf17.amcom.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

import br.com.rf17.amcom.dao.ItemDao;
import br.com.rf17.amcom.dao.LancamentoDao;
import br.com.rf17.amcom.model.Item;
import br.com.rf17.amcom.model.Lancamento;
import br.com.rf17.amcom.model.LancamentoItem;
import br.com.rf17.amcom.service.LancamentoService;

@ManagedBean
@ViewScoped
public class LancamentoBean implements InterfaceBean, Serializable {

	private static final long serialVersionUID = 4950559252074005386L;
	
	private static final String ID_FORM_DATA = "form_data_lancamento";
	private static final String ID_FORM_TABLE = "form_table_lancamento";
	private static final String ID_MESSAGES = ID_FORM_DATA + ":messages_lancamento";
	
	private Lancamento selectedLancamento;
	private LancamentoItem selectedLancamentoItem;	
	private List<Lancamento> lancamentos = new ArrayList<Lancamento>();

	private LancamentoDao lancamentoDao = new LancamentoDao();

	private Long numeroItemAdd;

	public LancamentoBean() {
		listar();
	}

	public Long getNumeroItemAdd() {
		return numeroItemAdd;
	}

	public void setNumeroItemAdd(Long numeroItemAdd) {
		this.numeroItemAdd = numeroItemAdd;
	}

	public LancamentoItem getSelectedLancamentoItem() {
		return selectedLancamentoItem;
	}

	public void setSelectedLancamentoItem(LancamentoItem selectedLancamentoItem) {
		this.selectedLancamentoItem = selectedLancamentoItem;
	}

	public Lancamento getSelectedLancamento() {
		return selectedLancamento;
	}

	public void setSelectedLancamento(Lancamento selectedLancamento) {
		this.selectedLancamento = selectedLancamento;
	}

	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}

	public void setLancamentos(List<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
	}

	@Override
	public void onRowSelect(SelectEvent event) {
		try {
			PrimeFaces.current().resetInputs(ID_FORM_DATA);
			selectedLancamento = lancamentoDao.getById(selectedLancamento.getOid());
			lancamentos = null;

			PrimeFaces.current().ajax().update(ID_FORM_TABLE);
			PrimeFaces.current().ajax().update(ID_FORM_DATA);
		} catch (Exception e) {
			mensagem(e);
		}
	}

	@Override
	public void novo() {
		selectedLancamento = new Lancamento();
		lancamentos = null;
		PrimeFaces.current().ajax().update(ID_FORM_TABLE);
	}

	@Override
	public void salvar() {
		try {
			lancamentoDao.save(selectedLancamento);
			FacesContext.getCurrentInstance().addMessage(ID_MESSAGES, new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvo!",
					"O lancamento " + selectedLancamento.getOid() + " foi salvo com sucesso!"));
			listar();
		} catch (Exception e) {
			mensagem(e);
		}
	}

	@Override
	public void excluir() {
		try {
			lancamentoDao.delete(selectedLancamento);
			FacesContext.getCurrentInstance().addMessage(ID_MESSAGES, new FacesMessage(FacesMessage.SEVERITY_INFO, "Excluído!",
					"O lancamento " + selectedLancamento.getOid() + " foi excluído com sucesso!"));
			listar();
		} catch (Exception e) {
			mensagem(e);
		}
	}

	@Override
	public void listar() {
		try {
			selectedLancamento = null;
			lancamentos = lancamentoDao.listAll();
			PrimeFaces.current().ajax().update(ID_FORM_TABLE);
		} catch (Exception e) {
			mensagem(e);
		}
	}
	
	@Override 
	public void mensagem(Exception e) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro:", e.getMessage()));
		PrimeFaces.current().ajax().update(ID_MESSAGES);
	}

	public void addItem() {
		try {
			Item itemAdd = new ItemDao().getById(numeroItemAdd);
			if (itemAdd == null) {
				throw new Exception("Item não encontrado!");				
			} else {
				if (selectedLancamento.getLancamentoItems() == null) {
					selectedLancamento.setLancamentoItems(new ArrayList<LancamentoItem>());
				}
				LancamentoItem lancamentoItem = new LancamentoItem();
				lancamentoItem.setLancamento(selectedLancamento);
				lancamentoItem.setItem(itemAdd);
				selectedLancamento.getLancamentoItems().add(lancamentoItem);
			}
			numeroItemAdd = null;
			calcula();
		} catch (Exception e) {
			mensagem(e);
		}
	}
	
	public void excluirItem() {
		selectedLancamento.getLancamentoItems().remove(selectedLancamentoItem);
		calcula();
	}
	
	public void calcula() {
		selectedLancamento = LancamentoService.calculaValorTotal(selectedLancamento);
	}

}
