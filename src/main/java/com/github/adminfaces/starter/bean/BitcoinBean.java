package com.github.adminfaces.starter.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;

import com.github.adminfaces.starter.audit.CustomRevisionEntity;
import com.github.adminfaces.starter.model.Bitcoin;

@Named
@ViewScoped
public class BitcoinBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = -1616465592668678083L;

	private Bitcoin bitcoin;
	private Bitcoin selectedBitcoin;
	private List<Bitcoin> auditList;
	
	private Integer bitcoinId;
	
	// Test
	@PersistenceContext
	private EntityManager em;

	@PostConstruct
	public void init() {
		bitcoin = new Bitcoin();
	}

	public void save() {
		getFacade().save(bitcoin);
	}

	public void delete(Bitcoin bitcoin) {
		getFacade().delete(bitcoin);
	}

	public List<Bitcoin> list() {
		return getFacade().findAllBitcoin();
	}

	public void prepareEdit(Bitcoin bitcoin) {
		this.bitcoin = bitcoin;
	}

	public Bitcoin getBitcoin() {
		return bitcoin;
	}

	public void setBitcoin(Bitcoin bitcoin) {
		this.bitcoin = bitcoin;
	}
	
	public void showAudit(Bitcoin b) {
		AuditReader auditReader = AuditReaderFactory.get(em);

		List<Number> revisionNumbers = auditReader.getRevisions(Bitcoin.class, b.getId());
		
		for (Number rev : revisionNumbers) {
			Bitcoin auditedBitcoin = auditReader.find(Bitcoin.class, b.getId(), rev);
			//log.info("Bitcoin ["+auditedBitcoin+"] at revision ["+rev+"].");
			System.err.println("Bitcoin ["+auditedBitcoin+"] at revision ["+rev+"].");
			Date date = auditReader.getRevisionDate(rev);
			System.err.println(date);
			
			
			Query q = em.createQuery(" select cre from CustomRevisionEntity cre where cre.id = :pId ");
			q.setParameter("pId", rev.intValue());
			
			CustomRevisionEntity cre = (CustomRevisionEntity) q.getSingleResult();
			
			System.out.println(cre.getIp());
			
		}
		
		
	}
	
//	public void onChangeBitcoin(AjaxBehaviorEvent event) {
//		System.out.println(selectedBitcoin);
//		
//		if(event.getComponent().getAttributes().get("value") != null && 
//				event.getComponent().getAttributes().get("value").toString().trim().equalsIgnoreCase("bitcoins")){ 
//			//seu código aqui } 
//		}
//		
//	}
	
	public void onChangeBitcoin() {
		System.out.println(bitcoinId);
		auditList = new ArrayList<Bitcoin>();
		Bitcoin bitcoin;
		
		
		AuditReader auditReader = AuditReaderFactory.get(em);

		List<Number> revisionNumbers = auditReader.getRevisions(Bitcoin.class, bitcoinId);
		
		
		for (Number rev : revisionNumbers) {
			bitcoin = auditReader.find(Bitcoin.class, bitcoinId, rev);
			
			// Revision Date
			Date revisionDate = auditReader.getRevisionDate(rev);
			System.err.println(revisionDate);
			bitcoin.setRevisionDate(revisionDate);
			
			//Query q = em.createQuery(" select cre from CustomRevisionEntity cre where cre.id = :pId ");
			//q.setParameter("pId", rev.intValue());
			
			//CustomRevisionEntity cre = (CustomRevisionEntity) q.getSingleResult();
			
			CustomRevisionEntity cre = em.find(CustomRevisionEntity.class, rev);
			
			// User name
			System.err.println(cre.getUsername());
			bitcoin.setUsername(cre.getUsername());
			
			// Ip
			System.err.println(cre.getIp());
			bitcoin.setIp(cre.getIp());
			
			// Test
			System.err.println(cre.getTimestamp());
			System.err.println(cre.getRevisionDate());
			
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(cre.getTimestamp());
			c.getTime();
			System.out.println(c.getTime());
			
			auditList.add(bitcoin);
		}
		
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		String username = authentication.getName();
//		
//		AuditQuery q = auditReader.createQuery().forRevisionsOfEntity(Bitcoin.class, false, true);
//		q.addProjection(AuditEntity.revisionNumber());
//		q.add(AuditEntity.revisionProperty("username").eq(username));
//		List<Number> revNumbers = q.getResultList();
//		
//		for (Number number : revNumbers) {
//			System.err.println(number);
//		}
//		
//		
//		
//		AuditReader reader = AuditReaderFactory.get(em);
//		List<Number> revisions = reader.getRevisions(Bitcoin.class, bitcoinId);
//		CustomRevisionEntity entity = em.find(CustomRevisionEntity.class, revisions.get(0));
//		
//		System.err.println("username: " + entity.getUsername() + " Ip: " + entity.getIp());
	}

	public Bitcoin getSelectedBitcoin() {
		return selectedBitcoin;
	}

	public void setSelectedBitcoin(Bitcoin selectedBitcoin) {
		this.selectedBitcoin = selectedBitcoin;
	}

	public List<Bitcoin> getAuditList() {
		return auditList;
	}

	public void setAuditList(List<Bitcoin> auditList) {
		this.auditList = auditList;
	}

	public Integer getBitcoinId() {
		return bitcoinId;
	}

	public void setBitcoinId(Integer bitcoinId) {
		this.bitcoinId = bitcoinId;
	}

	



}
