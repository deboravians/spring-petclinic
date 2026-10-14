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
package org.springframework.samples.petclinic.owner.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.samples.petclinic.owner.Pet;
import org.springframework.validation.Errors;
import org.springframework.validation.MapBindingResult;

class PetValidationRulesTests {

	private Owner owner;

	private Errors errors;

	@BeforeEach
	void setUp() {
		this.owner = new Owner();
		Pet max = new Pet();
		max.setName("Max");
		max.setId(1);
		this.owner.getPets().add(max);
		this.errors = new MapBindingResult(new HashMap<>(), "pet");
	}

	@Test
	void birthDateInTheFutureIsRejected() {
		Pet pet = new Pet();
		pet.setBirthDate(LocalDate.now().plusDays(1));

		new BirthDateNotInFutureRule().validate(pet, this.errors);

		assertTrue(this.errors.hasFieldErrors("birthDate"));
	}

	@Test
	void birthDateTodayIsAccepted() {
		Pet pet = new Pet();
		pet.setBirthDate(LocalDate.now());

		new BirthDateNotInFutureRule().validate(pet, this.errors);

		assertFalse(this.errors.hasErrors());
	}

	@Test
	void creationRejectsNameAlreadyUsedByOwner() {
		Pet pet = new Pet();
		pet.setName("max");

		new UniqueNameOnCreationRule(this.owner).validate(pet, this.errors);

		assertTrue(this.errors.hasFieldErrors("name"));
	}

	@Test
	void updateAllowsPetToKeepItsOwnName() {
		Pet pet = new Pet();
		pet.setName("Max");
		pet.setId(1);

		new UniqueNameOnUpdateRule(this.owner).validate(pet, this.errors);

		assertFalse(this.errors.hasErrors());
	}

	@Test
	void updateRejectsNameOfAnotherPet() {
		Pet pet = new Pet();
		pet.setName("Max");
		pet.setId(2);

		new UniqueNameOnUpdateRule(this.owner).validate(pet, this.errors);

		assertTrue(this.errors.hasFieldErrors("name"));
	}

}
