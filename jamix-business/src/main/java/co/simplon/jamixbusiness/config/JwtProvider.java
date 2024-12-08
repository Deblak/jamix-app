package co.simplon.jamixbusiness.config;

import java.time.Instant;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.algorithms.Algorithm;

public class JwtProvider {
    private final Algorithm algorithm;
    private final Long exp;

    protected JwtProvider(Algorithm algorithm, Long exp) {
	this.algorithm = algorithm;
	this.exp = exp;
    }

    public String create(String subject) {
	Instant issuedAt = Instant.now();
	Builder builder = JWT.create().withIssuedAt(issuedAt).withSubject(subject);

	if (exp > -1) {
	    Instant expiredAt = issuedAt.plusSeconds(exp);
	    builder.withExpiresAt(expiredAt);
	}
	return builder.sign(algorithm);
    }

}
