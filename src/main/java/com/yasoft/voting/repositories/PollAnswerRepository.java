package com.yasoft.voting.repositories;

import com.yasoft.voting.models.PollAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PollAnswerRepository extends JpaRepository<PollAnswer, Long> {
}
