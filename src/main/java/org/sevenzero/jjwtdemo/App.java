package org.sevenzero.jjwtdemo;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;

import org.apache.log4j.Logger;

/**
 * Hello world!
 *
 */
public class App {

	static final Logger log = Logger.getLogger(App.class);

	public static void main(String[] args) {
		System.out.println("Hello World!");

		Key key = MacProvider.generateKey();

		String compactJws = Jwts.builder().setSubject("login")
				.claim("username", "a").claim("passwd", "b")
				.signWith(SignatureAlgorithm.HS512, key).compact();
		
		log.info(compactJws);
		
		Jwt<?, ?> jwt = Jwts.parser().setSigningKey(key).parse(compactJws);
		log.info(jwt.getBody());
		log.info(jwt.getHeader());
		
		

	}

}
