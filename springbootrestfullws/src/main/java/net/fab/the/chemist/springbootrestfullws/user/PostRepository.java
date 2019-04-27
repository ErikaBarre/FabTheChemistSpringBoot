package net.fab.the.chemist.springbootrestfullws.user;

import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

}
