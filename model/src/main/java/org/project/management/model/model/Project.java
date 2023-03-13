package org.project.management.model.model;

import org.project.management.model.message.Events;
import org.project.management.model.message.MessagingBroker;

import java.util.Objects;

public class Project {
    private Long id;
    private String name;
    private String description;

    private Project() {
    }

    private Project(Long id,
                    String name,
                    String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public static Project project(Long id,
                                  String name,
                                  String description) {
        return new Project(id, name, description);
    }

    public Long getId() {
        return id;
    }

    public Project updateProjectInfo(Project employee) {
        this.name = employee.getName();
        this.description = employee.getDescription();

        MessagingBroker.produceEvent(Events.PROJECT_UPDATED);
        return this;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Project project = (Project) o;
        return Objects.equals(id, project.id) &&
                name.equals(project.name) &&
                Objects.equals(description, project.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}