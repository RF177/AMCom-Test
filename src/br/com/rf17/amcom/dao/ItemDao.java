package br.com.rf17.amcom.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import java.io.Serializable;
import java.util.List;

import br.com.rf17.amcom.dao.DaoCrud;
import br.com.rf17.amcom.model.Item;

import br.com.rf17.amcom.utils.hibernate.HibernateUtil;

public class ItemDao extends DaoCrud<Long, Item> implements Serializable {

	private static final long serialVersionUID = 2859645699658665022L;

	public void save(Item item) throws Exception {
		saveCrud(item, item.getOid());
	}

	public void delete(Item item) throws Exception {
		deleteCrud(item);
	}

	public Item getById(Long pk) throws Exception {
		return getByIdCrud(Item.class, pk);
	}

	@SuppressWarnings("unchecked")
	public List<Item> listAll() throws Exception {
		try {
			Session session = (Session) HibernateUtil.getEntityManager().getDelegate();
			Criteria criteria = session.createCriteria(Item.class);
			criteria.addOrder(Order.desc("oid"));
			return (List<Item>) criteria.list();
		} catch (Exception e) {
			throw new Exception("Erro ao listar items!");
		}
	}

}
