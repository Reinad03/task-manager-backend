package org.acme.Service;

import java.util.List;

import org.acme.Entity.Task;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class TaskService{
	
	@PersistenceContext
	EntityManager entityManager;
	
	public List<Task> getAllTasks(){
		return entityManager.createQuery("SELECT t FROM Task t", Task.class).getResultList();
	}
	
	public Task findById(Long id) {
		return entityManager.find(Task.class, id);
	}
	
	@Transactional
	public Task createTask(Task task) {
		if (!"To Do".equals(task.getStatus()) && !"Completed".equals(task.getStatus())) {
            throw new IllegalArgumentException("Invalid status value. Must be 'To Do' or 'Completed'");
        }
        entityManager.persist(task);
        return task;
	}
	
	@Transactional
	public Task updateTask(Long id, Task updatedTask) {
		 Task task = entityManager.find(Task.class, id);
	        if (task == null) {
	            throw new NotFoundException("Task with id " + id + " not found");
	        }
	        task.setTitle(updatedTask.getTitle());
	        task.setDescription(updatedTask.getDescription());
	        task.setStatus(updatedTask.getStatus());
	        return task;
	}
	
	@Transactional
	public void deleteTask(Long id) {
        Task task = entityManager.find(Task.class, id);
        if (task != null) {
            entityManager.remove(task);
        }
	}

}
