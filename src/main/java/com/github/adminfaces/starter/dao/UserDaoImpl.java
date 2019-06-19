package com.github.adminfaces.starter.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import com.github.adminfaces.starter.model.Authority;
import com.github.adminfaces.starter.model.User;

@Repository("userDaoImpl")
public class UserDaoImpl implements UserDaoCustom {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private DataSource dataSource;

	// https://stackoverflow.com/questions/30431035/spring-jpa-repository-dynamic-query
	// https://thoughts-on-java.org/jpa-native-queries/
	// https://www.petrikainulainen.net/programming/spring-framework/spring-data-jpa-tutorial-three-custom-queries-with-query-methods/
	
	// https://hibernate.org/orm/envers/
	// https://vladmihalcea.com/the-best-way-to-implement-an-audit-log-using-hibernate-envers/
	// https://vladmihalcea.com/the-best-way-to-map-a-many-to-many-association-with-extra-columns-when-using-jpa-and-hibernate/
	// https://coderi.com.br/2016/07/23/spring-boot-security-data-customizando-um-authentication-provider/
	
	// https://aws.amazon.com/pt/cloud9/
	
	// https://github.com/adminfaces
	// https://github.com/adminfaces/admin-starter-springboot-security
	
	
	

	@Override
	public List<User> findAllUserHQL() {
		Query q = em.createQuery(" select u from User u ");
		List<User> list = q.getResultList();
		return list;
	}

	@Override
	public List<User> findAllUserNative() {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select u.*, a.* ");
		sql.append(" from users u ");
		sql.append(" left join user_authority ua on ua.user_id = u.user_id ");
		sql.append(" left join authority a on a.authority_id = ua.authority_id ");
		
		Query q = em.createNativeQuery(sql.toString(), User.class);
		List<User> list = q.getResultList();
		
		List<User> userList = new ArrayList<User>();
		
		// Fix
		for (User user : list) {
			
			if (!userList.contains(user)) {
				userList.add(user);
			}
			
		}
		
		return userList;
		
//		StringBuilder sql = new StringBuilder();
//		sql.append(" select u.user_id, u.email, ua.user_id as uaUserId, ua.authority_id as uaAuthorityId, a.authority_id, a.authority ");
//		sql.append(" from users u ");
//		sql.append(" left join user_authority ua on ua.user_id = u.user_id ");
//		sql.append(" left join authority a on a.authority_id = ua.authority_id ");
//		
//		Query q = em.createNativeQuery(sql.toString());
//		
//		List<Object[]> list = q.getResultList();
//		
//		List<User> userList = new ArrayList<User>();
//		User user;
//		Set<Authority> authorityList = new HashSet<Authority>();
//		Authority auth;
//		
//		for (Object[] obj : list) {
//			Integer userId = (int) obj[0];
//			String userEmail = (String) obj[1];
//			
//			Integer uaUserId = (int) obj[2];
//			Integer uaAuthorityId = (int) obj[3];
//			
//			Integer authorityId = (int) obj[4];
//			String authority = (String) obj[5];
//						
//			user = new User();
//			user.setId(userId);
//			user.setEmail(userEmail);
//			
//			boolean newUser = false;
//			
//			for (User u : userList) {
//				
//				if (u.getId().equals(uaUserId)) {
//					auth = new Authority();
//					auth.setId(authorityId);
//					auth.setAuthority(authority);
//					
//					u.getAuths().add(auth);
//					
//					newUser = false;
//				} else {
//					authorityList = new HashSet<Authority>();
//					auth = new Authority();
//					auth.setId(authorityId);
//					auth.setAuthority(authority);
//					authorityList.add(auth);
//					
//					user.setAuths(authorityList);
//					
//					newUser = true;
//				}
//				
//			}
//			
//			if (userList.isEmpty()) {
//				authorityList = new HashSet<Authority>();
//				auth = new Authority();
//				auth.setId(authorityId);
//				auth.setAuthority(authority);
//				authorityList.add(auth);
//				
//				user.setAuths(authorityList);
//				
//				userList.add(user);
//			}
//			
//			if (newUser) {
//				userList.add(user);
//			}
//			
//		}
//		
//		return userList;
	}

	@Override
	public List<User> findAllUserDataSource() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
