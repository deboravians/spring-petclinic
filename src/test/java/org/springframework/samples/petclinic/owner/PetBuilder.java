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

import java.time.LocalDate;

public final class PetBuilder {

	private Integer id;

	private String name;

	private LocalDate birthDate;

	private PetType type;

	private PetBuilder() {
	}

	public static PetBuilder pet() {
		return new PetBuilder();
	}

	public PetBuilder withId(int id) {
		this.id = id;
		return this;
	}

	public PetBuilder withName(String name) {
		this.name = name;
		return this;
	}

	public PetBuilder withBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
		return this;
	}

	public PetBuilder withType(String typeName) {
		PetType petType = new PetType();
		petType.setName(typeName);
		this.type = petType;
		return this;
	}

	public PetBuilder withType(PetType type) {
		this.type = type;
		return this;
	}

	public Pet build() {
		Pet pet = buildWithoutId();
		assignId(pet);
		return pet;
	}

	Pet buildWithoutId() {
		Pet pet = new Pet();
		if (this.name != null) {
			pet.setName(this.name);
		}
		if (this.birthDate != null) {
			pet.setBirthDate(this.birthDate);
		}
		if (this.type != null) {
			pet.setType(this.type);
		}
		return pet;
	}

	void assignId(Pet pet) {
		if (this.id != null) {
			pet.setId(this.id);
		}
	}

}
