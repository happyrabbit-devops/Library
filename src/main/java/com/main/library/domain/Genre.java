package com.main.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Жанр произведения
 */
@Getter
@AllArgsConstructor
public enum Genre {

    STORY(1, "Рассказ", "<Описание рассказа>"),
    HORROR(2, "Хоррор", "<Описание хоррора>"),
    NOVEL(3, "Роман", "<Описание романов>");

    /**
     * Порядок сортировки
     */
    private int id;

    /**
     * Название-заголовок
     */
    private String caption;

    /**
     * Описание
     */
    private String description;
}
