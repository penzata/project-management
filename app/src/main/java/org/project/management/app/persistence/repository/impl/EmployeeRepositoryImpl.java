package org.project.management.app.persistence.repository.impl;

import lombok.AllArgsConstructor;
import org.project.management.app.persistence.entity.EmployeeEntity;
import org.project.management.app.persistence.repository.EmployeeRepositoryJpa;
import org.project.management.model.aggregators.EmployeeWithCompletedTasks;
import org.project.management.model.exception.IdNotFoundException;
import org.project.management.model.model.Employee;
import org.project.management.model.repository.EmployeeRepository;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final EmployeeRepositoryJpa employeeRepositoryJpa;

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Employee save(Employee employee) {
        EmployeeEntity employeeEntity = EmployeeEntity.fromModel(employee);
        EmployeeEntity savedEmployee = employeeRepositoryJpa.save(employeeEntity);
        return savedEmployee.toModel();
    }

    @Override
    public Employee findById(Long id) {
        EmployeeEntity employeeEntity = employeeRepositoryJpa.findById(id)
                .orElseThrow(() -> new IdNotFoundException(id.toString()));
        return employeeEntity.toModel();
    }

    @Override
    public void deleteById(Long id) {
        employeeRepositoryJpa.deleteById(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return employeeRepositoryJpa.existsByEmail(email);
    }

    @Override
    public List<Employee> findAllEmployees() {

        return employeeRepositoryJpa.findAll().stream()
                .map(EmployeeEntity::toModel)
                .toList();
    }

    @Override
    public List<EmployeeWithCompletedTasks> getTopEmployees(String maxNum) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValues(Map.of(
                "startDate", LocalDateTime.now().minusMonths(1),
                "limit", maxNum
        ));

        return jdbcTemplate.query("""
                            SELECT e.id, e.email, e.full_name, COUNT(t.id) as completed_tasks 
                            FROM employees e 
                            INNER JOIN tasks t on e.id = t.assignee_id and t.completed_date between :startDate and now()
                            GROUP BY e.id, e.email, e.full_name
                            order by completed_tasks DESC
                            LIMIT :limit
                        """,
                params, (rs, rowNum) -> this.mapRow(rs));
    }

    @Override
    public List<Employee> findByProjectId(Long projectId) {
        return employeeRepositoryJpa.findAllByProjectId(projectId);
    }

    public EmployeeWithCompletedTasks mapRow(ResultSet rs) throws SQLException {
        return new EmployeeWithCompletedTasks(rs.getLong("id"),
                rs.getString("full_name"),
                rs.getString("email"),
                rs.getInt("completed_tasks"));
    }
}