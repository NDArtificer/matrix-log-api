package com.artificer.matrixlog.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.artificer.matrixlog.domain.groups.ValidationGroups;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Client {

	@Id
	@NotNull(groups = ValidationGroups.ClientId.class)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	@NotBlank
	@Size(max = 200)
	private String name;

	@Column
	@NotBlank
	@Email
	@Size(max = 255)
	private String email;

	@Column
	@NotBlank
	@Size(max = 20)
	private String phone;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
