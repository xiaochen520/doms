package com.echo.dto;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * PcCdLargeStationT entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.echo.dto.PcCdLargeStationT
 * @author MyEclipse Persistence Tools
 */

public class PcCdLargeStationTDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory
			.getLog(PcCdLargeStationTDAO.class);

	protected void initDao() {
		// do nothing
	}

	public void save(PcCdLargeStationT transientInstance) {
		log.debug("saving PcCdLargeStationT instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(PcCdLargeStationT persistentInstance) {
		log.debug("deleting PcCdLargeStationT instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public PcCdLargeStationT findById(java.lang.String id) {
		log.debug("getting PcCdLargeStationT instance with id: " + id);
		try {
			PcCdLargeStationT instance = (PcCdLargeStationT) getHibernateTemplate()
					.get("com.echo.dto.PcCdLargeStationT", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(PcCdLargeStationT instance) {
		log.debug("finding PcCdLargeStationT instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding PcCdLargeStationT instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from PcCdLargeStationT as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all PcCdLargeStationT instances");
		try {
			String queryString = "from PcCdLargeStationT";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public PcCdLargeStationT merge(PcCdLargeStationT detachedInstance) {
		log.debug("merging PcCdLargeStationT instance");
		try {
			PcCdLargeStationT result = (PcCdLargeStationT) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(PcCdLargeStationT instance) {
		log.debug("attaching dirty PcCdLargeStationT instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(PcCdLargeStationT instance) {
		log.debug("attaching clean PcCdLargeStationT instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static PcCdLargeStationTDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (PcCdLargeStationTDAO) ctx.getBean("PcCdLargeStationTDAO");
	}
}