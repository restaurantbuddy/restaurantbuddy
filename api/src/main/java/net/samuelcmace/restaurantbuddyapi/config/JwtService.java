package net.samuelcmace.restaurantbuddyapi.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import net.samuelcmace.restaurantbuddyapi.DeploymentVariables;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Service bean containing methods to authenticate a user based on a JWT token.
 */
@Service
public class JwtService {

    /**
     * Method used to extract the username from the given JWT token.
     *
     * @param token The JWT token in question.
     * @return The subject (or username) stored in the token.
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Method used to extract the claims (or information stored) in a JWT token.
     *
     * @param token         The JWT token in question.
     * @param claimResolver Lambda function used to resolve the claims in a JWT token.
     * @param <T>
     * @return
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    /**
     * Method used to generate a token without any claims.
     *
     * @param userDetails An interface implementation containing information about the user.
     * @return A newly-instantiated JWT token.
     */
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    /**
     * Method used to generate a token with specific claims.
     *
     * @param extraClaims The claims to be added to the JWT token.
     * @param userDetails An interface implementation containing information about the user.
     * @return A newly-instantiated JWT token.
     */
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 365))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Method called to check the validity of a JWT token.
     *
     * @param token       The token in question.
     * @param userDetails An interface implementation containing information about the user.
     * @return A boolean indicating whether the token is valid.
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    /**
     * Method called to check the expiration on a JWT token.
     *
     * @param token The token in question
     * @return A boolean indicating whether the token is expired.
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Method called to extract the expiration date from a JWT token.
     *
     * @param token The token in question.
     * @return A new Date object containing inforamtion about when the token will expire.
     */
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Method called to extract all the JWT claims from a token.
     *
     * @param token The token in question.
     * @return An object containing the claims stored in the token.
     */
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Method called to get a new signing key to be used in all transactions dealing with JWT tokens.
     *
     * @return A newly-instantiated signing key.
     */
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(DeploymentVariables.JWT_SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
