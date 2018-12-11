package com.codingnomads.impacttracker.logic.user;

import com.codingnomads.impacttracker.model.Role;

public interface RoleRepository {

    Role findByRole(String role);
}

