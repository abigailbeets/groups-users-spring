package com.allstate.repository;

import com.allstate.domain.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by localadmin on 4/17/17.
 */
public interface UserRepository extends CrudRepository<User, Long> {
}
