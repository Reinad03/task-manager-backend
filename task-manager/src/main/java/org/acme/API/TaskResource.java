package org.acme.API;

import java.util.List;

import org.acme.Entity.Task;
import org.acme.Service.TaskService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/tasks")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TaskResource {
	
	@Inject
	TaskService taskService;
	
	@GET
	public List<Task> getAllTasks(){
		return taskService.getAllTasks();
	}
	
	@POST
	public Response createTask (Task task) {
		if (task.getTitle() == null || task.getTitle().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Title is required").build();
        }
        if (task.getDescription() != null && task.getDescription().length() > 500) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Description exceeds 500 characters").build();
        }
        Task createdTask = taskService.createTask(task);
        return Response.status(Response.Status.CREATED).entity(createdTask).build();
	}
	
	@PUT
    @Path("/{id}")
    public Response updateTask(@PathParam("id") Long id, Task updatedTask) {
        try {
            Task task = taskService.updateTask(id, updatedTask);
            return Response.ok(task).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
	
	@DELETE
    @Path("/{id}")
    public Response deleteTask(@PathParam("id") Long id) {
        taskService.deleteTask(id);
        return Response.noContent().build();
    }
	
	
	

}
