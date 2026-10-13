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

import java.util.List;

import org.springframework.samples.petclinic.owner.validation.PetValidationRule;
import org.springframework.samples.petclinic.owner.validation.PetValidationRules;
import org.springframework.util.Assert;

class UpdatePetFormProcessor extends PetFormProcessor {

	UpdatePetFormProcessor(ClinicFacade clinic) {
		super(clinic);
	}

	@Override
	protected List<PetValidationRule> validationRules(Owner owner) {
		return PetValidationRules.forUpdate(owner);
	}

	/**
	 * Updates the pet details if it exists or adds a new pet to the owner.
	 * @param owner The owner of the pet
	 * @param pet The pet with updated details
	 */
	@Override
	protected void save(Owner owner, Pet pet) {
		Integer id = pet.getId();
		Assert.state(id != null, "'pet.getId()' must not be null");
		Pet existingPet = owner.getPet(id);
		if (existingPet != null) {
			// Update existing pet's properties
			existingPet.setName(pet.getName());
			existingPet.setBirthDate(pet.getBirthDate());
			existingPet.setType(pet.getType());
		}
		else {
			owner.addPet(pet);
		}
		this.clinic.saveAndFlush(owner);
	}

	@Override
	protected String successMessage() {
		return "Pet details has been edited";
	}

}
