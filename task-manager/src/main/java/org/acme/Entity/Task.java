package org.acme.Entity;

import java.util.Objects;

import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@RegisterForReflection
@Table(name="tasks")
@Entity
public class Task {
	
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "Title cannot be empty")
	private String title;
	
	@Size(max = 500, message = "Description cannot exceed 500 characters")
	private String description;
	
	@Column(nullable =false)
	private String status;
	
	public Task(Long id, @NotNull(message = "Title cannot be empty") String title,
			@Size(max = 500, message = "Description cannot exceed 500 characters") String description, String status) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.status = status;
	}

	public Task() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getStatus() {
        return status;
    }
	
	public void setStatus(String status) {
		if(!"To Do".equals(status) && !"Completed".equals(status)){
			throw new IllegalArgumentException("Invalid status value. Must be 'To Do' or 'Completed'");
		}
		
		this.status = status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, id, status, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		return Objects.equals(description, other.description) && Objects.equals(id, other.id)
				&& Objects.equals(status, other.status) && Objects.equals(title, other.title);
	}


	@Override
	public String toString() {
		return "Task [id=" + id + ", title=" + title + ", description=" + description + ", status=" + status + "]";
	}
	
	
	
	
	

}
