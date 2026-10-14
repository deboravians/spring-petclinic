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

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
class ClinicFacade {

	private final OwnerRepository owners;

	private final PetTypeRepository types;

	ClinicFacade(OwnerRepository owners, PetTypeRepository types) {
		this.owners = owners;
		this.types = types;
	}

	Owner findOwner(int ownerId) {
		return this.owners.findById(ownerId)
			.orElseThrow(() -> new IllegalArgumentException(
					"Owner not found with id: " + ownerId + ". Please ensure the ID is correct "));
	}

	Owner findOwnerOrNew(Integer ownerId) {
		return ownerId == null ? new Owner() : findOwner(ownerId);
	}

	Pet findPet(int ownerId, Integer petId) {
		if (petId == null) {
			return new Pet();
		}
		return findOwner(ownerId).getPet(petId);
	}

	Pet requirePet(Owner owner, int ownerId, int petId) {
		Pet pet = owner.getPet(petId);
		if (pet == null) {
			throw new IllegalArgumentException(
					"Pet with id " + petId + " not found for owner with id " + ownerId + ".");
		}
		return pet;
	}

	Page<Owner> findOwnersByLastName(String lastName, Pageable pageable) {
		return this.owners.findByLastNameStartingWith(lastName, pageable);
	}

	Owner save(Owner owner) {
		return this.owners.save(owner);
	}

	Owner saveAndFlush(Owner owner) {
		return this.owners.saveAndFlush(owner);
	}

	void addVisit(Owner owner, int petId, Visit visit) {
		owner.addVisit(petId, visit);
		this.owners.save(owner);
	}

	List<PetType> findPetTypes() {
		return this.types.findPetTypes();
	}

}
