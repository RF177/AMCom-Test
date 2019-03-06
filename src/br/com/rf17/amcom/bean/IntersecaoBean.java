package br.com.rf17.amcom.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.rf17.amcom.service.IntersecaoService;

@ManagedBean
@ViewScoped
public class IntersecaoBean implements Serializable {

	private static final long serialVersionUID = -7828515138688821565L;
	
	private static final String ID_FORM_DATA = "form_data_intersecao";
	private static final String ID_MESSAGES = ID_FORM_DATA + ":messages_intersecao";

	private Integer faixa1Inicial;
	private Integer faixa1Final;
	private Integer faixa2Inicial;
	private Integer faixa2Final;

	public Integer getFaixa1Inicial() {
		return faixa1Inicial;
	}

	public void setFaixa1Inicial(Integer faixa1Inicial) {
		this.faixa1Inicial = faixa1Inicial;
	}

	public Integer getFaixa1Final() {
		return faixa1Final;
	}

	public void setFaixa1Final(Integer faixa1Final) {
		this.faixa1Final = faixa1Final;
	}

	public Integer getFaixa2Inicial() {
		return faixa2Inicial;
	}

	public void setFaixa2Inicial(Integer faixa2Inicial) {
		this.faixa2Inicial = faixa2Inicial;
	}

	public Integer getFaixa2Final() {
		return faixa2Final;
	}

	public void setFaixa2Final(Integer faixa2Final) {
		this.faixa2Final = faixa2Final;
	}

	public void consultar() {
		try {
			boolean intersecao = IntersecaoService.verificaIntersecao(faixa1Inicial, faixa1Final, faixa2Inicial, faixa2Final);
			String msg = "Existe interseção entre as faixas 1 e 2.";
			if (!intersecao) {
				msg = "Não há interseção entre as faixas 1 e 2.";
			}
			FacesContext.getCurrentInstance().addMessage(ID_MESSAGES, new FacesMessage(FacesMessage.SEVERITY_INFO, "Resultado:", msg));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(ID_MESSAGES, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro:", e.getMessage()));
		}
	}

}
