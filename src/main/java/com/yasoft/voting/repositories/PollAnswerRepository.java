package com.yasoft.voting.repositories;

import com.yasoft.voting.models.PollAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PollAnswerRepository extends JpaRepository<PollAnswer, Long> {
    Optional<List<PollAnswer>> findAllByUserId(@Param("user_id") Long userId);
}