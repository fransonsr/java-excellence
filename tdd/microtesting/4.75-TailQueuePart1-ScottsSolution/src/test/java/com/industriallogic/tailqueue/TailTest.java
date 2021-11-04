// ***************************************************************************
// Copyright (c) 2020, Industrial Logic, Inc., All Rights Reserved.
//
// This code is the exclusive property of Industrial Logic, Inc. It may ONLY be
// used by students during Industrial Logic's workshops or by individuals
// who are being coached by Industrial Logic on a project.
//
// This code may NOT be copied or used for any other purpose without the prior
// written consent of Industrial Logic, Inc.
// ****************************************************************************

package com.industriallogic.tailqueue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Calendar;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("unchecked") // blah, blah, blah
class TailTest {

    static final int DATE_OFFSET_SAME = 0;
    static final int DATE_OFFSET_BEFORE = -10;
    static final int DATE_OFFSET_AFTER = 10;

    static final BiConsumer<Tail, Tail> RESULT_SAME_PRIORITY = (tail, other) -> assertThat(other).isEqualByComparingTo(tail);
    static final BiConsumer<Tail, Tail> RESULT_HIGHER_PRIORITY = (tail, other) -> assertThat(other).isLessThan(tail);
    static final BiConsumer<Tail, Tail> RESULT_LOWER_PRIORITY = (tail, other) -> assertThat(other).isGreaterThan(tail);

    static Stream<Arguments> compareToValues() {
        return Stream.of(
         Arguments.of("different instance, same IDs; same priority", 10, Tail.IMPORTANT, DATE_OFFSET_SAME, RESULT_SAME_PRIORITY),
         Arguments.of("lower ID; higher priority", 5, Tail.IMPORTANT, DATE_OFFSET_SAME, RESULT_HIGHER_PRIORITY),
         Arguments.of("higher ID; lower priority", 10, Tail.IMPORTANT, DATE_OFFSET_SAME, RESULT_LOWER_PRIORITY),
         Arguments.of("higher priority class; higher priority", 0, Tail.URGENT, DATE_OFFSET_SAME, RESULT_HIGHER_PRIORITY),
         Arguments.of("lower priority class; lower priority", 0, Tail.REQUESTING, DATE_OFFSET_SAME, RESULT_LOWER_PRIORITY),
         Arguments.of("date offset before; higher priority", 0, Tail.IMPORTANT, DATE_OFFSET_BEFORE, RESULT_HIGHER_PRIORITY),
         Arguments.of("date offset after; lower priority", 0, Tail.IMPORTANT, DATE_OFFSET_AFTER, RESULT_LOWER_PRIORITY),
         Arguments.of("higher priority class; date offset before; higher priority", 0, Tail.URGENT, DATE_OFFSET_BEFORE, RESULT_HIGHER_PRIORITY),
         Arguments.of("higher priority class; date offset after; higher priority", 0, Tail.URGENT, DATE_OFFSET_AFTER, RESULT_HIGHER_PRIORITY),
         Arguments.of("lower priority class; date offset before; lower priority", 0, Tail.REQUESTING, DATE_OFFSET_BEFORE, RESULT_LOWER_PRIORITY),
         Arguments.of("lower priority class; date offset after; lower priority", 0, Tail.REQUESTING, DATE_OFFSET_AFTER, RESULT_LOWER_PRIORITY),
         Arguments.of("higher priority class; lower id; higher priority", 0, Tail.URGENT, DATE_OFFSET_SAME, RESULT_HIGHER_PRIORITY),
         Arguments.of("higher priority class; higher id; higher priority", 15, Tail.URGENT, DATE_OFFSET_SAME, RESULT_HIGHER_PRIORITY));
    }

    Tail tail;
    Calendar now;

    @BeforeEach
    void setUp() {
        now = Calendar.getInstance();
        tail = new Tail(10, Tail.IMPORTANT, now);
    }

    @MethodSource({ "compareToValues" })
    @ParameterizedTest(name = "[{index}] {0}")
    @SuppressWarnings({ "java:S2699" }) // the validationConsumer is the assertion function
    void compareTo(@SuppressWarnings("unused") String description, int id, int priority, int dateOffset, BiConsumer<Tail, Tail> validationConsumer) {
        Calendar calendar = (Calendar) now.clone();
        calendar.add(Calendar.SECOND, dateOffset);
        Tail other = new Tail(id, priority, calendar);
        validationConsumer.accept(tail, other);
    }

    @Test
    @SuppressWarnings({ "java:S2699" }) // RESULT_SAME_PRIORITY is the assertion
    void compareToSamePriorityForSameInstance() {
        RESULT_SAME_PRIORITY.accept(tail, tail);
    }
}
