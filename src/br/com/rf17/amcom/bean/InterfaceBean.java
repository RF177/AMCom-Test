package br.com.rf17.amcom.bean;

import org.primefaces.event.SelectEvent;

public interface InterfaceBean {
	
	public abstract void onRowSelect(SelectEvent event);
	public abstract void novo();
	public abstract void salvar();
	public abstract void excluir();
	public abstract void listar();	
	public abstract void mensagem(Exception exception);
	
}
