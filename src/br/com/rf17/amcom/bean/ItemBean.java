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
import br.com.rf17.amcom.model.Item;

@ManagedBean
@ViewScoped
public class ItemBean implements InterfaceBean, Serializable {

	private static final long serialVersionUID = -987480685798488464L;
	
	private static final String ID_FORM_DATA = "form_data_item";
	private static final String ID_FORM_TABLE = "form_table_item";
	private static final String ID_MESSAGES = ID_FORM_DATA + ":messages_item";
	
	private Item selectedItem;
	private List<Item> items = new ArrayList<Item>();

	private ItemDao itemDao = new ItemDao();

	public ItemBean() {
		listar();
	}

	public Item getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(Item selectedItem) {
		this.selectedItem = selectedItem;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	@Override
	public void onRowSelect(SelectEvent event) {
		try {
			PrimeFaces.current().resetInputs(ID_FORM_DATA);
			selectedItem = itemDao.getById(selectedItem.getOid());
			items = null;

			PrimeFaces.current().ajax().update(ID_FORM_TABLE);
			PrimeFaces.current().ajax().update(ID_FORM_DATA);
		} catch (Exception e) {
			mensagem(e);
		}
	}

	@Override
	public void novo() {
		selectedItem = new Item();
		items = null;
		PrimeFaces.current().ajax().update(ID_FORM_TABLE);
	}

	@Override
	public void salvar() {
		try {
			itemDao.save(selectedItem);
			FacesContext.getCurrentInstance().addMessage(ID_MESSAGES, new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvo!", "O item " + selectedItem.getOid() + " foi salvo com sucesso!"));
			listar();
		} catch (Exception e) {
			mensagem(e);
		}
	}

	@Override
	public void excluir() {
		try {
			itemDao.delete(selectedItem);
			FacesContext.getCurrentInstance().addMessage(ID_MESSAGES, new FacesMessage(FacesMessage.SEVERITY_INFO, "Excluído!", "O item " + selectedItem.getOid() + " foi excluído com sucesso!"));
			listar();
		} catch (Exception e) {
			mensagem(e);
		}
	}

	@Override
	public void listar() {
		try {
			selectedItem = null;
			items = itemDao.listAll();
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

}
