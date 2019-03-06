package br.com.rf17.amcom.service;

import br.com.rf17.amcom.model.Lancamento;
import br.com.rf17.amcom.model.LancamentoItem;

public class LancamentoService {

	/**
	 * Calcula o valor total do lancamento
	 * 
	 * @param lancamento
	 * @return lancamento
	 */
	public static Lancamento calculaValorTotal(Lancamento lancamento) {
		lancamento.setValorTotal(0.0);
		for(LancamentoItem lancamentoItem : lancamento.getLancamentoItems()) {
			lancamento.setValorTotal(lancamento.getValorTotal() + lancamentoItem.getItem().getValor());
		}
		return lancamento;
	}
	
}
