package Behavior;

public class Password {
	private final String PASSWORDENCRYPTED = "M[caih:,*+2";
	private final int key = 6;
	public boolean validatePassword(String password) {
		if (password.length() != PASSWORDENCRYPTED.length()) {
			return false;
		}
		
		for (int i = 0; i < password.length(); i ++) {
			if (((char) password.charAt(i) - 6) != PASSWORDENCRYPTED.charAt(i)) {
				return false;
			}
		}
		return true;
	}
}