package com.chris.conferenceapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.chris.conferenceapp.models.Attendee;

public interface AttendeeRepository extends JpaRepository<Attendee, Long> {
}
