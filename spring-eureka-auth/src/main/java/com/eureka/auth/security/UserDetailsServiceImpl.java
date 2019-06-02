package com.eureka.auth.security;

import java.sql.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService  {
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		AppUser user = null;


		try {
			String url = "jdbc:mysql://localhost:3306/users?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			Connection conn = DriverManager.getConnection(url,"root","root");
			PreparedStatement stmt = conn.prepareStatement("SELECT id, username, password FROM user WHERE username = ?");

			stmt.setString(1, username);

			ResultSet rs = stmt.executeQuery();

			while ( rs.next() ) {
				user = new AppUser(rs.getInt("id"), rs.getString("username"), encoder.encode(rs.getString("password")), "ADMIN");
			}
			conn.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}

		if(user != null && user.getUsername().equals(username)) {
			List<GrantedAuthority> grantedAuthorities = AuthorityUtils
						.commaSeparatedStringToAuthorityList("ROLE_" + user.getRole());

			return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
		}
		
		// If user not found. Throw this exception.
		throw new UsernameNotFoundException("Username: " + username + " not found");
	}
	
	private static class AppUser {
		private Integer id;
	    private String username, password;
	    private String role;
	    
		public AppUser(Integer id, String username, String password, String role) {
	    	this.id = id;
	    	this.username = username;
	    	this.password = password;
	    	this.role = role;
	    }

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
	    public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}
	}
}