package com.events.eventmanagement.event.specification;

import com.events.eventmanagement.event.entity.Event;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class EventSpecification {
    public static Specification<Event> byTitle(String title) {
        return ((root, query, builder) -> {
            if (title == null) {
                return builder.conjunction();
            }
            if (title.isEmpty()) {
                return builder.equal(root.get("title"), "");
            }
            return builder.like(builder.lower(root.get("title")), "%" + title.toLowerCase() + "%");
        });
    }

    public static Specification<Event> byCategory(String category) {
        return ((root, query, builder) -> {
            if (category == null) {
                return builder.conjunction();
            }
            return builder.equal(builder.lower(root.get("category")), category.toLowerCase());
        });
    }

    public static Specification<Event> byUserId(Long userId) {
        return ((root, query, builder)-> {
            if (userId == null) {
                return builder.conjunction();
            }
            return builder.equal(root.get("organizer").get("id"), userId);
        });
    }

    public static Specification<Event> byDate(LocalDate date) {
        return ((root, query, builder)-> {
            if (date == null) {
                return builder.conjunction();
            }
            return builder.equal(root.get("date"), date);
        });
    }

    public static Specification<Event> byLocation(String location) {
        return ((root, query, builder)-> {
            if (location == null) {
                return builder.conjunction();
            }
            return builder.equal(root.get("location"), location);
        });
    }
}
