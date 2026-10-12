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

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.samples.petclinic.owner.validation.PetValidationRule;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

abstract class PetFormProcessor {

	static final String FORM_VIEW = "pets/createOrUpdatePetForm";

	private static final String SUCCESS_VIEW = "redirect:/owners/{ownerId}";

	protected final OwnerRepository owners;

	protected PetFormProcessor(OwnerRepository owners) {
		this.owners = owners;
	}

	final String process(Owner owner, Pet pet, BindingResult result, RedirectAttributes redirectAttributes) {
		new PetValidator(validationRules(owner)).validate(pet, result);

		if (result.hasErrors()) {
			return FORM_VIEW;
		}

		try {
			save(owner, pet);
		}
		catch (DataIntegrityViolationException ex) {
			if (!isDuplicatePetNameViolation(ex)) {
				throw ex;
			}
			result.rejectValue("name", "duplicate", "already exists");
			return FORM_VIEW;
		}
		redirectAttributes.addFlashAttribute("message", successMessage());
		return SUCCESS_VIEW;
	}

	protected abstract List<PetValidationRule> validationRules(Owner owner);

	protected abstract void save(Owner owner, Pet pet);

	protected abstract String successMessage();

	private boolean isDuplicatePetNameViolation(DataIntegrityViolationException ex) {
		String message = ex.getMessage();
		return message != null && message.toLowerCase().contains("unique_owner_pet_name");
	}

}
