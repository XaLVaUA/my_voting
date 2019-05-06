package com.yasoft.voting.repositories;

import com.yasoft.voting.models.CensorPhrase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CensorPhraseRepository extends JpaRepository<CensorPhrase, Long> {
}
