package com.CraftIQ.CraftIQ.repository;

import com.CraftIQ.CraftIQ.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
}

