package rsa;
import java.math.BigInteger;

public class RsaAttributes {

	private BigInteger modulus;
	private BigInteger secretKey;
	private BigInteger publicKey;

	public BigInteger getModulus() {
		return modulus;
	}

	public void setModulus(BigInteger modulus) {
		this.modulus = modulus;
	}

	public BigInteger getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(BigInteger secretKey) {
		this.secretKey = secretKey;
	}

	public BigInteger getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(BigInteger publicKey) {
		this.publicKey = publicKey;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((modulus == null) ? 0 : modulus.hashCode());
		result = prime * result
				+ ((publicKey == null) ? 0 : publicKey.hashCode());
		result = prime * result
				+ ((secretKey == null) ? 0 : secretKey.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		RsaAttributes other = (RsaAttributes) obj;
		if (modulus == null) {
			if (other.modulus != null) {
				return false;
			}
		} else if (!modulus.equals(other.modulus)) {
			return false;
		}
		if (publicKey == null) {
			if (other.publicKey != null) {
				return false;
			}
		} else if (!publicKey.equals(other.publicKey)) {
			return false;
		}
		if (secretKey == null) {
			if (other.secretKey != null) {
				return false;
			}
		} else if (!secretKey.equals(other.secretKey)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "RsaAttributes [modulus=" + modulus + ", secretKey=" + secretKey
				+ ", publicKey=" + publicKey + "]";
	}

}
