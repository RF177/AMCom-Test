package br.com.rf17.amcom.service;

import java.util.ArrayList;
import java.util.List;

public class IntersecaoService {
	
	private IntersecaoService() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * Verifica se existe intersecao entre as faixas
	 * 
	 * @param faixa1Inicial
	 * @param faixa1Final
	 * @param faixa2Inicial
	 * @param faixa2Final
	 * @return
	 * @throws Exception
	 */
	public static boolean verificaIntersecao(Integer faixa1Inicial, Integer faixa1Final, Integer faixa2Inicial, Integer faixa2Final) throws Exception {
		
		verificaFaixas(faixa1Inicial, faixa1Final, faixa2Inicial, faixa2Final);
		
		List<Integer> list1Full = new ArrayList<Integer>();
		for (int i = faixa1Inicial; i <= faixa1Final; i++) {
			list1Full.add(i);
		}

		List<Integer> list2Full = new ArrayList<Integer>();
		for (int i = faixa2Inicial; i <= faixa2Final; i++) {
			list2Full.add(i);
		}

		for (int i1 : list1Full) {
			for (int i2 : list2Full) {
				if (i1 == i2) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Verifica e valida as faixas informadas
	 * 
	 * @param faixa1Inicial
	 * @param faixa1Final
	 * @param faixa2Inicial
	 * @param faixa2Final
	 * @throws Exception
	 */
	public static void verificaFaixas(Integer faixa1Inicial, Integer faixa1Final, Integer faixa2Inicial, Integer faixa2Final) throws Exception {
		if(faixa1Inicial > faixa1Final || faixa2Inicial > faixa2Final) {
			throw new Exception("Faixa inicial é maior que a faixa final!"); 
		}	
	}
	
}
