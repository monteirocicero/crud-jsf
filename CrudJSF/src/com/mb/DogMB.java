package com.mb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;

import com.facade.DogFacade;
import com.model.Dog;

@ManagedBean
public class DogMB extends BaseMB {
	
	@EJB
	private DogFacade dogFacade;
	
	private static final String CREATE_DOG = "createDog";
	private static final String DELETE_DOG = "deleteDog";
	private static final String UPDATE_DOG = "updateDog";
	private static final String LIST_ALL_DOGS = "listaAllDogs";
	private static final String STAY_IN_THE_SAME_PAGE = null;
	
	private Dog dog;
	
	public Dog getDog() {
		if (dog == null) {
			dog = new Dog();
		}
		return dog;
	}
	
	public void setDog(Dog dog) {
		this.dog = dog;
	}
	
	public List<Dog> getAllDogs() {
		return dogFacade.findAll();
	}
	
	public String updateDogStart() {
		return UPDATE_DOG;
	}
	
	public String updateDogEnd() {
		try {
			dogFacade.update(dog);
		} catch (EJBException e) {
			sendErrorMessageToUser("Error. Error. Check if the weight is above 0 or call the adm");
			return STAY_IN_THE_SAME_PAGE;
		}
		sendInfoMessageToUser("Operation Complete: Update");
		return LIST_ALL_DOGS;
	}
	
	public String deleteDogStart() {
		return DELETE_DOG;
	}
	
	public String deleteDogEnd() {
		try {
			dogFacade.delete(dog);
		} catch (EJBException e) {
			sendErrorMessageToUser("Error. Call the ADM");
			return STAY_IN_THE_SAME_PAGE;
		}
		sendInfoMessageToUser("Operation Complete: Delete");
		return LIST_ALL_DOGS;
	}
	
	public String createDogStart() {
		return CREATE_DOG;
	}
	
	public String createDogEnd() {
		try {
			dogFacade.save(dog);
		} catch (EJBException e) {
			sendErrorMessageToUser("Error. Error. Check if the weight is above 0 or call the adm");
			return STAY_IN_THE_SAME_PAGE;
		}
		sendInfoMessageToUser("Operation Complete: Create");
		return LIST_ALL_DOGS;
	}
	
	public String listaAllDogs() {
		return LIST_ALL_DOGS;
	}

}
