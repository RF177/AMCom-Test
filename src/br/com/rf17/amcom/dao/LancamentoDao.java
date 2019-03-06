package br.com.rf17.amcom.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import br.com.rf17.amcom.dao.DaoCrud;
import br.com.rf17.amcom.model.Lancamento;
import br.com.rf17.amcom.utils.hibernate.HibernateUtil;

public class LancamentoDao extends DaoCrud<Long, Lancamento> implements Serializable {
	
	private static final long serialVersionUID = 5444157021253496736L;

	public void save(Lancamento lancamento) throws Exception {
        saveCrud(lancamento, lancamento.getOid());
    }

    public void delete(Lancamento lancamento) throws Exception {
        deleteCrud(lancamento);
    }

    public Lancamento getById(Long pk) throws Exception {
        return getByIdCrud(Lancamento.class, pk);
    }
    
    @SuppressWarnings("unchecked")
	public List<Lancamento> listAll() throws Exception {
    	try {
			Session session = (Session) HibernateUtil.getEntityManager().getDelegate();
			Criteria criteria = session.createCriteria(Lancamento.class);
			criteria.addOrder(Order.desc("oid"));
			return (List<Lancamento>) criteria.list();
		} catch (Exception e) {
			throw new Exception("Erro ao listar items!");
		}
    }


}
