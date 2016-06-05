package dto;

public class UsuarioDTO {

	private int id;
	private String nick;
	private String nombre;
	private String apellido;
	private String password;
	private int idperfil;
	
	public UsuarioDTO(int id, String nick, String nombre, String apellido, String password, int idperfil) {
		this.id = id;
		this.nick = nick;
		this.nombre = nombre;
		this.apellido = apellido;
		this.password = password;
		this.idperfil = idperfil;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public String getPassword() {
		return password;
	}

	public int getIdperfil() {
		return idperfil;
	}

	public void setIdperfil(int idperfil) {
		this.idperfil = idperfil;
	}
	
	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}
}
