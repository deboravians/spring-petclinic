/*
 * Copyright 2012-2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.owner;

import java.util.ArrayList;
import java.util.List;

public final class OwnerBuilder {

	private Integer id;

	private String firstName;

	private String lastName;

	private String address;

	private String city;

	private String telephone;

	private final List<PetBuilder> pets = new ArrayList<>();

	private OwnerBuilder() {
	}

	public static OwnerBuilder owner() {
		return new OwnerBuilder();
	}

	public OwnerBuilder withId(int id) {
		this.id = id;
		return this;
	}

	public OwnerBuilder withFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public OwnerBuilder withLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public OwnerBuilder withAddress(String address) {
		this.address = address;
		return this;
	}

	public OwnerBuilder withCity(String city) {
		this.city = city;
		return this;
	}

	public OwnerBuilder withTelephone(String telephone) {
		this.telephone = telephone;
		return this;
	}

	public OwnerBuilder withPet(PetBuilder pet) {
		this.pets.add(pet);
		return this;
	}

	public Owner build() {
		Owner owner = new Owner();
		if (this.id != null) {
			owner.setId(this.id);
		}
		if (this.firstName != null) {
			owner.setFirstName(this.firstName);
		}
		if (this.lastName != null) {
			owner.setLastName(this.lastName);
		}
		if (this.address != null) {
			owner.setAddress(this.address);
		}
		if (this.city != null) {
			owner.setCity(this.city);
		}
		if (this.telephone != null) {
			owner.setTelephone(this.telephone);
		}
		for (PetBuilder petBuilder : this.pets) {
			Pet pet = petBuilder.buildWithoutId();
			owner.addPet(pet);
			petBuilder.assignId(pet);
		}
		return owner;
	}

}
