package com.CraftIQ.CraftIQ.controller;

import com.CraftIQ.CraftIQ.dto.SkillDto;
import com.CraftIQ.CraftIQ.service.Impl.SkillServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/skill")
@RequiredArgsConstructor
@CrossOrigin
public class SkillController {

    private final SkillServiceImpl skillService;

    // Create Skill
    @PostMapping("/create")
    public ResponseEntity<SkillDto> createSkill(@RequestBody SkillDto skillDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(skillService.createSkill(skillDto));
    }

    // Get all Skills
    @GetMapping("/")
    public ResponseEntity<List<SkillDto>> getAllSkills() {
        return ResponseEntity.status(HttpStatus.OK).body(skillService.getAllSkills());
    }

    // Get Skill by ID
    @GetMapping("/{id}")
    public ResponseEntity<SkillDto> getSkillById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(skillService.getSkillById(id));
    }

    // Update Skill by ID
    @PutMapping("/{id}")
    public ResponseEntity<SkillDto> updateSkill(@PathVariable Long id, @RequestBody SkillDto skillDto) {
        return ResponseEntity.status(HttpStatus.OK).body(skillService.updateSkill(id, skillDto));
    }

    // Delete Skill by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteSkill(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(skillService.deleteSkill(id));
    }

}
