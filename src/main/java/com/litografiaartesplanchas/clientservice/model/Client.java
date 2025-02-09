package com.litografiaartesplanchas.clientservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "cliente")
public class Client {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
	private int id;
	
	@NotBlank
	@Column(name = "nombre", nullable = false , length = 100)
	private String name;
	
	@Column(name = "apellido", nullable = true , length = 100)
	private String lastName;
	
	@NotBlank
	@Column(name = "tipo_persona", nullable = true , length = 10)
	private String typePerson;
	
	@NotBlank
	@Column(name = "correo", nullable = false , length = 255, unique = true)
	private String email;
	
	@NotBlank
	@Column(name = "contrasena", nullable = false , length = 255)
	private String password;
	
	@Column(name = "telefono", nullable = true , length = 35)
	private String phone;
	
	@Column(name = "foto", nullable = true , length = 255)
	private String photo;
	
	@Column(name = "activo", columnDefinition = "BOOLEAN DEFAULT true")
	private boolean isActive;
	
	@NotBlank
	@Column(name = "numero_documento", nullable = false , length = 45, unique=true)
	private String numberDocument;
	
	@NotBlank
	@ManyToOne
	@JoinColumn(name = "id_tipo_documento")
	private TypeDocument typeDocument;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getTypePerson() {
		return typePerson;
	}

	public void setTypePerson(String typePerson) {
		this.typePerson = typePerson;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getNumberDocument() {
		return numberDocument;
	}

	public void setNumberDocument(String numberDocument) {
		this.numberDocument = numberDocument;
	}

	public TypeDocument getTypeDocument() {
		return typeDocument;
	}

	public void setTypeDocument(TypeDocument typeDocument) {
		this.typeDocument = typeDocument;
	}
	
	
}
