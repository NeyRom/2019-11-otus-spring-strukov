package ru.strukov.springorm.model;
/* Created by Roman Strukov in 29.03.2020 */

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class Genre {
    @Getter @Setter
    private long id;
    @Getter
    private String title;

    public void setTitle(String title) {
        this.title = title.toLowerCase();
    }

    @Override
    public String toString() {
        return String.format("%d - %s", id, title);
    }
}
